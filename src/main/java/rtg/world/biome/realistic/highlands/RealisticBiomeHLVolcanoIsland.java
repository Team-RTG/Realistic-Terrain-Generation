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
    
    private static Block bopTopBlock = Blocks.grass;
    private static byte bopTopByte = (byte)0;
    private static Block bopFillBlock = Blocks.dirt;
    private static byte bopFillByte = (byte)0;
    private static Block bopMixTopBlock = Blocks.stone;
    private static byte bopMixTopByte = (byte)0;
    private static Block bopMixFillBlock = Blocks.dirt;
    private static byte bopMixFillByte = (byte)0;

    public RealisticBiomeHLVolcanoIsland(BiomeConfig config)
    {
    
        super(config, 
            hlBiome, BiomeGenBase.river,
            new TerrainHLVolcanoIsland(),
            new SurfaceHLVolcanoIsland(config, 
                bopTopBlock, //Block top 
                bopTopByte, //byte topByte
                bopFillBlock, //Block filler, 
                bopFillByte, //byte fillerByte
                bopMixTopBlock, //Block mixTop, 
                bopMixTopByte, //byte mixTopByte, 
                bopMixFillBlock, //Block mixFill, 
                bopMixFillByte, //byte mixFillByte, 
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
        this.hasVolcanoes = true;

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
        noLakes = true;
        noWaterFeatures = true;
    }
}
