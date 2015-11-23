package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLBirchHills;
import rtg.world.gen.terrain.highlands.TerrainHLBirchHills;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBirchHills extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.birchHills;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLBirchHills()
    {
    
        super(hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE), new TerrainHLBirchHills(230f, 120f, 0f),
            new SurfaceHLBirchHills(topBlock, fillerBlock, false, null, 0.95f));
        
        this.setRealisticBiomeName("HL Birch Hills");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_birchHills;
        this.generateVillages = ConfigHL.villageHL_birchHills;
    }
}
