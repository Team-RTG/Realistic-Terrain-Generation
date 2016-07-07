package rtg.event;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenMineshaft;
import net.minecraft.world.gen.structure.MapGenStronghold;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import rtg.RTG;
import rtg.config.rtg.ConfigRTG;
import rtg.util.Acceptor;
import rtg.util.Logger;
import rtg.util.RandomUtil;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.genlayer.RiverRemover;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;

import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

@SuppressWarnings("unused")
public class EventManagerRTG {

    public RealisticBiomeBase biome = null;
    private WeakHashMap<Integer, Acceptor<ChunkEvent.Load>> chunkLoadEvents = new WeakHashMap<>();

    public EventManagerRTG() {
        Logger.info("Creating RTG Event Manager");

        // Move this to the main class.. hope it doesn't break anything -srs
        // MapGenStructureIO.registerStructure(MapGenScatteredFeatureRTG.Start.class, "rtg_MapGenScatteredFeatureRTG");
        // MapGenStructureIO.registerStructure(MapGenVillageRTG.Start.class, "rtg_MapGenVillageRTG");
    }

    @SubscribeEvent(priority = EventPriority.LOW)
    public void mapGenEvent(InitMapGenEvent event) {
        Logger.debug("InitMapGenEvent: Attempting to generate a " + event.type.toString());

        switch (event.type) {
            case SCATTERED_FEATURE:
                if (ConfigRTG.generateScatteredFeatures)
                    event.newGen = new MapGenScatteredFeatureRTG();
                break;
            case CAVE:
                if (ConfigRTG.enableCaveModifications)
                    event.newGen = new MapGenCavesRTG();
                break;
            case RAVINE:
                if (ConfigRTG.enableRavineModifications)
                    event.newGen = new MapGenRavineRTG();
                break;
            case VILLAGE:
                if (ConfigRTG.enableVillageModifications)
                    event.newGen = new MapGenVillageRTG();
                break;
            case MINESHAFT:
                if (ConfigRTG.generateMineshafts)
                    event.newGen = new MapGenMineshaft();
                break;
            case STRONGHOLD:
                if (ConfigRTG.generateStrongholds)
                    event.newGen = new MapGenStronghold();
                break;
            default:
                break;
        }
    }

    @SubscribeEvent
    public void onGenerateMinable(OreGenEvent.GenerateMinable event) {
        switch (event.type) {
            case COAL:
                if (!ConfigRTG.generateOreCoal)
                    event.setResult(Result.DENY);
                break;
            case IRON:
                if (!ConfigRTG.generateOreIron)
                    event.setResult(Result.DENY);
                break;
            case GOLD:
                if (!ConfigRTG.generateOreGold)
                    event.setResult(Result.DENY);
                break;
            case DIAMOND:
                if (!ConfigRTG.generateOreDiamond)
                    event.setResult(Result.DENY);
                break;
            case REDSTONE:
                if (!ConfigRTG.generateOreRedstone)
                    event.setResult(Result.DENY);
                break;
            case LAPIS:
                if (!ConfigRTG.generateOreLapis)
                    event.setResult(Result.DENY);
                break;
            default:
                break;
        }
    }

    @SubscribeEvent
    public void onBiomeGenInit(WorldTypeEvent.InitBiomeGens event) {
        // only handle RTG world type
        if (!RTG.WORLD.isWorldTypeRTG()) return;

        if (event.newBiomeGens[0].getClass().getName().contains("GenLayerEB")) return;

        // FIXME: I'm removing this, but leaving it commented out for posterity, in case.
        // This used to be a config option. Hardcoding until we have a need for the option.
        // boolean stripRivers = true;
        // if (stripRivers) {
        try {
            event.newBiomeGens = new RiverRemover().riverLess(event.originalBiomeGens);
        } catch (ClassCastException ex) {
            //throw ex;
            // failed attempt because the GenLayers don't end with GenLayerRiverMix
        }
        //}
    }

    @SubscribeEvent
    public void onWorldLoad(WorldEvent.Load event) {
        if (RTG.WORLD.isWorldTypeRTG()) {
            Logger.info("WorldEvent.Load: RTG World Started with seed: " +
            MinecraftServer.getServer().getEntityWorld().getSeed());
        }
    }

    @SubscribeEvent
    public void onGetVillageBlockID(BiomeEvent.GetVillageBlockID event) {

        if (this.isDesertVillageBiome((event.biome == null) ? this.biome.baseBiome : event.biome)) {

            Block originalBlock = event.original;

            if (originalBlock == Blocks.cobblestone || originalBlock == Blocks.planks || originalBlock == Blocks.log
                    || originalBlock == Blocks.log2 || originalBlock == Blocks.gravel) {

                event.replacement = Blocks.sandstone;
            } else if (originalBlock == Blocks.oak_stairs || originalBlock == Blocks.stone_stairs) {

                event.replacement = Blocks.sandstone_stairs;
            }

            // The event has to be cancelled in order to override the original block.
            if (event.replacement != null) {
                event.setResult(Result.DENY);
            }
        }
    }

