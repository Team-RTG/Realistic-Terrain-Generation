package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.api.biome.BiomeConfig;
import rtg.config.highlands.ConfigHL;
import rtg.world.gen.surface.highlands.SurfaceHLEstuary;
import rtg.world.gen.terrain.highlands.TerrainHLEstuary;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLEstuary extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.estuary;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLEstuary(BiomeConfig config) {
    
        super(
            hlBiome, BiomeGenBase.river,
            new TerrainHLEstuary(90f, 180f, 13f, 100f, 38f, 260f, 71f),
            new SurfaceHLEstuary(topBlock, fillerBlock));
        
        this.config = config;
    }
}
