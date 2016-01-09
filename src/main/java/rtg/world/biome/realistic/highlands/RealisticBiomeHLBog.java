package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLBog;
import rtg.world.gen.terrain.highlands.TerrainHLBog;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBog extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.bog;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLBog()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainHLBog(),
            new SurfaceHLBog(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Bog");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLBog;
        this.generateVillages = ConfigHL.villageHLBog;
    }
}
