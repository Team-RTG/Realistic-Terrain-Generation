package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaIcePlainsSpikes;
import rtg.world.gen.terrain.vanilla.TerrainVanillaIcePlainsSpikes;

public class RealisticBiomeVanillaIcePlainsSpikes extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.icePlains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(BiomeGenBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaIcePlainsSpikes(BiomeConfig config) {
        super(config,
                mutationBiome,
                Biomes.frozenRiver,
                new TerrainVanillaIcePlainsSpikes(),
                new SurfaceVanillaIcePlainsSpikes(config, topBlock, fillerBlock, topBlock, topBlock)
        );
        this.noLakes = true;
    }
}
