package rtg.event;

import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import rtg.RTG;
import rtg.config.rtg.ConfigRTG;
import rtg.util.Acceptor;
import rtg.util.Logger;
import rtg.util.RandomUtil;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.genlayer.RiverRemover;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;

@SuppressWarnings({"WeakerAccess","unused"})
public class EventManagerRTG
{
    private final LoadChunkRTG           LOAD_CHUNK_RTG          = new LoadChunkRTG();
    private final GenerateOreRTG         GENERATE_ORE_RTG        = new GenerateOreRTG();
    private final InitBiomeGensRTG       INIT_BIOME_GENS_RTG     = new InitBiomeGensRTG();
    private final BiomeFeaturesRTG       BIOME_FEATURES_RTG      = new BiomeFeaturesRTG();
    private final CustomTreesRTG         CUSTOM_TREES_RTG        = new CustomTreesRTG();

    public final WorldEventsRTG         DISPLAY_SEED_RTG        = new WorldEventsRTG();
    public final RTGEventRegister       RTG_EVENTS_ENABLER      = new RTGEventRegister();
    public final RTGEventUnregister     RTG_EVENTS_DISABLER     = new RTGEventUnregister();

    private WeakHashMap<Integer, Acceptor<ChunkEvent.Load>> chunkLoadEvents = new WeakHashMap<>();
    private RealisticBiomeBase biome = null;
    private boolean REGISTERED = false;
    private long WORLD_SEED;

    public EventManagerRTG() {
        // These should be registered once, and stay registered -srs
        MinecraftForge.TERRAIN_GEN_BUS.register(RTG_EVENTS_ENABLER);
        MinecraftForge.EVENT_BUS.register(RTG_EVENTS_DISABLER);
        MinecraftForge.EVENT_BUS.register(DISPLAY_SEED_RTG);
        Logger.info("RTG EventManager Initialised");
    }

    public class LoadChunkRTG
    {
        LoadChunkRTG() {
            Logger.debug("RTG Event System: Initialising LoadChunkRTG");
        }

        @SubscribeEvent
        public void loadChunkRTG(ChunkEvent.Load event) {
            Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(event.world.provider.dimensionId);
            if (acceptor != null) {
                acceptor.accept(event);
            }
        }
    }

    public class GenerateOreRTG
    {
        GenerateOreRTG() {
            Logger.debug("RTG Event System: Initialising GenerateOresRTG");
        }

        @SubscribeEvent
        public void onGenerateMinable(OreGenEvent.GenerateMinable event) {

            switch (event.type) {

                case COAL:
                    if (!ConfigRTG.generateOreCoal) { event.setResult(Result.DENY); }
                    return;

                case IRON:
                    if (!ConfigRTG.generateOreIron) { event.setResult(Result.DENY); }
                    return;

                case REDSTONE:
                    if (!ConfigRTG.generateOreRedstone) { event.setResult(Result.DENY); }
                    return;

                case GOLD:
                    if (!ConfigRTG.generateOreGold) { event.setResult(Result.DENY); }
                    return;

                case LAPIS:
                    if (!ConfigRTG.generateOreLapis) { event.setResult(Result.DENY); }
                    return;

                case DIAMOND:
                    if (!ConfigRTG.generateOreDiamond) { event.setResult(Result.DENY); }
                    return;

                default:
            }
        }
    }

    public class InitBiomeGensRTG
    {
        InitBiomeGensRTG()
        {
            Logger.debug("RTG Event System: Initialising InitBiomeGensRTG");
        }

