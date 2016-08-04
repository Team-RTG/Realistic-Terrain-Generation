package rtg.event;

import net.minecraft.server.MinecraftServer;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.InitMapGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.WorldTypeEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

import rtg.config.rtg.ConfigRTG;
import rtg.util.Logger;
import rtg.world.WorldTypeRTG;
import rtg.world.gen.MapGenCavesRTG;
import rtg.world.gen.MapGenRavineRTG;
import rtg.world.gen.genlayer.RiverRemover;
import rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import rtg.world.gen.structure.MapGenVillageRTG;
import rtg.world.gen.structure.StructureOceanMonumentRTG;


public class EventManagerRTG
{
    // Event handlers.
    private final WorldEventRTG WORLD_EVENT_HANDLER = new WorldEventRTG();
    private final GenerateMinableRTG GENERATE_MINABLE_EVENT_HANDLER = new GenerateMinableRTG();
    private final InitBiomeGensRTG INIT_BIOME_GENS_EVENT_HANDLER = new InitBiomeGensRTG();
    private final InitMapGenRTG INIT_MAP_GEN_EVENT_HANDLER = new InitMapGenRTG();
    private final DecorateBiomeEventRTG DECORATE_BIOME_EVENT_HANDLER = new DecorateBiomeEventRTG();

    private long worldSeed;
    private boolean isWorldTypeRTG = true;

    public EventManagerRTG() {

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
                    if (!ConfigRTG.generateOreCoal) { event.setResult(Event.Result.DENY); }
                    return;

                case IRON:
                    if (!ConfigRTG.generateOreIron) { event.setResult(Event.Result.DENY); }
                    return;

                case REDSTONE:
                    if (!ConfigRTG.generateOreRedstone) { event.setResult(Event.Result.DENY); }
                    return;

                case GOLD:
                    if (!ConfigRTG.generateOreGold) { event.setResult(Event.Result.DENY); }
                    return;

                case LAPIS:
                    if (!ConfigRTG.generateOreLapis) { event.setResult(Event.Result.DENY); }
                    return;

                case DIAMOND:
                    if (!ConfigRTG.generateOreDiamond) { event.setResult(Event.Result.DENY); }
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

                case OCEAN_MONUMENT:
                    event.newGen = new StructureOceanMonumentRTG();
                    break;

                default:
                    break;
            }

            Logger.debug("event newGen = %s", event.newGen.toString());
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
        MinecraftForge.ORE_GEN_BUS.register(GENERATE_MINABLE_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(INIT_BIOME_GENS_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(INIT_MAP_GEN_EVENT_HANDLER);
        MinecraftForge.TERRAIN_GEN_BUS.register(DECORATE_BIOME_EVENT_HANDLER);

        logEventMessage("RTG's event handlers have been registered successfully.");
    }

    private static void logEventMessage(String message) {
        Logger.debug("RTG Event System: " + message);
    }
}