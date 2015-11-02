package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLVolcanoIsland;
import rtg.world.gen.terrain.highlands.TerrainHLVolcanoIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLVolcanoIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.volcanoIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLVolcanoIsland()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLVolcanoIsland(),
            new SurfaceHLVolcanoIsland(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Volcano Island");
        this.biomeCategory = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_volcanoIsland;
    }
}
