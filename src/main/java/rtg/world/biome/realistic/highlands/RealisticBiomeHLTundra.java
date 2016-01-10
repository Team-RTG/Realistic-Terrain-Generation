package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biomes.highlands.config.BiomeConfigHLTundra;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLTundra;
import rtg.world.gen.terrain.highlands.TerrainHLTundra;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLTundra extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.tundra;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLTundra()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE),
            new TerrainHLTundra(),
            new SurfaceHLTundra(topBlock, fillerBlock));
        
        this.biomeConfig = new BiomeConfigHLTundra();
        this.biomeWeight = ConfigHL.weightHLTundra;
        this.generateVillages = ConfigHL.villageHLTundra;
    }
}
