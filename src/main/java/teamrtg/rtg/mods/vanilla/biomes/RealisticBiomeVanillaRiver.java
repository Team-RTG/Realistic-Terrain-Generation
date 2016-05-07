package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.surface.part.SurfacePart;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;
import teamrtg.rtg.world.gen.deco.DecoBaseBiomeDecorations;

public class RealisticBiomeVanillaRiver extends RealisticBiomeVanillaBase {
    public static BiomeGenBase vanillaBiome = Biomes.RIVER;

    public RealisticBiomeVanillaRiver(ChunkProviderRTG chunkProvider) {
        super(
                vanillaBiome,
                Biomes.RIVER,
                chunkProvider
        );
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainFlatLakes(x, y, simplex, river, 3f, 60f);
            }
        };
    }

    @Override
    protected SurfacePart initSurface() {
        SurfacePart surface = PARTS.surfaceGeneric();
        return surface;
    }


    @Override
    protected void initDecos() {
		DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
		this.addDeco(decoBaseBiomeDecorations);
    }

    @Override
    protected void initProperties() {
        this.config.WATER_POND_CHANCE.setDefault(0);
        this.config.LAVA_POND_CHANCE.setDefault(0);
        this.config.SURFACE_BLEED_OUT.setDefault(false);
    }
}
