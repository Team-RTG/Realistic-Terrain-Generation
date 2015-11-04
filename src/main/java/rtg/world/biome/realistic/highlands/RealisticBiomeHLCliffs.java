package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLCliffs;
import rtg.world.gen.terrain.highlands.TerrainHLCliffs;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLCliffs extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.cliffs;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLCliffs()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
            new TerrainHLCliffs(230f, 120f, 0f),
            new SurfaceHLCliffs(topBlock, fillerBlock, false, null, 0.95f));
        
        this.setRealisticBiomeName("HL Cliffs");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_cliffs;
    }
}
