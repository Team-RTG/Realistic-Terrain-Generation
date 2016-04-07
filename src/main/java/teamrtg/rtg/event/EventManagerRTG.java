package teamrtg.rtg.event;

import net.minecraft.block.BlockSandStone;
import net.minecraft.block.BlockStairs;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.*;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.EventPriority;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import teamrtg.rtg.core.RTG;
import teamrtg.rtg.api.util.debug.Logger;
import teamrtg.rtg.api.mods.Mods;
import teamrtg.rtg.world.WorldTypeRTG;
import teamrtg.rtg.world.biome.BiomeProviderRTG;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.gen.MapGenCavesRTG;
import teamrtg.rtg.world.gen.MapGenRavineRTG;
import teamrtg.rtg.world.gen.genlayer.RiverRemover;
import teamrtg.rtg.world.gen.structure.MapGenScatteredFeatureRTG;
import teamrtg.rtg.world.gen.structure.MapGenVillageRTG;
import teamrtg.rtg.world.gen.structure.StructureOceanMonumentRTG;

public class EventManagerRTG {

    public RealisticBiomeBase biome = null;

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
    public void eventListenerRTG(WorldEvent.Load event) {

        if (!(event.getWorld().getWorldInfo().getTerrainType() instanceof WorldTypeRTG)) {

            MinecraftForge.TERRAIN_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.ORE_GEN_BUS.unregister(RTG.eventMgr);
            MinecraftForge.EVENT_BUS.unregister(RTG.eventMgr);
        }
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

        if (!event.getWorld().getWorldInfo().getTerrainType().getWorldTypeName().equalsIgnoreCase("RTG")) {
            return;
        }

        if (event.getWorld().provider.getDimension() == 0) {
            Logger.info("World Seed: %d", event.getWorld().getSeed());
            Logger.info("Reloading configs");
            Mods.syncAllConfigs();
        }
    }

    @SubscribeEvent
    public void onGetVillageBlockID(BiomeEvent.GetVillageBlockID event) {
        RealisticBiomeBase biomeReal;
        if (!Mods.RTG.config.ENABLE_VILLAGE_MODIFICATIONS.get()) {
            return;
        }
        if (event.getBiome() instanceof RealisticBiomeBase) {
            biomeReal = (RealisticBiomeBase) event.getBiome();
        } else if (event.getBiome() == null && this.biome != null) {
            biomeReal = this.biome;
        } else {
            return;
        }
        if (RealisticBiomeBase.getIdForBiome(biomeReal) == RealisticBiomeBase.getIdForBiome(Mods.VANILLA.biomes.desert) ||
                RealisticBiomeBase.getIdForBiome(biomeReal) == RealisticBiomeBase.getIdForBiome(Mods.VANILLA.biomes.desertHills) ||
                RealisticBiomeBase.getIdForBiome(biomeReal) == RealisticBiomeBase.getIdForBiome(Mods.VANILLA.biomes.desert)) {
            if (event.getOriginal().getBlock() == Blocks.log || event.getOriginal().getBlock() == Blocks.log2) {
                event.setReplacement(Blocks.sandstone.getDefaultState());
            }

            if (event.getOriginal().getBlock() == Blocks.cobblestone) {
                event.setReplacement(Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.DEFAULT.getMetadata()));
            }

            if (event.getOriginal().getBlock() == Blocks.planks) {
                event.setReplacement(Blocks.sandstone.getStateFromMeta(BlockSandStone.EnumType.SMOOTH.getMetadata()));
            }

            if (event.getOriginal().getBlock() == Blocks.oak_stairs) {
                event.setReplacement(Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
            }

            if (event.getOriginal().getBlock() == Blocks.stone_stairs) {
                event.setReplacement(Blocks.sandstone_stairs.getDefaultState().withProperty(BlockStairs.FACING, event.getOriginal().getValue(BlockStairs.FACING)));
            }

            if (event.getOriginal().getBlock() == Blocks.gravel) {
                event.setReplacement(Blocks.sandstone.getDefaultState());
            }
        }
        // The event has to be cancelled in order to override the original block.
        if (event.getReplacement() != null) {
            event.setResult(Result.DENY);
        }
    }

    @SubscribeEvent
    public void preBiomeDecorate(DecorateBiomeEvent.Pre event) {

        //Are we in an RTG world? Do we have RTG's chunk manager?
        if (event.getWorld().getWorldInfo().getTerrainType() instanceof WorldTypeRTG && event.getWorld().getBiomeProvider() instanceof BiomeProviderRTG) {

            BiomeProviderRTG cmr = (BiomeProviderRTG) event.getWorld().getBiomeProvider();
            this.biome = cmr.getBiomeDataAt(event.getPos().getX(), event.getPos().getZ());
        }
    }
}