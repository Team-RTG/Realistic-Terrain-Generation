package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.world.biome.surface.SurfaceBase;
import teamrtg.rtg.mods.vanilla.surfaces.SurfaceVanillaSunflowerPlains;
import teamrtg.rtg.world.biome.terrain.GroundEffect;
import teamrtg.rtg.world.biome.terrain.TerrainBase;

public class RealisticBiomeVanillaSunflowerPlains extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.PLAINS;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaSunflowerPlains() {
        super(
                mutationBiome,
                Biomes.RIVER
        );
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            private final GroundEffect groundEffect = new GroundEffect(4f);

            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return riverized(65f + groundEffect.added(simplex, cell, x, y), river);
            }
        };
    }

    @Override
    protected SurfaceBase initSurface() {
        return new SurfaceVanillaSunflowerPlains(this);
    }

    @Override
    protected void initDecos() {

    }

    @Override
    protected void initProperties() {

    }
}
