package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.biome.deco.collection.DecoCollectionSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavanna;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavanna;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.SAVANNA.topBlock;
    public static IBlockState fillerBlock = Biomes.SAVANNA.fillerBlock;

    public RealisticBiomeVanillaSavanna(BiomeConfig config) {

        super(config,
            Biomes.SAVANNA,
            Biomes.RIVER,
            new TerrainVanillaSavanna(),
            new SurfaceVanillaSavanna(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), Blocks.GRASS.getDefaultState(), 13f, 0.27f)
        );

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna(this.config._boolean(BiomeConfigVanillaSavanna.decorationLogsId)));
    }
}
