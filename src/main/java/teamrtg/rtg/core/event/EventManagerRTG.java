package teamrtg.rtg.core.event;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamrtg.rtg.api.module.Mods;
import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.util.Acceptor;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.util.math.RandomUtil;
import teamrtg.rtg.api.world.biome.RTGBiome;
import teamrtg.rtg.core.RTG;
import teamrtg.rtg.core.world.BiomeProviderRTG;
import teamrtg.rtg.core.world.WorldTypeRTG;
import teamrtg.rtg.core.world.gen.MapGenCavesRTG;
import teamrtg.rtg.core.world.gen.MapGenRavineRTG;
import teamrtg.rtg.core.world.gen.genlayer.RiverRemover;
import teamrtg.rtg.core.world.gen.structure.MapGenScatteredFeatureRTG;
import teamrtg.rtg.core.world.gen.structure.MapGenVillageRTG;
import teamrtg.rtg.core.world.gen.structure.StructureOceanMonumentRTG;

import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

public class EventManagerRTG {

	private WeakHashMap<Integer,Acceptor<ChunkEvent.Load>> chunkLoadEvents =
            new WeakHashMap<>();

    public EventManagerRTG() {
        MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        if (Mods.RTG.config.ENABLE_VILLAGE_MODIFICATIONS.get())
            MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
        MapGenStructureIO.registerStructure(StructureOceanMonumentRTG.StartMonument.class, "rtg_MapGenOceanMonumentRTG");
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void eventListenerRTG(InitMapGenEvent event) {

        Logger.debug("event type = %s", event.getType().toString());
        Logger.debug("event originalGen = %s", event.getOriginalGen().toString());

        if (event.getType() == InitMapGenEvent.EventType.SCATTERED_FEATURE) {
            event.setNewGen(new MapGenScatteredFeatureRTG());
        } else if (event.getType() == InitMapGenEvent.EventType.VILLAGE) {

            if (Mods.RTG.config.ENABLE_VILLAGE_MODIFICATIONS.get()) {
                event.setNewGen(new MapGenVillageRTG());
            }
        } else if (event.getType() == InitMapGenEvent.EventType.CAVE) {

            if (Mods.RTG.config.ENABLE_CAVE_MODIFICATIONS.get()) {

                event.setNewGen(new MapGenCavesRTG());
            }
        } else if (event.getType() == InitMapGenEvent.EventType.RAVINE) {

            if (Mods.RTG.config.ENABLE_RAVINE_MODIFICATIONS.get()) {

                event.setNewGen(new MapGenRavineRTG());
            }
        } else if (event.getType() == InitMapGenEvent.EventType.OCEAN_MONUMENT) {
            event.setNewGen(new StructureOceanMonumentRTG());
        }
        Logger.debug("event newGen = %s", event.getNewGen().toString());
    }

    @SubscribeEvent
    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {

        // only handle RTG world type
        if (!event.getWorldType().getWorldTypeName().equalsIgnoreCase("RTG")) return;

        boolean stripRivers = true; // This used to be a config option. Hardcoding until we have a need for the option.

        if (stripRivers) {
            try {
                event.setNewBiomeGens(new RiverRemover().riverLess(event.getOriginalBiomeGens()));
            } catch (ClassCastException ex) {
                //throw ex;
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if ((event.getWorld().getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
            if (event.getWorld().provider.getDimension() == 0) {
                Logger.info("World Seed: %d", event.getWorld().getSeed());
                Logger.info("Reloading configs");
                Mods.syncAllConfigs();
            }
        } else {
            MinecraftForge.TERRAIN_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.ORE_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.EVENT_BUS.unregister(RTG.eventMgr);
        }

    }
    
	@SubscribeEvent
	public void onSaplingGrowTree(SaplingGrowTreeEvent event)
	{
		
		// Are RTG saplings enabled?
		if (!Mods.RTG.config.ENABLE_SAPLINGS.get()) {
			return;
		}
		
		// Are we in an RTG world? Do we have RTG's chunk manager?
		if (!(event.getWorld().getWorldInfo().getTerrainType() instanceof WorldTypeRTG) || !(event.getWorld().getBiomeProvider() instanceof BiomeProviderRTG)) {
			return;
		}
		
		Random rand = event.getRand();
		
		// Should we generate a vanilla tree instead?
		if (rand.nextInt(Mods.RTG.config.RTG_TREE_CHANCE.get()) != 0) {
			Logger.debug("Skipping RTG tree generation.");
			return;
		}		
		
		World world = event.getWorld();
		BlockPos pos = event.getPos();
		IBlockState saplingBlock = world.getBlockState(pos);

		BiomeProviderRTG cmr = (BiomeProviderRTG) event.getWorld().getBiomeProvider();
		Biome vb = world.getBiome(pos);
		RTGBiome rb = RTGBiome.forBiome(vb.getIdForBiome(vb));
		ArrayList<TreeRTG> biomeTrees = rb.rtgTrees;
		
		Logger.debug("Biome = %s", vb.getBiomeName());
		Logger.debug("Ground Sapling Block = %s", saplingBlock.getBlock().getLocalizedName());
		Logger.debug("Ground Sapling Meta = %d", saplingBlock.getBlock().getMetaFromState(saplingBlock));

		if (biomeTrees.size() > 0) {
			
			// First, let's get all of the trees in this biome that match the sapling on the ground.
			ArrayList<TreeRTG> validTrees = new ArrayList<TreeRTG>();
			
			for (int i = 0; i < biomeTrees.size(); i++) {
				
				Logger.debug("Biome Tree #%d = %s", i, biomeTrees.get(i).getClass().getName());
				Logger.debug("Biome Tree #%d Sapling Block = %s", i, biomeTrees.get(i).saplingBlock.getBlock().getLocalizedName());
				Logger.debug("Biome Tree #%d Sapling Meta = %d", i, biomeTrees.get(i).saplingBlock.getBlock().getMetaFromState(biomeTrees.get(i).saplingBlock));
				
				if (saplingBlock == biomeTrees.get(i).saplingBlock) {
					validTrees.add(biomeTrees.get(i));
					Logger.debug("Valid tree found!");
				}
			}
			
			// If there are valid trees, then proceed; otherwise, let's get out here.
			if (validTrees.size() > 0) {
				
				// Get a random tree from the list of valid trees.
				TreeRTG tree = validTrees.get(rand.nextInt(validTrees.size()));
				
				Logger.debug("Tree = %s", tree.getClass().getName());

				// Set the trunk size if min/max values have been set.
				if (tree.minTrunkSize > 0 && tree.maxTrunkSize > tree.minTrunkSize) {
					tree.trunkSize = RandomUtil.getRandomInt(rand, tree.minTrunkSize, tree.maxTrunkSize);
				}
				
				// Set the crown size if min/max values have been set.
				if (tree.minCrownSize > 0 && tree.maxCrownSize > tree.minCrownSize) {
					tree.crownSize = RandomUtil.getRandomInt(rand, tree.minCrownSize, tree.maxCrownSize);
				}

				/**
				 * Set the generateFlag to what it needs to be for growing trees from saplings,
				 * generate the tree, and then set it back to what it was before.
				 * 
				 * TODO: Does this affect the generation of normal RTG trees?
				 */
				int oldFlag = tree.generateFlag;
				tree.generateFlag = 3;
				boolean generated = tree.generate(world, rand, pos);
				tree.generateFlag = oldFlag;

				if (generated) {
					
					// Prevent the original tree from generating.
					event.setResult(Result.DENY);
					
					// Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
					if (world.getBlockState(pos) == saplingBlock) {
						world.setBlockState(pos, Blocks.AIR.getDefaultState(), 2);
					}
				}
			}
			else {
				
				Logger.debug("There are no RTG trees associated with the sapling on the ground. Generating a vanilla tree instead.");
			}
		}
	}
	
    @SubscribeEvent
    public void onGenerateMinable(OreGenEvent.GenerateMinable event) {

    	if (Mods.RTG.config.GENERATE_ORES.get()) {
    	
	    	// COAL, DIAMOND, DIRT, GOLD, GRAVEL, IRON, LAPIS, REDSTONE, QUARTZ, DIORITE, GRANITE, ANDESITE, EMERALD, SILVERFISH, CUSTOM
	        switch (event.getType()) {
	            
		        case COAL:
		            if (!Mods.RTG.config.GENERATE_ORES_COAL.get()) { event.setResult(Result.DENY); }
		            return;
		            
		        case DIAMOND:
		            if (!Mods.RTG.config.GENERATE_ORES_DIAMOND.get()) { event.setResult(Result.DENY); }
		            return;
	            
		        case DIRT:
		            if (!Mods.RTG.config.GENERATE_ORES_DIRT.get()) { event.setResult(Result.DENY); }
		            return;
		            
	            case GOLD:
	                if (!Mods.RTG.config.GENERATE_ORES_GOLD.get()) { event.setResult(Result.DENY); }
	                return;
		            
		        case GRAVEL:
		            if (!Mods.RTG.config.GENERATE_ORES_GRAVEL.get()) { event.setResult(Result.DENY); }
		            return;
	
	            case IRON:
	                if (!Mods.RTG.config.GENERATE_ORES_IRON.get()) { event.setResult(Result.DENY); }
	                return;
	                
	            case LAPIS:
	                if (!Mods.RTG.config.GENERATE_ORES_LAPIS.get()) { event.setResult(Result.DENY); }
	                return;
	                
	            case REDSTONE:
	                if (!Mods.RTG.config.GENERATE_ORES_REDSTONE.get()) { event.setResult(Result.DENY); }
	                return;
	                
	            case DIORITE:
	                if (!Mods.RTG.config.GENERATE_ORES_DIORITE.get()) { event.setResult(Result.DENY); }
	                return;                
	
	            case GRANITE:
	                if (!Mods.RTG.config.GENERATE_ORES_GRANITE.get()) { event.setResult(Result.DENY); }
	                return;
	                
	            case ANDESITE:
	                if (!Mods.RTG.config.GENERATE_ORES_ANDESITE.get()) { event.setResult(Result.DENY); }
	                return;
	                
	            case EMERALD:
	                if (!Mods.RTG.config.GENERATE_ORES_EMERALD.get()) { event.setResult(Result.DENY); }
	                return;
	
	            default:
	            	return;
	        }
    	}
    }

    @SubscribeEvent
    public void onChunkLoadEvent(ChunkEvent.Load loadEvent)  {
        Integer dimension = loadEvent.getWorld().provider.getDimension();
        Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(dimension);
        if (acceptor != null) {
            acceptor.accept(loadEvent);
        }
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
        chunkLoadEvents.put(dimension, action);
    }
}