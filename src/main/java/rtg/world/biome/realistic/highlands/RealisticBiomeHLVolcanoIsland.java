package rtg.world.biome.realistic.highlands;

import highlands.api.HighlandsBiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.gen.feature.WorldGenVolcano;
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

        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
    }
    @Override
    public float rNoise(OpenSimplexNoise simplex, CellNoise cell, int x, int y, float border, float river) {
        // no rivers or lakes
        return terrain.generateNoise(simplex, cell, x, y, border, river);
    }
    
    @Override
    public void rMapGen(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY,
        int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[])
    {
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(6) == 0)
        {
            if(!(((WorldChunkManagerRTG) cmr).getBiomeGenAt(baseX*16, baseY*16) instanceof RealisticBiomeHLVolcanoIsland))
            {
                return;
            }
            float river = cmr.getRiverStrength(baseX * 16, baseY * 16) + 1f;
            if (river > 0.98f && cmr.isBorderlessAt(baseX * 16, baseY * 16))
            {
                long i1 = mapRand.nextLong() / 2L * 2L + 1L;
                long j1 = mapRand.nextLong() / 2L * 2L + 1L;
                mapRand.setSeed((long) chunkX * i1 + (long) chunkY * j1 ^ world.getSeed());
                
                WorldGenVolcano.build(blocks, metadata, world, mapRand, baseX, baseY, chunkX, chunkY, simplex, cell, noise);
            }
        }
    }
}
