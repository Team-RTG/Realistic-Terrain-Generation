package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.helpers.TreeGen;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSteppe;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBSteppe;

public class RealisticBiomeEBSteppe extends RealisticBiomeEBBase
{
    private static Block ebTopBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass;
    private static byte ebTopByte = EnhancedBiomesMod.useNewGrass ? (byte)6 : (byte)0;
    private static Block ebFillBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt;
    private static byte ebFillByte = EnhancedBiomesMod.useNewGrass ? (byte)6 : (byte)0;
    private static Block ebMixTopBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass;
    private static byte ebMixTopByte = EnhancedBiomesMod.useNewGrass ? (byte)6 : (byte)0;
    private static Block ebMixFillBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt;
    private static byte ebMixFillByte = EnhancedBiomesMod.useNewGrass ? (byte)6 : (byte)0;
    private static Block ebCliff1Block = Blocks.stone;
    private static byte ebCliff1Byte = (byte)0;
    private static Block ebCliff2Block = Blocks.cobblestone;
    private static byte ebCliff2Byte = (byte)0;
    
	public RealisticBiomeEBSteppe(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.COLD),
			new TerrainEBSteppe(),
			new SurfaceEBSteppe(
                ebTopBlock, //Block top 
                ebTopByte, //byte topByte
                ebFillBlock, //Block filler, 
                ebFillByte, //byte fillerByte
                ebMixTopBlock, //Block mixTop, 
                ebMixTopByte, //byte mixTopByte, 
                ebMixFillBlock, //Block mixFill, 
                ebMixFillByte, //byte mixFillByte, 
                ebCliff1Block, //Block cliff1, 
                ebCliff1Byte, //byte cliff1Byte, 
                ebCliff2Block, //Block cliff2, 
                ebCliff2Byte, //byte cliff2Byte, 
                80f, //float mixWidth, 
                -0.15f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.setRealisticBiomeName("EB Steppe");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBSteppe;
		this.generateVillages = ConfigEB.villageEBSteppe;
	}
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        //RealisticBiomeBase.rDecorateSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        for (int l14 = 0; l14 < 24; l14++)
        {
            if (rand.nextInt(12) == 0) {
                int l19 = chunkX + rand.nextInt(16) + 8;
                int k22 = rand.nextInt(128);
                int j24 = chunkY + rand.nextInt(16) + 8;
                
                int plantType = rand.nextInt(4);
                
                switch (plantType) {
                    case 0:
                        (new WorldGenGrass(Blocks.double_plant, 2)).generate(world, rand, l19, k22, j24);
                        break;
                    case 1:
                        (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
                        break;
                    case 2:
                        (new WorldGenGrass(Blocks.double_plant, 3)).generate(world, rand, l19, k22, j24);
                        break;
                    case 3:
                        (new WorldGenGrass(Blocks.tallgrass, 2)).generate(world, rand, l19, k22, j24);
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
