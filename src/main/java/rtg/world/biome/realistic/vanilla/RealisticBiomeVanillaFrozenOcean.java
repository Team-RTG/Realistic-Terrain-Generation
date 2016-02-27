package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase
{
    
    public static  IBlockState topBlock = BiomeGenBase.frozenOcean.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.frozenOcean.fillerBlock;
    
    public RealisticBiomeVanillaFrozenOcean(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.frozenOcean,
            BiomeGenBase.river,
            new TerrainVanillaFrozenOcean(),
            new SurfaceVanillaFrozenOcean(config, Blocks.sand.getDefaultState(), Blocks.sand.getDefaultState(), Blocks.gravel.getDefaultState(), 20f, 0.2f));
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
