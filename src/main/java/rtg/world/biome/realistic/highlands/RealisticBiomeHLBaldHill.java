package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLBaldHill;
import rtg.world.gen.terrain.highlands.TerrainHLBaldHill;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLBaldHill extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.baldHill;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLBaldHill(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
            new TerrainHLBaldHill(90f, 180f, 13f, 100f, 38f, 260f, 90f),
            new SurfaceHLBaldHill(Blocks.cobblestone, Blocks.stone)
        );
        
        this.config = config;
        this.generateVillages = ConfigHL.villageHLBaldHill;
    }
}
