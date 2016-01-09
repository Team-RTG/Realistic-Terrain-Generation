package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLLowlands;
import rtg.world.gen.terrain.highlands.TerrainHLLowlands;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLLowlands extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.lowlands;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLLowlands()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLLowlands(),
            new SurfaceHLLowlands(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Lowlands");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLLowlands;
        this.generateVillages = ConfigHL.villageHLLowlands;
    }
}
