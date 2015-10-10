package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLDesertMountains;
import rtg.world.gen.terrain.highlands.TerrainHLDesertMountains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLDesertMountains extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.desertMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLDesertMountains()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLDesertMountains(230f, 100f, 0f),
            new SurfaceHLDesertMountains(topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f));
        
        this.setRealisticBiomeName("HL Desert Mountains");
        this.biomeCategory = BiomeCategory.HOT;
        this.biomeWeight = ConfigHL.weightHL_desertMountains;
    }
}
