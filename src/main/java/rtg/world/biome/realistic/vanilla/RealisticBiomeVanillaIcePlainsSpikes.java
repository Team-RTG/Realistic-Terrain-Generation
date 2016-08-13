package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.Biome;

import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBaseBiomeDecorations;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlainsSpikes;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase {

    public static Biome standardBiome = Biome.icePlains;
    public static Biome mutationBiome = Biome.getBiome(standardBiome.biomeID + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaIcePlainsSpikes(BiomeConfig config) {

        super(config,
            mutationBiome,
            Biome.frozenRiver,
            new TerrainVanillaIcePlainsSpikes(),
            new SurfaceVanillaIcePlainsSpikes(config, topBlock, fillerBlock, topBlock, topBlock)
        );

        this.noLakes = true;

        DecoBaseBiomeDecorations decoBaseBiomeDecorations = new DecoBaseBiomeDecorations();
        this.addDeco(decoBaseBiomeDecorations);
    }
}
