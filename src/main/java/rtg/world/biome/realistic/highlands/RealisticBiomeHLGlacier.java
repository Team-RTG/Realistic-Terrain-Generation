package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biomes.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLGlacier;
import rtg.world.gen.terrain.highlands.TerrainHLGlacier;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLGlacier extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.glacier;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLGlacier(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
            new TerrainHLGlacier(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLGlacier(topBlock, fillerBlock, false, null, 0.95f));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigHL.weightHLGlacier;
        this.generateVillages = ConfigHL.villageHLGlacier;
    }
}
