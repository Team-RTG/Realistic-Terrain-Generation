package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLDunes;
import rtg.world.gen.terrain.highlands.TerrainHLDunes;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLDunes extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.dunes;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLDunes()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLDunes(),
            new SurfaceHLDunes(topBlock, fillerBlock));
        
        this.setRealisticBiomeName("HL Dunes");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLDunes;
        this.generateVillages = ConfigHL.villageHLDunes;
    }
}
