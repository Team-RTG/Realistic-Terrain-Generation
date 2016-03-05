package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase
{
    
    public static IBlockState topBlock = BiomeGenBase.plains.topBlock;
    public static IBlockState fillerBlock = BiomeGenBase.plains.fillerBlock;
    
    public RealisticBiomeVanillaPlains(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.plains,
            BiomeGenBase.river,
            new TerrainVanillaPlains(),
            new SurfaceVanillaPlains(config, topBlock, fillerBlock));
    }
}
