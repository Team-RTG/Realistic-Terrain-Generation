package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHillsPlusM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHillsPlusM;

public class RealisticBiomeVanillaExtremeHillsPlusM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = Biomes.extremeHillsPlus;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(RealisticBiomeBase.getIdForBiome(standardBiome) + MUTATION_ADDEND);
    
    public static IBlockState topBlock = mutationBiome.topBlock;
    public static IBlockState fillerBlock = mutationBiome.fillerBlock;
    
    public RealisticBiomeVanillaExtremeHillsPlusM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            Biomes.river,
            new TerrainVanillaExtremeHillsPlusM(230f, 120f, 0f),
            new SurfaceVanillaExtremeHillsPlusM(config, Blocks.grass.getDefaultState(), Blocks.dirt.getDefaultState(), false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.gravel.getStateFromMeta(2), 0.08f));
        this.generatesEmeralds = true;
        this.noLakes=true;
        this.noWaterFeatures=true;
    }
}
