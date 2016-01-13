package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLOasis;
import rtg.world.gen.terrain.highlands.TerrainHLOasis;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLOasis extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.oasis;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLOasis(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
            new TerrainHLOasis(),
            new SurfaceHLOasis(Blocks.sand, fillerBlock));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigHL.weightHLOasis;
        this.generateVillages = ConfigHL.villageHLOasis;
    }
}
