package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaSunflowerPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaSunflowerPlains;

public class RealisticBiomeVanillaSunflowerPlains extends RealisticBiomeVanillaBase {
    public static BiomeGenBase standardBiome = Biomes.plains;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);

    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaSunflowerPlains() {
        super(
                mutationBiome,
                Biomes.river,
                new TerrainVanillaSunflowerPlains(),
                new SurfaceVanillaSunflowerPlains(config, topBlock, fillerBlock));
    }
}
