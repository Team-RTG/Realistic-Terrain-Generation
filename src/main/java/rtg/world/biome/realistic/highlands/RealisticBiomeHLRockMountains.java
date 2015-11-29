package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLRockMountains;
import rtg.world.gen.terrain.highlands.TerrainHLRockMountains;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLRockMountains extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.rockMountains;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLRockMountains()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLRockMountains(230f, 100f, 0f),
            new SurfaceHLRockMountains(topBlock, fillerBlock, false, null, 0f, 1.5f, 60f, 65f, 1.5f));
        
        this.setRealisticBiomeName("HL Rock Mountains");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHLRockMountains;
        this.generateVillages = ConfigHL.villageHLRockMountains;
    }
}