        @SubscribeEvent
        public void initBiomeGensRTG(WorldTypeEvent.InitBiomeGens event) {

            if (event.newBiomeGens[0].getClass().getName().contains("GenLayerEB")) return;

            try {
                event.newBiomeGens = new RiverRemover().riverLess(event.originalBiomeGens);
            } catch (ClassCastException ex) {
                //throw ex;
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }

    public class BiomeFeaturesRTG
    {
        BiomeFeaturesRTG() {
            Logger.debug("RTG Event System: Initialising BiomeFeaturesRTG");
        }

        @SubscribeEvent(priority = EventPriority.LOW)
        public void doBiomeFeatures(InitMapGenEvent event) {

            Logger.debug("event type = %s", event.type.toString());
            Logger.debug("event originalGen = %s", event.originalGen.toString());

            switch (event.type) {
                case SCATTERED_FEATURE:
                    event.newGen = new MapGenScatteredFeatureRTG();
                    break;

                case VILLAGE:
                    if (ConfigRTG.enableVillageModifications) {
                        event.newGen = new MapGenVillageRTG();
                    }
                    break;

                case CAVE:
                    if (ConfigRTG.enableCaveModifications) {
                        event.newGen = new MapGenCavesRTG();
                    }
                    break;

                case RAVINE:
                    if (ConfigRTG.enableRavineModifications) {
                        event.newGen = new MapGenRavineRTG();
                    }
                    break;

                default:
            }
            Logger.debug("event newGen = %s", event.newGen.toString());
        }
    }

    public class CustomTreesRTG
    {
        CustomTreesRTG() {
            Logger.debug("RTG Event System: Initialising CustomTreesRTG");
        }

        @SubscribeEvent
        public void onSaplingGrowTree(SaplingGrowTreeEvent event) {
            // Are RTG saplings enabled?
            if (!ConfigRTG.enableRTGSaplings) { return; }

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

            WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) world.getWorldChunkManager();
            //BiomeGenBase bgg = cmr.getBiomeGenAt(x, z);
            BiomeGenBase bgg = world.getBiomeGenForCoords(x, z);
            RealisticBiomeBase rb = RealisticBiomeBase.getBiome(bgg.biomeID);
            ArrayList<TreeRTG> biomeTrees = rb.rtgTrees;

            Logger.debug("Biome = %s", rb.baseBiome.biomeName);
            Logger.debug("Ground Sapling Block = %s", saplingBlock.getLocalizedName());
            Logger.debug("Ground Sapling Meta = %d", saplingMeta);

            if (biomeTrees.size() > 0) {
                // First, let's get all of the trees in this biome that match the sapling on the ground.
                ArrayList<TreeRTG> validTrees = new ArrayList<>();

                for (int i = 0; i < biomeTrees.size(); i++) {

                    Logger.debug("Biome Tree #%d = %s", i, biomeTrees.get(i).getClass().getName());
                    Logger.debug("Biome Tree #%d Sapling Block = %s", i, biomeTrees.get(i).saplingBlock.getClass().getName());
                    Logger.debug("Biome Tree #%d Sapling Meta = %d", i, biomeTrees.get(i).saplingMeta);

                    if (saplingBlock == biomeTrees.get(i).saplingBlock && saplingMeta == biomeTrees.get(i).saplingMeta) {

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
                    boolean generated = tree.generate(world, rand, x, y, z);
                    tree.generateFlag = oldFlag;

                    if (generated) {

                        // Prevent the original tree from generating.
                        event.setResult(Result.DENY);

                        // Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
                        if (world.getBlock(x, y, z) == saplingBlock) {
                            world.setBlock(x, y, z, Blocks.air, (byte)0, 2);
                        }
                    }
                }
                else Logger.debug("There are no RTG trees associated with the sapling on the ground." +
                        " Generating a vanilla tree instead.");
            }
        }
    }

    public class WorldEventsRTG
    {
        WorldEventsRTG() {
            Logger.debug("RTG Event System: Initialising WorldEventsRTG");
        }

        @SubscribeEvent
        public void logWorldSeed(WorldEvent.Load event) {
            // This event fires for each dimension loaded (and then one last time in which it returns 0?),
            // so initialise a field to 0 and set it to the world seed and only display it in the log once.
            if (WORLD_SEED != event.world.getSeed() && event.world.getSeed() != 0) {

                WORLD_SEED = event.world.getSeed();
                Logger.info("World Seed: " + WORLD_SEED);
            }
        }

        @SubscribeEvent
        public void resetWorldSeed(WorldEvent.Unload event) {
            // reset WORLD_SEED so that it logs on the next server start if the seed is the same as the last load.
            WORLD_SEED = 0;
        }
    }

    public class RTGEventRegister
    {
        RTGEventRegister() {
            Logger.debug("RTG Event System: Initialising RTGEventRegister");
        }

        @SubscribeEvent
        public void registerRTGEventHandlers(WorldTypeEvent.InitBiomeGens event) {
            if (event.worldType instanceof WorldTypeRTG) {
                if (!REGISTERED) {
                    Logger.info("Registering RTG's Terrain Event Handlers...");
                    RTG.eventMgr.registerEventHandlers();
                    if (REGISTERED) Logger.info("RTG's Terrain Event Handlers have been registered successfully.");
                }
            }
            else {
                if (REGISTERED) RTG.eventMgr.unRegisterEventHandlers();
            }
        }
    }

    public class RTGEventUnregister
    {
        RTGEventUnregister() {
            Logger.debug("RTG Event System: Initialising RTGEventUnregister");
        }

        @SubscribeEvent
        public void unregisterRTGEventHandlers(WorldEvent.Unload event) {
            if (REGISTERED) {
                Logger.info("Unregistering RTG's Terrain Event Handlers...");
                RTG.eventMgr.unRegisterEventHandlers();
                if (!REGISTERED) Logger.info("RTG's Terrain Event Handlers have been unregistered successfully.");
            }
        }
    }

    public void registerEventHandlers() {
        MinecraftForge.EVENT_BUS        .register(LOAD_CHUNK_RTG);
        MinecraftForge.ORE_GEN_BUS      .register(GENERATE_ORE_RTG);
        MinecraftForge.TERRAIN_GEN_BUS  .register(INIT_BIOME_GENS_RTG);
        MinecraftForge.TERRAIN_GEN_BUS  .register(BIOME_FEATURES_RTG);
        MinecraftForge.TERRAIN_GEN_BUS  .register(CUSTOM_TREES_RTG);
        REGISTERED = true;
    }

    public void unRegisterEventHandlers() {
        MinecraftForge.EVENT_BUS        .unregister(LOAD_CHUNK_RTG);
        MinecraftForge.ORE_GEN_BUS      .unregister(GENERATE_ORE_RTG);
        MinecraftForge.TERRAIN_GEN_BUS  .unregister(INIT_BIOME_GENS_RTG);
        MinecraftForge.TERRAIN_GEN_BUS  .unregister(BIOME_FEATURES_RTG);
        MinecraftForge.TERRAIN_GEN_BUS  .unregister(CUSTOM_TREES_RTG);
        REGISTERED = false;
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
        chunkLoadEvents.put(dimension, action);
    }
}
