package rtg.event;

import java.util.ArrayList;
import java.util.Random;
import java.util.WeakHashMap;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;

import cpw.mods.fml.common.eventhandler.Event;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.EventPriority;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

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


public class EventManagerRTG
{
    // Event handlers.
    private final WorldEventRTG WORLD_EVENT_HANDLER = new WorldEventRTG();
    private final LoadChunkRTG LOAD_CHUNK_EVENT_HANDLER = new LoadChunkRTG();
    private final GenerateMinableRTG GENERATE_MINABLE_EVENT_HANDLER = new GenerateMinableRTG();
    private final InitBiomeGensRTG INIT_BIOME_GENS_EVENT_HANDLER = new InitBiomeGensRTG();
    private final InitMapGenRTG INIT_MAP_GEN_EVENT_HANDLER = new InitMapGenRTG();
    private final SaplingGrowTreeRTG SAPLING_GROW_TREE_EVENT_HANDLER = new SaplingGrowTreeRTG();
    private final DecorateBiomeEventRTG DECORATE_BIOME_EVENT_HANDLER = new DecorateBiomeEventRTG();

    private WeakHashMap<Integer, Acceptor<ChunkEvent.Load>> chunkLoadEvents = new WeakHashMap<>();
    private long worldSeed;
    private boolean isWorldTypeRTG = true;

    public EventManagerRTG() {

    }

    public class LoadChunkRTG
    {
        LoadChunkRTG() {
            logEventMessage("Initialising LoadChunkRTG...");
        }

