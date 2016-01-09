package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLSteppe;
import rtg.world.gen.terrain.highlands.TerrainHLSteppe;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLSteppe extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.steppe;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLSteppe()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLSteppe(70f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLSteppe(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Steppe");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLSteppe;
        this.generateVillages = ConfigHL.villageHLSteppe;
    }
}
