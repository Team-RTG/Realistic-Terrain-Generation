package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.world.biome.deco.collection.DecoCollectionForest;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biome.forest.topBlock;
    public static IBlockState fillerBlock = Biome.forest.fillerBlock;

    public RealisticBiomeVanillaForest(BiomeConfig config) {

        super(config,
            Biome.forest,
            Biome.river,
            new TerrainVanillaForest(),
            new SurfaceVanillaForest(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt.getStateFromMeta(2), 0.10f)
        );

        this.addDecoCollection(new DecoCollectionForest(this.config._boolean(BiomeConfigVanillaForest.decorationLogsId)));
    }
}
