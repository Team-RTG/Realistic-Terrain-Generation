package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.BiomeBase.BiomeSize;
import rtg.world.gen.feature.WorldGenGrass;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBClearing;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBClearing;

public class RealisticBiomeEBClearing extends RealisticBiomeEBBase
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
    
	public RealisticBiomeEBClearing(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBClearing(),
			new SurfaceEBClearing(
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
		
		this.setRealisticBiomeName("EB Clearing");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBClearing;
		this.generateVillages = ConfigEB.villageEBClearing;
	}
}
