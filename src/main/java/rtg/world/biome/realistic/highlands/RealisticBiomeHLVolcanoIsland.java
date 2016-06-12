package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.gen.surface.highlands.SurfaceHLVolcanoIsland;
import rtg.world.gen.terrain.highlands.TerrainHLVolcanoIsland;

public class RealisticBiomeHLVolcanoIsland extends RealisticBiomeHLBase
{
    public static BiomeGenBase hlBiome = HighlandsBiomes.volcanoIsland;
    
    private static Block hlTopBlock = Blocks.grass;
    private static byte hlTopByte = (byte)0;
    private static Block hlFillBlock = Blocks.dirt;
    private static byte hlFillByte = (byte)0;
    private static Block hlMixTopBlock = Blocks.stone;
    private static byte hlMixTopByte = (byte)0;
    private static Block hlMixFillBlock = Blocks.dirt;
    private static byte hlMixFillByte = (byte)0;

    public RealisticBiomeHLVolcanoIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLVolcanoIsland(),
            new SurfaceHLVolcanoIsland(config, 
                hlTopBlock, //Block top 
                hlTopByte, //byte topByte
                hlFillBlock, //Block filler, 
                hlFillByte, //byte fillerByte
                hlMixTopBlock, //Block mixTop, 
                hlMixTopByte, //byte mixTopByte, 
                hlMixFillBlock, //Block mixFill, 
                hlMixFillByte, //byte mixFillByte, 
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
        );
		this.waterSurfaceLakeChance = 0;
		this.lavaSurfaceLakeChance = 1;
		this.noLakes = true;
		this.noWaterFeatures = true;

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
    }
}
