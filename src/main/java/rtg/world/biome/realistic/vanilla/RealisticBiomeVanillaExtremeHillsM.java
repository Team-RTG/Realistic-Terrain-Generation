package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsM;

public class RealisticBiomeVanillaExtremeHillsM extends RealisticBiomeVanillaBase {

    public static Biome standardBiome = Biomes.EXTREME_HILLS;
    public static Biome mutationBiome = Biome.getBiome(standardBiome.biomeID + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaExtremeHillsM(BiomeConfig config) {

        super(config,
            mutationBiome,
            Biomes.RIVER,
            new TerrainVanillaExtremeHillsM(10f, 140f, 68f, 200f),
            new SurfaceVanillaExtremeHillsM(config, topBlock, fillerBlock, Blocks.GRASS.getDefaultState(), Blocks.DIRT.getDefaultState(), 60f,
                -0.14f, 14f, 0.25f)
        );

        this.generatesEmeralds = true;
        this.noWaterFeatures = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
