package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForestHills;
import rtg.world.biome.deco.collection.DecoCollectionForest;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForestHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForestHills;

public class RealisticBiomeVanillaForestHills extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.FOREST_HILLS.topBlock;
    public static IBlockState fillerBlock = Biomes.FOREST_HILLS.fillerBlock;

    public RealisticBiomeVanillaForestHills(BiomeConfig config) {

        super(config,
            Biomes.FOREST_HILLS,
            Biomes.RIVER,
            new TerrainVanillaForestHills(),
            new SurfaceVanillaForestHills(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.DIRT.getStateFromMeta(2), 0.15f)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionForest(this.config._boolean(BiomeConfigVanillaForestHills.decorationLogsId)));
    }
}
