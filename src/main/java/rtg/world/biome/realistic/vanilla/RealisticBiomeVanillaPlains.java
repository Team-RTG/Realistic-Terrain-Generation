package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.gen.surface.vanilla.SurfaceVanillaPlains;
import rtg.world.gen.terrain.vanilla.TerrainVanillaPlains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaPlains extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.plains.topBlock.getBlock();
    public static Block fillerBlock = BiomeGenBase.plains.fillerBlock.getBlock();
    
    public RealisticBiomeVanillaPlains(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.plains,
            BiomeGenBase.river,
            new TerrainVanillaPlains(),
            new SurfaceVanillaPlains(topBlock, fillerBlock));
        
        this.config = config;
    }
}
