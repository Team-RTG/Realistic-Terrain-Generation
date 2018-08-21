package rtg.world.biome.realistic.biomesyougo;

import net.minecraft.world.biome.Biome;
import rtg.api.world.RTGWorld;
import rtg.api.world.terrain.TerrainBase;
import rtg.api.world.terrain.heighteffect.GroundEffect;


public class RealisticBiomeBYGPaperBirchForest extends RealisticBiomeBYGBase {

    public RealisticBiomeBYGPaperBirchForest(Biome biome) {

        super(biome);
    }

    @Override
    public void initConfig() {

    }

    @Override
    public TerrainBase initTerrain() {

        return new TerrainVanillaBirchForest();
    }

    public class TerrainVanillaBirchForest extends TerrainBase {


        private GroundEffect groundEffect = new GroundEffect(4f);

        public TerrainVanillaBirchForest() {

        }

        @Override
        public float generateNoise(RTGWorld rtgWorld, int x, int y, float border, float river) {
            //return terrainPlains(x, y, simplex, river, 160f, 10f, 60f, 80f, 65f);
            return riverized(65f + groundEffect.added(rtgWorld, x, y), river);
        }
    }
}
