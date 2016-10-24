package rtg.world.biome.realistic.biomesoplenty;

import biomesoplenty.api.biome.BOPBiomes;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import rtg.api.biome.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPBambooForest;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPBambooForest;

public class RealisticBiomeBOPBambooForest extends RealisticBiomeBOPBase {

    public static Biome biome = BOPBiomes.bamboo_forest.get();
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeBOPBambooForest(BiomeConfig config) {

        super(config, biome, river,
            new TerrainBOPBambooForest(),
            new SurfaceBOPBambooForest(config, biome.topBlock, biome.fillerBlock, 0f, 1.5f, 60f, 65f, 1.5f, BlockUtil.getStateDirt(2), 0.15f)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
