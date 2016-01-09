package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import rtg.config.biomesoplenty.ConfigBOP;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.WorldGenVolcano;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.biomesoplenty.SurfaceBOPVolcano;
import rtg.world.gen.terrain.biomesoplenty.TerrainBOPVolcano;
import biomesoplenty.api.content.BOPCBiomes;
import cpw.mods.fml.common.registry.GameData;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

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
    
    private static SurfaceBase surface = new SurfaceBOPVolcano(
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
    );
    
    private static SurfaceBase riverSurface = new SurfaceRiverOasis();
    
	public RealisticBiomeBOPVolcano()
	{
		super(
			bopBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainBOPVolcano(),
			surface
		);
		
		this.setRealisticBiomeName("BOP Volcano");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigBOP.weightBOPVolcano;
		this.generateVillages = ConfigBOP.villageBOPVolcano;
		
		this.waterSurfaceLakeChance = 0;
		this.lavaSurfaceLakeChance = 1;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        for (int l14 = 0; l14 < 15; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(3) == 0)
            {
                (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
            }
            else
            {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
            }
        }
    }
    
    @Override
    public void rMapGen(Block[] blocks, byte[] metadata, World world, WorldChunkManagerRTG cmr, Random mapRand, int baseX, int baseY,
        int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float noise[])
    {
    
        if (baseX % 4 == 0 && baseY % 4 == 0 && mapRand.nextInt(6) == 0)
        {
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
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        surface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        riverSurface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
