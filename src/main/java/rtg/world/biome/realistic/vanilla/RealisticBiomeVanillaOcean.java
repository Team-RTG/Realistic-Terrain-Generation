package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.gen.surface.vanilla.SurfaceVanillaOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaOcean;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.ocean.topBlock.getBlock();
    public static Block fillerBlock = BiomeGenBase.ocean.fillerBlock.getBlock();
    
    public RealisticBiomeVanillaOcean(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.ocean,
            BiomeGenBase.river,
            new TerrainVanillaOcean(),
            new SurfaceVanillaOcean(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.config = config;
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
