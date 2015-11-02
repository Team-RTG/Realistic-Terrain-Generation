package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRedDesert;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRedDesert;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBRedDesert extends RealisticBiomeEBBase
{
    
    public RealisticBiomeEBRedDesert(BiomeGenBase ebBiome)
    {
    
        super(
            ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
            new TerrainEBRedDesert(),
            new SurfaceEBRedDesert());
        
        this.setRealisticBiomeName("EB Red Desert");
        this.biomeCategory = BiomeCategory.NORMAL;
        this.biomeWeight = ConfigEB.weightEBRedDesert;
    }
}
