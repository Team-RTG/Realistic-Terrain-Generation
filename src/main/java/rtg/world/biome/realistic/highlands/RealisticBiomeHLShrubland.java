package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLShrubland;
import rtg.world.gen.terrain.highlands.TerrainHLShrubland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLShrubland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.shrubland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLShrubland()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLShrubland(10f, 20f, 7f, 90f, 10f, 200f, 68f),
            new SurfaceHLShrubland(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Shrubland");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLShrubland;
        this.generateVillages = ConfigHL.villageHLShrubland;
    }
}
