package rtg.event;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockPlanks.EnumType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenLiquids;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import rtg.RTGConfig;
import rtg.api.RTGAPI;
import rtg.api.util.BlockUtil;
import rtg.api.util.Direction;
import rtg.api.util.Logger;
import rtg.api.util.UtilityClass;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;
import rtg.api.world.deco.DecoVariableTree;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGCeibaPentandra;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGRhizophoraMucronata;
import rtg.api.world.gen.feature.tree.rtg.TreeRTGSalixMyrtilloides;
import rtg.api.world.gen.feature.tree.rtg.RTGSaplingManager;
import rtg.api.world.gen.feature.tree.rtg.TreeDensityLimiter;
import rtg.world.biome.BiomeProviderRTG;


@UtilityClass
public final class EventHandlerCommon
{
    private EventHandlerCommon() {}

    public static void init() {
        MinecraftForge.TERRAIN_GEN_BUS.register(EventHandlerCommon.class);
    }

    // TERRAIN_GEN_BUS
    @SubscribeEvent(priority = EventPriority.LOW)
    public static void onDecorateBiome(final DecorateBiomeEvent.Decorate event) {

        final World world = event.getWorld();
        if (world.getBiomeProvider() instanceof BiomeProviderRTG) {
            final Decorate.EventType eventType = event.getType();
            if (eventType == Decorate.EventType.LAKE_WATER || eventType == Decorate.EventType.LAKE_LAVA) {
                event.setResult(Event.Result.DENY);
                generateFalls(world, event.getRand(), event.getChunkPos(), eventType);
            }
        }
    }

    private static void generateFalls(final World world, final Random rand, final ChunkPos chunkPos, final Decorate.EventType type) {
        final BlockPos offsetpos = new BlockPos(chunkPos.x * 16 + 8, 0, chunkPos.z * 16 + 8);
        switch (type) {
            case LAKE_WATER:
                // reduced chance due to reduced random y level
                for (int i = 0; i < 20; i++) {
                    (new WorldGenLiquids(Blocks.FLOWING_WATER))
                        .generate(world, rand, offsetpos.add(rand.nextInt(16), rand.nextInt(64) + 8, rand.nextInt(16)));
                }
                break;
            case LAKE_LAVA:
                // reduced chance due to reduced random y level
                for (int i = 0; i < 5; i++) {
                    (new WorldGenLiquids(Blocks.FLOWING_LAVA))
                        .generate(world, rand, offsetpos.add(rand.nextInt(16), rand.nextInt(rand.nextInt(rand.nextInt(64) + 8) + 8), rand.nextInt(16)));
                }
                break;
            default:
        }
    }

    // TERRAIN_GEN_BUS
    @SuppressWarnings("deprecation")
    //@SubscribeEvent
    //  Deprecated
    public static void saplingGrowTreeRTG(SaplingGrowTreeEvent event) {

        final World world = event.getWorld();

        // skip if RTG saplings are disabled or this world does not use BiomeProviderRTG
        if (!RTGConfig.rtgTreesFromSaplings() || !(world.getBiomeProvider() instanceof BiomeProviderRTG)) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting: RTG trees are disabled, or not an RTG dimension");
            return;
        }

        final BlockPos pos = event.getPos();
        final IBlockState saplingBlock = world.getBlockState(pos);
        Logger.trace("Handling SaplingGrowTreeEvent in dim: {}, at: {}, for: {}", world.provider.getDimension(), pos, saplingBlock);