        @SubscribeEvent
        public void loadChunkRTG(ChunkEvent.Load event) {

            // Are we in an RTG world?
            if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
                return;
            }

            Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(event.world.provider.dimensionId);
            if (acceptor != null) {
                acceptor.accept(event);
            }
        }
    }

    public class GenerateMinableRTG
    {
        GenerateMinableRTG() {
            logEventMessage("Initialising GenerateMinableRTG...");
        }

        @SubscribeEvent
        public void generateMinableRTG(OreGenEvent.GenerateMinable event) {

            // Are we in an RTG world?
            if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
                return;
            }

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
                	break;
            }
        }
    }

    public class InitBiomeGensRTG
    {
        InitBiomeGensRTG() {
            logEventMessage("Initialising InitBiomeGensRTG...");
        }

        @SubscribeEvent
        public void initBiomeGensRTG(WorldTypeEvent.InitBiomeGens event) {

            // Are we in an RTG world?
            if (!(event.worldType instanceof WorldTypeRTG)) {
                return;
            }

            if (event.newBiomeGens[0].getClass().getName().contains("GenLayerEB")) {
                return;
            }

            try {
                event.newBiomeGens = new RiverRemover().riverLess(event.originalBiomeGens);
            } catch (ClassCastException ex) {
                //throw ex;
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }

    public class InitMapGenRTG
    {
        InitMapGenRTG() {
            logEventMessage("Initialising InitMapGenRTG...");
        }

        @SubscribeEvent(priority = EventPriority.LOW)
        public void initMapGenRTG(InitMapGenEvent event) {

            // If the Overworld isn't an RTG dimension, then we definitely don't want to get involved here.
            // We need to do a try/catch because sometimes this event gets fired before the Overworld has loaded.
            try {
                if (!(MinecraftServer.getServer().worldServerForDimension(0).getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {
                    return;
                }
            }
            catch (Exception e) {

                Logger.debug("Overworld not loaded... checking global variable.");

                // Let's do one last sanity check to make sure it's safe to proceed.
                if (!isWorldTypeRTG) {
                    return;
                }
            }

            Logger.debug("event type = %s", event.type.toString());
            Logger.debug("event originalGen = %s", event.originalGen.toString());

            switch (event.type) {

                case SCATTERED_FEATURE:
                    if (ConfigRTG.enableScatteredFeatureModifications) {
                        event.newGen = new MapGenScatteredFeatureRTG();
                    }
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
                	break;
            }
            
            Logger.debug("event newGen = %s", event.newGen.toString());
        }
    }

    public class SaplingGrowTreeRTG
    {
        SaplingGrowTreeRTG() {
            logEventMessage("Initialising SaplingGrowTreeRTG...");
        }

        @SubscribeEvent
        public void saplingGrowTreeRTG(SaplingGrowTreeEvent event) {

            // Are RTG saplings enabled?
            if (!ConfigRTG.enableRTGSaplings) {
                return;
            }

            // Are we in an RTG world? Do we have RTG's chunk manager?
            if (!(event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG) || !(event.world.getWorldChunkManager() instanceof WorldChunkManagerRTG)) {
                return;
            }

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

            // Are we dealing with a sapling? Sounds like a silly question, but apparently it's one that needs to be asked.
            if (!(saplingBlock instanceof BlockSapling)) {
                Logger.debug("Cannot grow an RTG tree from a non-sapling block (%s).", saplingBlock.getLocalizedName());
                return;
            }

            // Let's try to get the sapling meta.
            int saplingMeta;
            try {
                saplingMeta = saplingBlock.getDamageValue(world, x, y, z);
            }
            catch (Exception e) {
                Logger.debug("Could not get sapling meta from sapling block (%s).", saplingBlock.getLocalizedName());
                saplingMeta = 0;
            }

            WorldChunkManagerRTG cmr = (WorldChunkManagerRTG) world.getWorldChunkManager();
            //BiomeGenBase bgg = cmr.getBiomeGenAt(x, z);
            BiomeGenBase bgg = world.getBiomeGenForCoords(x, z);
            RealisticBiomeBase rb = RealisticBiomeBase.getBiome(bgg.biomeID);

            // Instead of patching the biome, we should just return early here to allow vanilla logic to kick in.
            if (rb == null) {
                Logger.debug("NULL biome (%d) found when trying to grow an RTG tree from a sapling.", bgg.biomeID);
                return;
            }

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

                    // Prevent the original tree from generating.
                    event.setResult(Event.Result.DENY);

                    // Get a random tree from the list of valid trees.
                    TreeRTG tree = validTrees.get(rand.nextInt(validTrees.size()));

                    Logger.debug("Tree = %s", tree.getClass().getName());

                    // Set the trunk size if min/max values have been set.
                    if (tree.minTrunkSize > 0 && tree.maxTrunkSize > tree.minTrunkSize) {

                        tree.setTrunkSize(RandomUtil.getRandomInt(rand, tree.minTrunkSize, tree.maxTrunkSize));
                    }

                    // Set the crown size if min/max values have been set.
                    if (tree.minCrownSize > 0 && tree.maxCrownSize > tree.minCrownSize) {

                        tree.setCrownSize(RandomUtil.getRandomInt(rand, tree.minCrownSize, tree.maxCrownSize));
                    }

                    int treeHeight = tree.trunkSize + tree.crownSize;
                    if (treeHeight < 1) {
                        Logger.debug("Unable to grow RTG tree with no height.");
                        return;
                    }

                    if (!tree.hasSpaceToGrow(world, rand, x, y, z, treeHeight)) {
                        Logger.debug("Unable to grow RTG tree with %d height. Something in the way.", treeHeight);
                        return;
                    }

                    /*
                     * Set the generateFlag to what it needs to be for growing trees from saplings,
                     * generate the tree, and then set it back to what it was before.
                     *
                     * TODO: Does this affect the generation of normal RTG trees? - Pink
                     */
                    int oldFlag = tree.generateFlag;
                    tree.generateFlag = 3;
                    boolean generated = tree.generate(world, rand, x, y, z);
                    tree.generateFlag = oldFlag;

                    if (generated) {

                        // Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
                        if (world.getBlock(x, y, z) == saplingBlock) {
                            world.setBlock(x, y, z, Blocks.air, (byte)0, 2);
                        }
                    }
                }
                else {

                    Logger.debug("There are no RTG trees associated with the sapling on the ground." +
                        " Generating a vanilla tree instead.");
                }
            }
        }
    }

    public class WorldEventRTG
    {
        WorldEventRTG() {
            logEventMessage("Initialising WorldEventRTG...");
        }

        @SubscribeEvent
        public void onWorldLoad(WorldEvent.Load event) {

            // This event fires for each dimension loaded (and then one last time in which it returns 0?),
            // so initialise a field to 0 and set it to the world seed and only display it in the log once.
            if (worldSeed != event.world.getSeed() && event.world.getSeed() != 0) {

                worldSeed = event.world.getSeed();
                Logger.info("World Seed: " + worldSeed);
            }
        }

        @SubscribeEvent
        public void onWorldUnload(WorldEvent.Unload event) {
        	
            // Reset the world seed so that it logs on the next server start if the seed is the same as the last load.
            worldSeed = 0;
        }
    }

    public class DecorateBiomeEventRTG
    {
        DecorateBiomeEventRTG() {
            logEventMessage("Initialising DecorateBiomeEventRTG...");
        }

        @SubscribeEvent
        public void preBiomeDecorate(DecorateBiomeEvent.Pre event)
        {
            //Are we in an RTG world?
            isWorldTypeRTG = (event.world.getWorldInfo().getTerrainType() instanceof WorldTypeRTG);
        }
    }

    /*
     * This method registers most of RTG's event handlers.
     *
     * We don't need to check if the event handlers are unregistered before registering them
     * because Forge already performs those checks. This means that we could execute this method a
     * million times, and each event handler would still only be registered once.
     */
    public void registerEventHandlers() {

        logEventMessage("Registering RTG's event handlers...");

        MinecraftForge.EVENT_BUS.register(WORLD_EVENT_HANDLER);
        MinecraftForge.EVENT_BUS.register(LOAD_CHUNK_EVENT_HANDLER);
        MinecraftForge.ORE_GEN_BUS.register(GENERATE_MINABLE_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(INIT_BIOME_GENS_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(INIT_MAP_GEN_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(SAPLING_GROW_TREE_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(DECORATE_BIOME_EVENT_HANDLER);

        logEventMessage("RTG's event handlers have been registered successfully.");
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {
        chunkLoadEvents.put(dimension, action);
    }

    private static void logEventMessage(String message) {
        Logger.debug("RTG Event System: " + message);
    }
}