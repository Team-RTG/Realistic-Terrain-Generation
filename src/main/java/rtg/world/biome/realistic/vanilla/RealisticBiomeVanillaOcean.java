package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.vanilla.SurfaceVanillaOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaOcean;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.ocean.topBlock;
    public static Block fillerBlock = BiomeGenBase.ocean.fillerBlock;
    
    public RealisticBiomeVanillaOcean(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.ocean,
            BiomeGenBase.river,
            new TerrainVanillaOcean(),
            new SurfaceVanillaOcean(config, Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
        this.noLakes=true;
    }
}
