package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLLake;
import rtg.world.gen.terrain.highlands.TerrainHLLake;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLLake extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.lake;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLLake()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainHLLake(),
            new SurfaceHLLake(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone));
        
        this.setRealisticBiomeName("HL Lake");
        this.biomeCategory = BiomeCategory.WET;
        this.biomeWeight = ConfigHL.weightHL_lake;
    }
}
