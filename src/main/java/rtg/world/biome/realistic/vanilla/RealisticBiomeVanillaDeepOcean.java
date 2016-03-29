package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDeepOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDeepOcean;

public class RealisticBiomeVanillaDeepOcean extends RealisticBiomeVanillaBase
{
    
    public static  IBlockState topBlock = BiomeGenBase.deepOcean.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.deepOcean.fillerBlock;
    
    public RealisticBiomeVanillaDeepOcean(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.deepOcean,
            BiomeGenBase.river,
            new TerrainVanillaDeepOcean(),
            new SurfaceVanillaDeepOcean(config, Blocks.gravel.getDefaultState(), Blocks.gravel.getDefaultState(), Blocks.clay.getDefaultState(), 20f, 0.1f));
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes=true;
    }
}
