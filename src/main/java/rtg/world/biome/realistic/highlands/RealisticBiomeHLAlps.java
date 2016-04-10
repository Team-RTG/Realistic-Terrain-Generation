package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.gen.surface.highlands.SurfaceHLAlps;
import rtg.world.gen.terrain.highlands.TerrainHLAlps;

public class RealisticBiomeHLAlps extends RealisticBiomeHLBase {
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.alps;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLAlps(BiomeConfig config) {
    
        super(config, hlBiome, BiomeGenBase.frozenRiver, new TerrainHLAlps(),
            new SurfaceHLAlps(config, topBlock, fillerBlock, false, null, 0.45f));
    }
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // no rivers or lakes
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }
}
