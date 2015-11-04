package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLAlps;
import rtg.world.gen.terrain.highlands.TerrainHLAlps;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLAlps extends RealisticBiomeHLBase {
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.alps;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLAlps() {
    
        super(hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.frozenRiver, Climate.ICE), new TerrainHLAlps(),
            new SurfaceHLAlps(topBlock, fillerBlock, false, null, 0.45f));
        
        this.setRealisticBiomeName("HL Alps");
        this.biomeSize = BiomeSize.NORMAL;
        this.biomeWeight = ConfigHL.weightHL_alps;
    }
}
