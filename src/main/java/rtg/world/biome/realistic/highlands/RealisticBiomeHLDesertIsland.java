package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLDesertIsland;
import rtg.world.gen.terrain.highlands.TerrainHLDesertIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLDesertIsland extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.desertIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLDesertIsland(BiomeConfig config)
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLDesertIsland(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLDesertIsland(topBlock, fillerBlock));
        
        this.biomeConfig = config;
        this.biomeWeight = ConfigHL.weightHLDesertIsland;
        this.generateVillages = ConfigHL.villageHLDesertIsland;
    }
}
