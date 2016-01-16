package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLMesa;
import rtg.world.gen.terrain.highlands.TerrainHLMesa;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLMesa extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.mesa;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLMesa(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLMesa(),
            new SurfaceHLMesa(topBlock, fillerBlock, (byte) 1));
        
        this.config = config;
        this.biomeWeight = ConfigHL.weightHLMesa;
        this.generateVillages = ConfigHL.villageHLMesa;
    }
}
