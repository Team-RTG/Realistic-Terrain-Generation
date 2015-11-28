package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLCanyon;
import rtg.world.gen.terrain.highlands.TerrainHLCanyon;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLCanyon extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.canyon;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLCanyon()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.OASIS),
            new TerrainHLCanyon(true, 35f, 160f, 60f, 40f, 69f),
            new SurfaceHLCanyon(topBlock, fillerBlock, (byte) 0, 0));
        
        this.setRealisticBiomeName("HL Canyon");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLCanyon;
        this.generateVillages = ConfigHL.villageHLCanyon;
    }
}
