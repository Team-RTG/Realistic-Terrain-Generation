package rtg.world.biome.realistic.enhancedbiomes;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBadlands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBadlands;

public class RealisticBiomeEBBadlands extends RealisticBiomeEBBase
{	
    private static Block ebTopBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass;
    private static byte ebTopByte = EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0;
    private static Block ebFillBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt;
    private static byte ebFillByte = EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0;
    private static Block ebMixTopBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.grassEB : Blocks.grass;
    private static byte ebMixTopByte = EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0;
    private static Block ebMixFillBlock = EnhancedBiomesMod.useNewGrass ? EnhancedBiomesBlocks.dirtEB : Blocks.dirt;
    private static byte ebMixFillByte = EnhancedBiomesMod.useNewGrass ? (byte)7 : (byte)0;
    private static Block ebCliff1Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneEB : Blocks.sandstone;
    private static byte ebCliff1Byte = (EnhancedBiomesMod.useNewStone == 1) ? (byte)2 : (byte)0;
    private static Block ebCliff2Block = (EnhancedBiomesMod.useNewStone == 1) ? EnhancedBiomesBlocks.stoneCobbleEB : Blocks.sandstone;
    private static byte ebCliff2Byte = (EnhancedBiomesMod.useNewStone == 1) ? (byte)2 : (byte)0;
    
	public RealisticBiomeEBBadlands(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.HOT),
			new TerrainEBBadlands(),
			new SurfaceEBBadlands(
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
		
		this.setRealisticBiomeName("EB Badlands");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBBadlands;
		this.generateVillages = ConfigEB.villageEBBadlands;
	}
}
