package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.gen.WorldGenSpikedBush;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenFlowers;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.feature.tree.WorldGenTreeShrub;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBSandstoneCanyon;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWastelands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWastelands;

public class RealisticBiomeEBWastelands extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
    private static Block cobbleBlock = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.cobblestone;
    private static byte cobbleByte = (EnhancedBiomesMod.useNewStone == 1) ? (byte)11 : (byte)0;
    
	public RealisticBiomeEBWastelands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBWastelands(),
			new SurfaceEBSandstoneCanyon(
                Blocks.gravel, //Block top 
                (byte)0, //byte topByte
                cobbleBlock, //Block filler, 
                cobbleByte, //byte fillerByte
                Blocks.dirt, //Block mixTop, 
                (byte)0, //byte mixTopByte, 
                cobbleBlock, //Block mixFill, 
                cobbleByte, //byte mixFillByte, 
                cobbleBlock, //Block cliff1, 
                cobbleByte, //byte cliff1Byte, 
                cobbleBlock, //Block cliff2, 
                cobbleByte, //byte cliff2Byte, 
                40f, //float mixWidth, 
                -0.10f, //float mixHeight, 
                10f, //float smallWidth, 
                0.5f //float smallStrength
            )
		);
		
		this.setRealisticBiomeName("EB Wastelands");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBWastelands;
		this.generateVillages = ConfigEB.villageEBWastelands;
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
	
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength,
        float river)
    {
    
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 95 && (k1 < 64 || rand.nextInt(8) == 0))
            {
                (new WorldGenBlob(cobbleBlock, cobbleByte, rand.nextInt(1), rand)).generate(world, rand, i1, k1, j1, true);
            }
        }
        
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;

        for (int i15 = 0; i15 < 1f * strength; i15++)
        {
            int i17 = chunkX + rand.nextInt(16) + 8;
            int i20 = 64 + rand.nextInt(64);
            int l22 = chunkY + rand.nextInt(16) + 8;
            (new WorldGenDeadBush(Blocks.deadbush)).generate(world, rand, i17, i20, l22);
        }
        
        for (int l14 = 0; l14 < 12f * strength; l14++)
        {
            int l19 = chunkX + rand.nextInt(16) + 8;
            int k22 = rand.nextInt(128);
            int j24 = chunkY + rand.nextInt(16) + 8;
            
            if (rand.nextInt(16) == 0) {
                (new WorldGenGrass(Blocks.tallgrass, 1)).generate(world, rand, l19, k22, j24);
            }
        }
    }
}
