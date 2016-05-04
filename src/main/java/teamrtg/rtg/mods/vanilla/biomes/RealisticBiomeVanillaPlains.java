package teamrtg.rtg.mods.vanilla.biomes;

import net.minecraft.init.Biomes;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.biome.terrain.GroundEffect;
import teamrtg.rtg.world.biome.terrain.TerrainBase;
import teamrtg.rtg.world.gen.ChunkProviderRTG;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase {

    public RealisticBiomeVanillaPlains(ChunkProviderRTG chunkProvider) {

        super(
                Biomes.PLAINS,
                Biomes.RIVER,
                chunkProvider
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
    protected void initDecos() {

    }

    @Override
    protected void initProperties() {
        config.FILL_LAYERS.setDefault(3);
    }
}
