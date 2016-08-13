package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaigaHills;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaigaHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaigaHills;

public class RealisticBiomeVanillaTaigaHills extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.TAIGA_HILLS.topBlock;
    public static IBlockState fillerBlock = Biomes.TAIGA_HILLS.fillerBlock;

    public RealisticBiomeVanillaTaigaHills(BiomeConfig config) {

        super(config,
            Biomes.TAIGA_HILLS,
            Biomes.RIVER,
            new TerrainVanillaTaigaHills(),
            new SurfaceVanillaTaigaHills(config, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), true, Blocks.GRAVEL.getDefaultState(), 0.2f)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaigaHills.decorationLogsId), 10f));
    }
}
