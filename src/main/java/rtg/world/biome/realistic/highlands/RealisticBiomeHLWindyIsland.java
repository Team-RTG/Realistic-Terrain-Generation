package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLWindyIsland;
import rtg.world.gen.terrain.highlands.TerrainHLWindyIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLWindyIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.windyIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLWindyIsland()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainHLWindyIsland(),
            new SurfaceHLWindyIsland(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Windy Island");
        this.biomeCategory = BiomeCategory.WET;
        this.biomeWeight = ConfigHL.weightHL_windyIsland;
    }
}
