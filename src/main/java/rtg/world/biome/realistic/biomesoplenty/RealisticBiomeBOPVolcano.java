package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.RTGBiomeProvider;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPVolcano;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPVolcano;
import biomesoplenty.api.content.BOPCBiomes;
import cpw.mods.fml.common.registry.GameData;

public class RealisticBiomeBOPVolcano extends RealisticBiomeBOPBase
{	
	public static BiomeGenBase bopBiome = BOPCBiomes.volcano;
	
    private static Block bopTopBlock = GameData.getBlockRegistry().getObject("BiomesOPlenty:ashStone");
    private static byte bopTopByte = (byte)0;
    private static Block bopFillBlock = GameData.getBlockRegistry().getObject("BiomesOPlenty:ashStone");
    private static byte bopFillByte = (byte)0;
    private static Block bopMixTopBlock = GameData.getBlockRegistry().getObject("BiomesOPlenty:ash");
    private static byte bopMixTopByte = (byte)0;
    private static Block bopMixFillBlock = GameData.getBlockRegistry().getObject("BiomesOPlenty:ashStone");
    private static byte bopMixFillByte = (byte)0;
    private static Block bopCliff1Block = GameData.getBlockRegistry().getObject("BiomesOPlenty:ashStone");
    private static byte bopCliff1Byte = (byte)0;
    private static Block bopCliff2Block = GameData.getBlockRegistry().getObject("BiomesOPlenty:ash");
    private static byte bopCliff2Byte = (byte)0;

	public RealisticBiomeBOPVolcano(BiomeConfig config)
	{
		super(config, 
			bopBiome, BiomeGenBase.river,
			new TerrainBOPVolcano(),
			new SurfaceBOPVolcano(config, 
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
    public void rMapGen(Block[] blocks, byte[] metadata, World world, RTGBiomeProvider cmr, Random mapRand, int baseX, int baseY,
        int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[])
    {
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(6) == 0)
        {
            if(!(((WorldChunkManagerRTG) cmr).getBiomeGenAt(baseX*16, baseY*16) instanceof RealisticBiomeBOPVolcano))
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
