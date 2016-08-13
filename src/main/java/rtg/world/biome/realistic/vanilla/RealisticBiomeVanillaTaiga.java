package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaTaiga;
import rtg.world.biome.deco.collection.DecoCollectionTaiga;
import rtg.world.gen.surface.vanilla.SurfaceVanillaTaiga;
import rtg.world.gen.terrain.vanilla.TerrainVanillaTaiga;

public class RealisticBiomeVanillaTaiga extends RealisticBiomeVanillaBase {

    public static IBlockState topBlock = Biomes.TAIGA.topBlock;
    public static IBlockState fillerBlock = Biomes.TAIGA.fillerBlock;

    public RealisticBiomeVanillaTaiga(BiomeConfig config) {

        super(config,
            Biomes.TAIGA,
            Biomes.RIVER,
            new TerrainVanillaTaiga(),
            new SurfaceVanillaTaiga(config, topBlock, fillerBlock)
        );

        this.addDecoCollection(new DecoCollectionTaiga(this.config._boolean(BiomeConfigVanillaTaiga.decorationLogsId), 10f));
    }
}
