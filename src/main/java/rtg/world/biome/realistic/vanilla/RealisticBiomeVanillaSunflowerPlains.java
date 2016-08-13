package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSunflowerPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSunflowerPlains;

public class RealisticBiomeVanillaSunflowerPlains extends RealisticBiomeVanillaBase {

    public static Biome standardBiome = Biome.plains;
    public static Biome mutationBiome = Biome.getBiome(standardBiome.biomeID + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaSunflowerPlains(BiomeConfig config) {

        super(config,
            mutationBiome,
            Biome.river,
            new TerrainVanillaSunflowerPlains(),
            new SurfaceVanillaSunflowerPlains(config, topBlock, fillerBlock)
        );

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
