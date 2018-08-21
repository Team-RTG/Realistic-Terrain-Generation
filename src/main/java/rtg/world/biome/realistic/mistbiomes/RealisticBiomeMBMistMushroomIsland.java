package rtg.world.biome.realistic.mistbiomes;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeMBMistMushroomIsland extends RealisticBiomeMBBase {

    public RealisticBiomeMBMistMushroomIsland(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaMushroomIsland();
    }

    public class TerrainVanillaMushroomIsland extends TerrainBase {

        private float heigth;
        private float width;

        public TerrainVanillaMushroomIsland() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainGrasslandFlats(x, y, rtgWorld, river, 40f, 68f);
        }
    }
}
