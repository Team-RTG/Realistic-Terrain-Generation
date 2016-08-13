package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;

import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaSavannaM;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.biome.deco.collection.DecoCollectionSavanna;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSavannaM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSavannaM;

public class RealisticBiomeVanillaSavannaM extends RealisticBiomeVanillaBase {

    public static Biome standardBiome = Biome.savanna;
    public static Biome mutationBiome = Biome.getBiome(standardBiome.biomeID + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaSavannaM(BiomeConfig config) {

        super(config,
            mutationBiome,
            Biome.river,
            new TerrainVanillaSavannaM(),
            new SurfaceVanillaSavannaM(config, topBlock, fillerBlock)
        );

        this.noLakes = true;

        this.addDecoCollection(new DecoCollectionDesertRiver());
        this.addDecoCollection(new DecoCollectionSavanna(this.config._boolean(BiomeConfigVanillaSavannaM.decorationLogsId)));
    }
}
