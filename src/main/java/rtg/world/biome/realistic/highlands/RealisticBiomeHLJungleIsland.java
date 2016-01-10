package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biomes.highlands.config.BiomeConfigHLJungleIsland;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLJungleIsland;
import rtg.world.gen.terrain.highlands.TerrainHLJungleIsland;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLJungleIsland extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.jungleIsland;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLJungleIsland()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.WET),
            new TerrainHLJungleIsland(0f, 140f, 68f, 200f),
            new SurfaceHLJungleIsland(topBlock, fillerBlock));
        
        this.biomeConfig = new BiomeConfigHLJungleIsland();
        this.biomeWeight = ConfigHL.weightHLJungleIsland;
        this.generateVillages = ConfigHL.villageHLJungleIsland;
    }
}
