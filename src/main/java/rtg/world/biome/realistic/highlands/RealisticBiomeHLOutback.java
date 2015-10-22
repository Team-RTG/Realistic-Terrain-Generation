package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import rtg.config.highlands.ConfigHL;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.highlands.SurfaceHLOutback;
import rtg.world.gen.terrain.highlands.TerrainHLOutback;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeHLOutback extends RealisticBiomeHLBase
{
    
    public static BiomeGenBase hlBiome = HighlandsBiomes.outback;
    
    public static Block topBlock = hlBiome.topBlock;
    public static Block fillerBlock = hlBiome.fillerBlock;
    
    public RealisticBiomeHLOutback()
    {
    
        super(
            hlBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
            new TerrainHLOutback(300f),
            new SurfaceHLOutback(topBlock, fillerBlock, Blocks.stone, Blocks.cobblestone, (byte) 0, 1));
        
        this.setRealisticBiomeName("HL Outback");
        this.biomeCategory = BiomeCategory.HOT;
        this.biomeWeight = ConfigHL.weightHL_outback;
    }
}
