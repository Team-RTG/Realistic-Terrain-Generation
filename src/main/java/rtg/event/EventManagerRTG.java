package rtg.event;

import java.util.ArrayList;
import java.util.WeakHashMap;

import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.SaplingGrowTreeEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.ChunkEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_LAVA;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LAKE_WATER;
import static net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType.*;

import rtg.api.RTGAPI;
import rtg.api.config.RTGConfig;
import rtg.api.dimension.DimensionManagerRTG;
import rtg.api.event.SurfaceEvent;
import rtg.api.util.*;
import rtg.api.world.gen.feature.tree.rtg.TreeRTG;
import rtg.util.UBColumnCache;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeProviderRTG;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.genlayer.RiverRemover;


@SuppressWarnings({"WeakerAccess", "unused"})
public class EventManagerRTG {

    // Event handlers.
    private final WorldEventRTG WORLD_EVENT_HANDLER = new WorldEventRTG();
    private final LoadChunkRTG LOAD_CHUNK_EVENT_HANDLER = new LoadChunkRTG();
    private final GenerateMinableRTG GENERATE_MINABLE_EVENT_HANDLER = new GenerateMinableRTG();
    private final InitBiomeGensRTG INIT_BIOME_GENS_EVENT_HANDLER = new InitBiomeGensRTG();
    private final SaplingGrowTreeRTG SAPLING_GROW_TREE_EVENT_HANDLER = new SaplingGrowTreeRTG();
    private final DecorateBiomeEventRTG DECORATE_BIOME_EVENT_HANDLER = new DecorateBiomeEventRTG();
    private final SurfaceEventRTG SURFACE_EVENT_HANDLER = new SurfaceEventRTG();

    private WeakHashMap<Integer, Acceptor<ChunkEvent.Load>> chunkLoadEvents = new WeakHashMap<>();
    private long worldSeed;
    private RTGConfig rtgConfig;

    public EventManagerRTG() {

        rtgConfig = RTGAPI.config();
    }

    public class LoadChunkRTG {

        LoadChunkRTG() {

            logEventMessage("Initialising LoadChunkRTG...");
        }

        @SubscribeEvent
        public void loadChunkRTG(ChunkEvent.Load event) {

            // Are we in an RTG world?
            if (!DimensionManagerRTG.isValidDimension(event.getWorld().provider.getDimension())) {
                return;
            }

            Acceptor<ChunkEvent.Load> acceptor = chunkLoadEvents.get(event.getWorld().provider.getDimension());
            if (acceptor != null) {
                acceptor.accept(event);
            }
        }
    }

    public class GenerateMinableRTG {

