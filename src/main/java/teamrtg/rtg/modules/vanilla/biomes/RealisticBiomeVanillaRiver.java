package teamrtg.rtg.modules.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.TerrainBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;
import teamrtg.rtg.api.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.modules.vanilla.RealisticBiomeVanillaBase;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {
    public static BiomeGenBase vanillaBiome = Biomes.RIVER;

    public RealisticBiomeVanillaRiver() {
        super(
                vanillaBiome,
            Biomes.RIVER
        );
    }

    @Override
    public TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
                return terrainFlatLakes(x, y, rtgWorld.simplex, river, 3f, 60f);
            }
        };
    }

    @Override
    public SurfacePart initSurface() {
        SurfacePart surface = PARTS.surfaceGeneric();
        return surface;
    }


    @Override
    public void initDecos() {
        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    public void initConfig() {
        this.config.WATER_POND_CHANCE.setDefault(0);
        this.config.LAVA_POND_CHANCE.setDefault(0);
        this.config.SURFACE_BLEED_OUT.setDefault(false);
    }
}
