package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLFlyingMountains;
import rtg.world.gen.terrain.highlands.TerrainHLFlyingMountains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLFlyingMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.flyingMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLFlyingMountains()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLFlyingMountains(230f, 100f, 0f),
            new SurfaceHLFlyingMountains(topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f));
        
        this.setRealisticBiomeName("HL Flying Mountains");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_flyingMountains;
        this.generateVillages = ConfigHL.villageHL_flyingMountains;
    }
}