        @SubscribeEvent
        public void generateMinableRTG(OreGenEvent.GenerateMinable event) {

            // Are we in an RTG world?
            if (!(event.getWorld().getWorldType() instanceof WorldTypeRTG)) {
                return;
            }

            // Are we in a valid dimension?
            if (!DimensionManagerRTG.isValidDimension(event.getWorld().provider.getDimension())) {
                return;
            }

            ChunkOreGenTracker chunkOreGenTracker = WorldTypeRTG.chunkProvider.getChunkOreGenTracker();
            BlockPos eventPos = event.getPos();
            OreGenEvent.GenerateMinable.EventType eventType = event.getType();
            String eventName = null;
            boolean allowCancel = rtgConfig.ALLOW_ORE_GEN_EVENT_CANCELLATION.get();

            // No switch statements allowed! - Pink

            if (eventType == ANDESITE) {
                eventName = "ANDESITE";
                if (!rtgConfig.GENERATE_ORE_ANDESITE.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == COAL) {
                eventName = "COAL";
                if (!rtgConfig.GENERATE_ORE_COAL.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == DIAMOND) {
                eventName = "DIAMOND";
                if (!rtgConfig.GENERATE_ORE_DIAMOND.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == DIORITE) {
                eventName = "DIORITE";
                if (!rtgConfig.GENERATE_ORE_DIORITE.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == DIRT) {
                eventName = "DIRT";
                if (!rtgConfig.GENERATE_ORE_DIRT.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == EMERALD) {
                eventName = "EMERALD";
                if (!rtgConfig.GENERATE_ORE_EMERALD.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == GOLD) {
                eventName = "GOLD";
                if (!rtgConfig.GENERATE_ORE_GOLD.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == GRANITE) {
                eventName = "GRANITE";
                if (!rtgConfig.GENERATE_ORE_GRANITE.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == GRAVEL) {
                eventName = "GRAVEL";
                if (!rtgConfig.GENERATE_ORE_GRAVEL.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == IRON) {
                eventName = "IRON";
                if (!rtgConfig.GENERATE_ORE_IRON.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == LAPIS) {
                eventName = "LAPIS";
                if (!rtgConfig.GENERATE_ORE_LAPIS.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == REDSTONE) {
                eventName = "REDSTONE";
                if (!rtgConfig.GENERATE_ORE_REDSTONE.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }
            else if (eventType == SILVERFISH) {
                eventName = "SILVERFISH";
                if (!rtgConfig.GENERATE_ORE_SILVERFISH.get() || (chunkOreGenTracker.hasGeneratedOres(eventPos) && allowCancel)) {
                    event.setResult(Event.Result.DENY);
                }
            }

            //Logger.debug("%s EVENT @ %d %d %d (%d %d)", eventName, eventPos.getX(), eventPos.getY(), eventPos.getZ(), (eventPos.getX() / 16), (eventPos.getZ() / 16));
        }
    }

    public class InitBiomeGensRTG {

        @SubscribeEvent
        public void initBiomeGensRTG(WorldTypeEvent.InitBiomeGens event) {

            // Are we in an RTG world?
            if (!(event.getWorldType() instanceof WorldTypeRTG)) {
                return;
            }

            try {
                event.setNewBiomeGens(new RiverRemover().riverLess(event.getOriginalBiomeGens()));
            }
            catch (ClassCastException ex) {
                //throw ex;
                // failed attempt because the GenLayers don't end with GenLayerRiverMix
            }
        }
    }

    public class SaplingGrowTreeRTG {

        SaplingGrowTreeRTG() {

            logEventMessage("Initialising SaplingGrowTreeRTG...");
        }

        @SubscribeEvent
        public void saplingGrowTreeRTG(SaplingGrowTreeEvent event) {

            // Are RTG saplings enabled?
            if (!rtgConfig.ENABLE_RTG_SAPLINGS.get()) {
                return;
            }

            // Are we in an RTG world? Do we have RTG's chunk manager?
            if (!(DimensionManagerRTG.isValidDimension(event.getWorld().provider.getDimension())) ||
                !(event.getWorld().getBiomeProvider() instanceof BiomeProviderRTG)) {
                return;
            }

            // Should we generate a vanilla tree instead?
            if (event.getRand().nextInt(rtgConfig.RTG_TREE_CHANCE.get()) != 0) {

                Logger.debug("Skipping RTG tree generation.");
                return;
            }

            IBlockState saplingBlock = event.getWorld().getBlockState(event.getPos());

            // Are we dealing with a sapling? Sounds like a silly question, but apparently it's one that needs to be asked.
            if (!(saplingBlock.getBlock() instanceof BlockSapling)) {
                Logger.debug("Could not get sapling meta from non-sapling BlockState (%s).", saplingBlock.getBlock().getLocalizedName());
                return;
            }

            BiomeProviderRTG cmr = (BiomeProviderRTG) event.getWorld().getBiomeProvider();
            //Biome bgg = cmr.getBiomeGenAt(x, z);
            Biome bgg = event.getWorld().getBiome(event.getPos());
            RealisticBiomeBase rb = RealisticBiomeBase.getBiome(Biome.getIdForBiome(bgg));

            // Instead of patching the biome, we should just return early here to allow vanilla logic to kick in.
            if (rb == null) {
                Logger.debug("NULL biome (%d) found when trying to grow an RTG tree from a sapling.", Biome.getIdForBiome(bgg));
                return;
            }

            ArrayList<TreeRTG> biomeTrees = rb.getTrees();
            int saplingMeta = SaplingUtil.getMetaFromState(saplingBlock);

            Logger.debug("Biome = %s", rb.baseBiome.getBiomeName());
            Logger.debug("Ground Sapling Block = %s", saplingBlock.getBlock().getLocalizedName());
            Logger.debug("Ground Sapling Meta = %d", saplingMeta);

            if (biomeTrees.size() > 0) {

                // First, let's get all of the trees in this biome that match the sapling on the ground.
                ArrayList<TreeRTG> validTrees = new ArrayList<>();

                for (int i = 0; i < biomeTrees.size(); i++) {

                    Logger.debug("Biome Tree #%d = %s", i, biomeTrees.get(i).getClass().getName());
                    Logger.debug("Biome Tree #%d Sapling Block = %s", i, biomeTrees.get(i).getSaplingBlock().getBlock().getLocalizedName());
                    Logger.debug("Biome Tree #%d Sapling Meta = %d", i, SaplingUtil.getMetaFromState(biomeTrees.get(i).getSaplingBlock()));

                    if (saplingBlock.getBlock() == biomeTrees.get(i).getSaplingBlock().getBlock()) {

                        if (SaplingUtil.getMetaFromState(saplingBlock) == SaplingUtil.getMetaFromState(biomeTrees.get(i).getSaplingBlock())) {

                            validTrees.add(biomeTrees.get(i));
                            Logger.debug("Valid tree found!");
                        }
                    }
                }

                // If there are valid trees, then proceed; otherwise, let's get out here.
                if (validTrees.size() > 0) {

                    // Prevent the original tree from generating.
                    event.setResult(Event.Result.DENY);

                    // Get a random tree from the list of valid trees.
                    TreeRTG tree = validTrees.get(event.getRand().nextInt(validTrees.size()));

                    Logger.debug("Tree = %s", tree.getClass().getName());

                    // Set the trunk size if min/max values have been set.
                    if (tree.getMinTrunkSize() > 0 && tree.getMaxTrunkSize() > tree.getMinTrunkSize()) {

                        tree.setTrunkSize(RandomUtil.getRandomInt(event.getRand(), tree.getMinTrunkSize(), tree.getMaxTrunkSize()));
                    }

                    // Set the crown size if min/max values have been set.
                    if (tree.getMinCrownSize() > 0 && tree.getMaxCrownSize() > tree.getMinCrownSize()) {

                        tree.setCrownSize(RandomUtil.getRandomInt(event.getRand(), tree.getMinCrownSize(), tree.getMaxCrownSize()));
                    }

                    int treeHeight = tree.getTrunkSize() + tree.getCrownSize();
                    if (treeHeight < 1) {
                        Logger.debug("Unable to grow RTG tree with no height.");
                        return;
                    }

                    if (!tree.hasSpaceToGrow(event.getWorld(), event.getRand(), event.getPos(), treeHeight)) {
                        Logger.debug("Unable to grow RTG tree with %d height. Something in the way.", treeHeight);
                        return;
                    }

                    /*
                     * Set the generateFlag to what it needs to be for growing trees from saplings,
                     * generate the tree, and then set it back to what it was before.
                     *
                     * TODO: Does this affect the generation of normal RTG trees? - Pink
                     */
                    int oldFlag = tree.getGenerateFlag();
                    tree.setGenerateFlag(3);
                    boolean generated = tree.generate(event.getWorld(), event.getRand(), event.getPos());
                    tree.setGenerateFlag(oldFlag);

                    if (generated) {

                        // Sometimes we have to remove the sapling manually because some trees grow around it, leaving the original sapling.
                        if (event.getWorld().getBlockState(event.getPos()) == saplingBlock) {
                            event.getWorld().setBlockState(event.getPos(), Blocks.AIR.getDefaultState(), 2);
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

    public class WorldEventRTG {

        WorldEventRTG() {

            logEventMessage("Initialising WorldEventRTG...");
        }

        @SubscribeEvent
        public void onWorldLoad(WorldEvent.Load event) {

            // This event fires for each dimension loaded (and then one last time in which it returns 0?),
            // so initialise a field to 0 and set it to the world seed and only display it in the log once.
            if (worldSeed != event.getWorld().getSeed() && event.getWorld().getSeed() != 0) {

                worldSeed = event.getWorld().getSeed();
                Logger.info("World Seed: " + worldSeed);
            }
        }

        @SubscribeEvent
        public void onWorldUnload(WorldEvent.Unload event) {

            // Reset the world seed so that it logs on the next server start if the seed is the same as the last load.
            worldSeed = 0;
        }
    }

    public class DecorateBiomeEventRTG {

        DecorateBiomeEventRTG() {

            logEventMessage("Initialising DecorateBiomeEventRTG...");
        }

        @SubscribeEvent
        public void onBiomeDecorate(DecorateBiomeEvent.Decorate event) {

            // Are flowing liquid modifications enabled?
            // Note: This will need to move to the switch statement below if we add more case statements.
            if (!rtgConfig.ENABLE_FLOWING_LIQUID_MODIFICATIONS.get()) {
                return;
            }

            // Are we in an RTG world? Do we have RTG's chunk manager?
            if (!(DimensionManagerRTG.isValidDimension(event.getWorld().provider.getDimension())) ||
                !(event.getWorld().getBiomeProvider() instanceof BiomeProviderRTG)) {
                return;
            }

            DecorateBiomeEvent.Decorate.EventType eventType = event.getType();

            // No switch statements allowed! - Pink

            /*
             * Vanilla generates flowing liquids during biome decoration,
             * so we're going to cancel that event here and generate them via rPopulatePostDecorate().
             */
            if (eventType == LAKE_WATER || eventType == LAKE_LAVA) {
                event.setResult(Event.Result.DENY);
            }
        }
    }

    public class SurfaceEventRTG {

        private final ModPresenceTester undergroundBiomesMod = new ModPresenceTester("undergroundbiomes");

        // Create UBColumnCache only if UB is present
        private final UBColumnCache ubColumnCache = undergroundBiomesMod.present() ? new UBColumnCache() : null;

        SurfaceEventRTG() {

            logEventMessage("Initialising SurfaceEventRTG...");
        }

        @SubscribeEvent
        public void onHardcodedCobble(SurfaceEvent.HardcodedBlock event) {

            if ((undergroundBiomesMod.present())) {
                event.setBlock(ubColumnCache.column(event.getWorldX(), event.getWorldZ()).cobblestone(event.getWorldY()));
            }
        }

        @SubscribeEvent
        public void onShadowStone(SurfaceEvent.HardcodedBlock event) {

            if ((undergroundBiomesMod.present()) && rtgConfig.ENABLE_UBC_STONE_SHADOWING.get()) {
                event.setBlock(Blocks.STONE.getDefaultState());
            }
        }

        @SubscribeEvent
        public void onShadowDesert(SurfaceEvent.HardcodedBlock event) {

            if ((undergroundBiomesMod.present()) && rtgConfig.ENABLE_UBC_DESERT_SHADOWING.get()) {
                event.setBlock(Blocks.STONE.getDefaultState());
            }
        }

        @SubscribeEvent
        public void onBoulderBlock(SurfaceEvent.BoulderBlock event) {

            IBlockState defaultBlock = event.getBlock();

            if (defaultBlock == Blocks.COBBLESTONE.getDefaultState()) {

                if (undergroundBiomesMod.present() && rtgConfig.ENABLE_UBC_BOULDERS.get()) {

                    event.setBlock(ubColumnCache.column(event.getWorldX(), event.getWorldZ()).cobblestone(event.getWorldY()));
                }
            }
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
        MinecraftForge.TERRAIN_GEN_BUS.register(SAPLING_GROW_TREE_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(DECORATE_BIOME_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(SURFACE_EVENT_HANDLER);

        logEventMessage("RTG's event handlers have been registered successfully.");
    }

    private static void logEventMessage(String message) {

        Logger.debug("RTG Event System: " + message);
    }

    public void setDimensionChunkLoadEvent(int dimension, Acceptor<ChunkEvent.Load> action) {

        chunkLoadEvents.put(dimension, action);
    }
}