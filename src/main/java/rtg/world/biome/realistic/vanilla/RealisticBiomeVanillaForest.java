package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.world.biome.deco.collection.DecoCollectionForest;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.FOREST.topBlock;
    public static IBlockState fillerBlock = Biomes.FOREST.fillerBlock;

    public RealisticBiomeVanillaForest(BiomeConfig config) {

        super(config,
            Biomes.FOREST,
            Biomes.RIVER,
            new TerrainVanillaForest(),
            new SurfaceVanillaForest(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getStateFromMeta(2), 0.10f)
        );

        this.addDecoCollection(new DecoCollectionForest(this.config._boolean(BiomeConfigVanillaForest.decorationLogsId)));
    }
}