        // Are we dealing with a sapling? Sounds like a silly question, but apparently it's one that needs to be asked.
        if (!(saplingBlock.getBlock() instanceof BlockSapling)) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting: Sapling is not a sapling block ({})", saplingBlock.getBlock().getClass().getName());
            return;
        }

        final Random rand = event.getRand();

        // Should we generate a vanilla tree instead?
        int chance = RTGConfig.rtgTreeChance();
        if (rand.nextInt(chance < 1 ? 1 : chance) != 0) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting RTG tree generation: random chance");
            return;
        }

        final IRealisticBiome rtgBiome = RTGAPI.getRTGBiome(world.getBiome(pos));
        Collection<TreeRTG> biomeTrees = rtgBiome.getTrees();

        if (biomeTrees.isEmpty()) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting RTG tree generation: No RTG trees to generate in Biome: {}", rtgBiome.baseBiomeResLoc());
            return;
        }

        // First, let's get all of the trees in this biome that match the sapling on the ground.
        List<TreeRTG> validTrees = biomeTrees.stream()
            .filter(tree-> saplingBlock.getBlock() == tree.getSaplingBlock().getBlock() &&
                BlockUtil.getTypeFromSapling(saplingBlock) == BlockUtil.getTypeFromSapling(tree.getSaplingBlock()))
            .collect(Collectors.toList());

        // Abort if there are no valid trees.
        if (validTrees.isEmpty()) {
            Logger.debug("[SaplingGrowTreeEvent] No RTG trees found for sapling, so generating original tree instead");
            return;
        }

        // Get a random tree from the list of valid trees.
        TreeRTG tree = validTrees.get(rand.nextInt(validTrees.size()));

        // Set the trunk size if min/max values have been set.
        if (tree.getMinTrunkSize() > 0 && tree.getMaxTrunkSize() > tree.getMinTrunkSize()) {
            tree.setTrunkSize(DecoBase.getRangedRandom(rand, tree.getMinTrunkSize(), tree.getMaxTrunkSize()));
        }

        // Set the crown size if min/max values have been set.
        if (tree.getMinCrownSize() > 0 && tree.getMaxCrownSize() > tree.getMinCrownSize()) {
            tree.setCrownSize(DecoBase.getRangedRandom(rand, tree.getMinCrownSize(), tree.getMaxCrownSize()));
        }

        int treeHeight = tree.getTrunkSize() + tree.getCrownSize();
        if (treeHeight < 1) {
            Logger.debug("[SaplingGrowTreeEvent] Unable to grow RTG tree with no height: {}[logblock={}, leafblock={}, saplingblock={}]",
                tree.getClass().getSimpleName(), tree.getLogBlock(), tree.getLeavesBlock(), tree.getSaplingBlock());
            return;
        }

        if (!BlockUtil.checkVerticalMaterials(BlockUtil.MatchType.ALL_IGNORE_REPLACEABLE, world, pos.up(), treeHeight - 1)) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting RTG tree generation: not enough space above");
            return;
        }

        /*
         * Set the generateFlag to what it needs to be for growing trees from saplings,
         * generate the tree, and then set it back to what it was before.
         *
         * TODO: Does this affect the generation of normal RTG trees? - Pink
         */
        int oldFlag = tree.getGenerateFlag();
        tree.setGenerateFlag(19);
        boolean generated = tree.generate(world, rand, pos);
        tree.setGenerateFlag(oldFlag);

        if (generated) {
            event.setResult(Event.Result.DENY);
            // Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
            if (world.getBlockState(pos) == saplingBlock) {
                world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
            }
        }
        Logger.trace("Finished handling SaplingGrowTreeEvent in Biome: {}, with sapling: {}", rtgBiome.baseBiomeResLoc(), saplingBlock);
    }
 // TERRAIN_GEN_BUS
    @SubscribeEvent
    public static void variableSaplingGrowTreeRTG(SaplingGrowTreeEvent event) {

        final World world = event.getWorld();
        
        Logger.info("trying RTG trees", "");

        // skip if RTG saplings are disabled or this world does not use BiomeProviderRTG
        if (!RTGConfig.rtgTreesFromSaplings() || !(world.getBiomeProvider() instanceof BiomeProviderRTG)) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting: RTG trees are disabled, or not an RTG dimension");
            return;
        }

        final BlockPos pos = event.getPos();
        final IBlockState saplingBlock = world.getBlockState(pos);
        Logger.trace("Handling SaplingGrowTreeEvent in dim: {}, at: {}, for: {}", world.provider.getDimension(), pos, saplingBlock);

        // Are we dealing with a sapling? Sounds like a silly question, but apparently it's one that needs to be asked.
        if (!(saplingBlock.getBlock() instanceof BlockSapling)) {
            Logger.debug("[SaplingGrowTreeEvent] Aborting: Sapling is not a sapling block ({})", saplingBlock.getBlock().getClass().getName());
            return;
        }

        final Random rand = event.getRand();

        // Should we generate a vanilla tree instead?
        // TODO: RTGConfig.rtgTreeChance() is obsolete with this system
        
        RTGSaplingManager saplingManager = new RTGSaplingManager();//not saving this due to sapling growth being so infrequent
        

        Logger.info("managing", "");
        
        if (!saplingManager.manages(saplingBlock)) return;// do nothing if weren't not handling this sapling type
        
        int groupSize = countSaplingGroup(world,pos,saplingBlock);
        
        Logger.info("group size{}", groupSize);
        if (groupSize == 1) return; // lone saplings grow as vanilla
        
        if (groupSize ==4) {
        	// check for 4x4, which we will hand to vanilla for some trees
        	if (saplingManager.reject2x2(saplingBlock)) {
        		if (is2x2(world,pos,saplingBlock)) return;
        	}
        }
        
        //now check to see if this is not in the center of its group
        //by looking for adjacent directions with more around it
        
        BlockPos trunkLocation = pos;
        for (Direction direction: Direction.list()) {
        	BlockPos testLocation = direction.moved(pos);
        	int testCount = countSaplingGroup(world,testLocation,saplingBlock);

            Logger.info("test size {} {} {}", testCount, pos, testLocation);
        	if (testCount > groupSize) {
        		groupSize = testCount;
        		trunkLocation = testLocation;
        	}
        }

        Logger.info("group size{}", groupSize);
        
        // Swamp Willow special code
        if (saplingManager.couldBeSwampWillow(saplingBlock)) {
        	if (groupSize == 3) {
            	if (obtuseAngle(world,trunkLocation,saplingBlock)) {
            		new TreeRTGSalixMyrtilloides().generateSaplings(world, rand, pos);
            		finishGeneration(event,world,trunkLocation,saplingBlock);
            		return;
            	}
        	}
        }
        
        // Roofed Forest Tree Special Code
        if (saplingManager.darkOak(saplingBlock)) {
        	if (groupSize == 5) {        
        		TreeRTG pentandraTree = new TreeRTGCeibaPentandra(13f, 3, 0.32f, 0.1f);;
	            pentandraTree.setLogBlock(BlockUtil.getStateLog(EnumType.DARK_OAK));
	            pentandraTree.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.DARK_OAK));
	            pentandraTree.setMaxAllowedObstruction(TreeRTG.ROOFED_FOREST_LIGHT_OBSTRUCTION_LIMIT);
	            pentandraTree.setMinTrunkSize(2);
	            pentandraTree.setMaxTrunkSize(3);
	            pentandraTree.setMinCrownSize(9);
	            pentandraTree.setMaxCrownSize(18);
	            pentandraTree.setNoLeaves(false);
	            pentandraTree.randomizeTreeSize(rand);
	            pentandraTree.generate(world, rand, pos);
        		finishGeneration(event,world,trunkLocation,saplingBlock);
        		return;
        	}
        	if (groupSize == 6) {
                TreeRTG mucronataTree = new TreeRTGRhizophoraMucronata(3, 4, 13f, 0.32f, 0.1f);
                mucronataTree.setLogBlock(BlockUtil.getStateLog(EnumType.DARK_OAK));
                mucronataTree.setLeavesBlock(BlockUtil.getStateLeaf(EnumType.DARK_OAK));
                mucronataTree.setMaxAllowedObstruction(TreeRTG.ROOFED_FOREST_LIGHT_OBSTRUCTION_LIMIT);
                mucronataTree.setMinTrunkSize(2);
                mucronataTree.setMaxTrunkSize(3);
                mucronataTree.setMinCrownSize(9);
                mucronataTree.setMaxCrownSize(18);
                mucronataTree.setNoLeaves(false);
                mucronataTree.randomizeTreeSize(rand);
                mucronataTree.generate(world, rand, pos);
        		finishGeneration(event,world,trunkLocation,saplingBlock);
        		return;
        	}
        	// otherwise we're going to ignore this
        	return;
        }
        DecoVariableTree variableTree = saplingManager.tree(saplingBlock);
        
        // Determine height
        int actualHeight = variableTree.largestVanillaTree() + 1;
        if (groupSize > 2) {
        	// 2 is minimum height
        	actualHeight += (groupSize -3)*5 + rand.nextInt(5);
        }
        
        variableTree.doGenerate(world, rand, trunkLocation, actualHeight, new TreeDensityLimiter(1000000));
        finishGeneration(event,world,trunkLocation,saplingBlock);
    }
    
    private static void finishGeneration(SaplingGrowTreeEvent event,World world, BlockPos trunkLocation, IBlockState saplingBlock) {
        event.setResult(Event.Result.DENY);
        // Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
        if (RTGSaplingManager.similar(world.getBlockState(trunkLocation), saplingBlock)) {
            world.setBlockState(trunkLocation, Blocks.AIR.getDefaultState(), 2);
        }
    	for (Direction direction: Direction.list()) {
    		BlockPos adjacent = direction.moved(trunkLocation);
    		Logger.info("{} {}", world.getBlockState(adjacent),saplingBlock);
            if (RTGSaplingManager.similar(world.getBlockState(adjacent), saplingBlock)) {
                world.setBlockState(adjacent, Blocks.AIR.getDefaultState(), 2);
            }
    	}
    	
    }
    
    private static int countSaplingGroup(World world, BlockPos pos,IBlockState saplingBlock) {
    	if (!RTGSaplingManager.similar(world.getBlockState(pos), saplingBlock)) return  0;
    	int found = 1;
    	for (Direction direction: Direction.list()) {
    		if (RTGSaplingManager.similar(world.getBlockState(direction.moved(pos)), saplingBlock)) {
    			found++;
    		}
    	}
    	return found;
    }
    
    private static boolean obtuseAngle(World world, BlockPos pos,IBlockState saplingBlock) {
    	ArrayList<Direction> found = new ArrayList<>();
    			
    	for (Direction direction: Direction.list()) {
    		if (RTGSaplingManager.similar(world.getBlockState(direction.moved(pos)), saplingBlock)) {
    			found.add(direction);
    		}
    	}
    	if (found.size() == 2) {
    		//must be two
    		int different = found.get(1).index - found.get(0).index;
    		if (different==3) return true;// obtuse angle
    		if (different==5) return true;// obtuse the other way
    	}
    	return false;
    }
    
    private static boolean is2x2(World world, BlockPos pos,IBlockState saplingBlock) {
    	
    	// northeast
    	if (world.getBlockState(pos.north()) == saplingBlock) 
    		if (world.getBlockState(pos.east()) == saplingBlock)
        		if (world.getBlockState(pos.east().north()) == saplingBlock) return true;

    	// northwest
    	if (world.getBlockState(pos.north()) == saplingBlock) 
    		if (world.getBlockState(pos.west()) == saplingBlock)
        		if (world.getBlockState(pos.west().north()) == saplingBlock) return true;
    	// southeast
    	if (world.getBlockState(pos.south()) == saplingBlock) 
    		if (world.getBlockState(pos.east()) == saplingBlock)
        		if (world.getBlockState(pos.east().south()) == saplingBlock) return true;

    	// southwest
    	if (world.getBlockState(pos.south()) == saplingBlock) 
    		if (world.getBlockState(pos.west()) == saplingBlock)
        		if (world.getBlockState(pos.west().south()) == saplingBlock) return true;
    	
    	// none of the above
    	return false;
    }
}
