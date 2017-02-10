package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavanna;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.biome.deco.collection.DecoCollectionSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavanna;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavanna;

public class RealisticBiomeVanillaSavanna extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = BiomeGenBase.savanna.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.savanna.fillerBlock;

    public RealisticBiomeVanillaSavanna(BiomeConfig config) {

        super(config,
            BiomeGenBase.savanna,
            BiomeGenBase.river,
            new TerrainVanillaSavanna(),
            new SurfaceVanillaSavanna(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), Blocks.grass.getDefaultState(), 13f, 0.27f)
        );

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna(this.config._boolean(BiomeConfigVanillaSavanna.decorationLogsId)));
    }
}
