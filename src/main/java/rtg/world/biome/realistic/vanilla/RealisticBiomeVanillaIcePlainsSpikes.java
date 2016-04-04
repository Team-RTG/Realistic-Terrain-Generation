package rtg.world.biome.realistic.vanilla;

import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.icePlains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public RealisticBiomeVanillaIcePlainsSpikes() {
        super(
                mutationBiome,
                Biomes.frozenRiver
        );
        this.noLakes = true;
    }

    @Override
        protected SurfaceBase initSurface() {
        return new SurfaceVanillaIcePlainsSpikes(this);
    }

    @Override
    protected TerrainBase initTerrain() {
        return new TerrainBase() {
            @Override
            public float generateNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
                return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 200f, 65f);
            }
        };
    }

    @Override
    protected void initProperties()
    {

    }

    @Override
    protected void initDecos()
    {

    }
}