    private boolean isDesertVillageBiome(BiomeGenBase biome) {
        return biome != null &&
                BiomeDictionary.isBiomeOfType(biome, Type.HOT) &&
                BiomeDictionary.isBiomeOfType(biome, Type.DRY) &&
                BiomeDictionary.isBiomeOfType(biome, Type.SANDY);
    }

    @SubscribeEvent
    public void onGetVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event) {

        boolean replaced = false;

        // Use event.biome, if that's null, fall back to our own copy
        if (this.isDesertVillageBiome((event.biome == null) ? this.biome.baseBiome : event.biome)) {

            Block originalBlock = event.original;

            if (originalBlock == Blocks.log ||
                    originalBlock == Blocks.log2 ||
                    originalBlock == Blocks.cobblestone) {

                event.replacement = 0;
                replaced = true;
            }

            if (originalBlock == Blocks.planks) {
                event.replacement = 2;
                replaced = true;
            }
        }

        // The event has to be cancelled in order to override the original block.
        if (replaced) {
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void preBiomeDecorate(DecorateBiomeEvent.Pre event) {
    /**
     *  FIXME:  We shouldn't have to check for this, if WorldType!=RTG this Event Manager
     *          shouldn't even be registered, and if it is, then something is broken. -srs
    */
    // Are we in an RTG world? Do we have RTG's chunk manager?
    //  if (RTG.WORLD.isWorldTypeRTG() && event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG)
    //  {
            WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) event.world.getWorldChunkManager();
            this.biome = cmr.getBiomeDataAt(event.chunkX, event.chunkZ);
    //  }
    }

    @SubscribeEvent
    public void onSaplingGrowTree(SaplingGrowTreeEvent event) {

        // Are RTG saplings enabled?
        if (!ConfigRTG.enableRTGSaplings) { return; }

        /**
         *  FIXME:  We shouldn't have to check for this, if WorldType!=RTG this Event Manager
         *          shouldn't even be registered, and if it is, then something is broken. -srs
         */
/*
        Are we in an RTG world? Do we have RTG's chunk manager?
        if (!RTG.WORLD.isWorldTypeRTG() || !(event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG))
        {
			return;
		}
*/
        Random rand = event.rand;

        // Should we generate a vanilla tree instead?
        if (rand.nextInt(ConfigRTG.rtgTreeChance) != 0) {
            Logger.debug("Skipping RTG tree generation.");
            return;
        }

        World world = event.world;
        int x = event.x;
        int y = event.y;
        int z = event.z;

        Block saplingBlock = world.getBlock(x, y, z);
        byte saplingMeta = (byte) saplingBlock.getDamageValue(world, x, y, z);

        BiomeGenBase bgg = world.getBiomeGenForCoords(x, z);
        RealisticBiomeBase rb = RealisticBiomeBase.getBiome(bgg.biomeID);
        ArrayList<TreeRTG> biomeTrees = rb.rtgTrees;

        Logger.debug("Biome: " + rb.baseBiome.biomeName + ", Sapling: " +
                Block.blockRegistry.getNameForObject(saplingBlock) + ":" + saplingMeta);

        if (biomeTrees.size() > 0) {
            // First, let's get all of the trees in this biome that match the sapling on the ground.
            ArrayList<TreeRTG> validTrees = new ArrayList<>();

            for (int i = 0; i < biomeTrees.size(); i++) {
                Logger.debug("Biome Tree #" + i + " = " + biomeTrees.get(i).getClass().getSimpleName());
                Logger.debug("Biome Tree #" + i + " Sapling Block:meta = " +
                        Block.blockRegistry.getNameForObject(biomeTrees.get(i).saplingBlock) + ":" +
                        biomeTrees.get(i).saplingMeta);

                if (saplingBlock == biomeTrees.get(i).saplingBlock && saplingMeta == biomeTrees.get(i).saplingMeta) {
                    validTrees.add(biomeTrees.get(i));
                    Logger.debug("Valid tree found!");
                }
            }

            // If there are valid trees, then proceed; otherwise, let's get out here.
            if (validTrees.size() > 0) {
                // Get a random tree from the list of valid trees.
                TreeRTG tree = validTrees.get(rand.nextInt(validTrees.size()));

                Logger.debug("Tree = " + tree.getClass().getName());

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
                boolean generated = tree.generate(world, rand, x, y, z);
                tree.generateFlag = oldFlag;

                if (generated) {
                    // Prevent the original tree from generating.
                    event.setResult(Result.DENY);

                    // Sometimes we have to remove the sapling manually because
                    // some trees grow around it, leaving the original sapling.
                    if (world.getBlock(x, y, z) == saplingBlock) {
                        world.setBlock(x, y, z, Blocks.air, (byte) 0, 2);
                    }
                }
            } else {
                Logger.debug("No associated RTG trees for Biome: " + biome + ", sapling: " +
                        Block.blockRegistry.getNameForObject(saplingBlock) + ":" + saplingMeta);
                Logger.debug("Generating vanilla tree instead.");
            }
        }
    }

    @SubscribeEvent
    public void onChunkLoadEvent(ChunkEvent.Load loadEvent) {
        Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(loadEvent.world.provider.dimensionId);
        if (acceptor != null) {
            acceptor.accept(loadEvent);
        }
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
        chunkLoadEvents.put(dimension, action);
    }
}