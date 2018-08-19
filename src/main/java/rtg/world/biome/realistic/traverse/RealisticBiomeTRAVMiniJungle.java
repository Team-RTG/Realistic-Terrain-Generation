package rtg.world.biome.realistic.traverse;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;


public class RealisticBiomeTRAVMiniJungle extends RealisticBiomeTRAVBase {

    public RealisticBiomeTRAVMiniJungle(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaJungle();
    }

    public class TerrainVanillaJungle extends TerrainBase {

        public TerrainVanillaJungle() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {

            return terrainFlatLakes(x, y, rtgWorld, river, 66f);
        }
    }
}
