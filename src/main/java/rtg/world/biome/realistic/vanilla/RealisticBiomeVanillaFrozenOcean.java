package rtg.world.biome.realistic.vanilla;

import rtg.api.biome.BiomeConfig;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.gen.surface.vanilla.SurfaceVanillaFrozenOcean;
import rtg.world.gen.terrain.vanilla.TerrainVanillaFrozenOcean;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeVanillaFrozenOcean extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.frozenOcean.topBlock.getBlock();
    public static Block fillerBlock = BiomeGenBase.frozenOcean.fillerBlock.getBlock();
    
    public RealisticBiomeVanillaFrozenOcean(BiomeConfig config)
    {
    
        super(
            BiomeGenBase.frozenOcean,
            BiomeGenBase.river,
            new TerrainVanillaFrozenOcean(),
            new SurfaceVanillaFrozenOcean(Blocks.sand, Blocks.sand, Blocks.gravel, 20f, 0.2f));
        
        this.config = config;
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
}
