package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBadlands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBadlands;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBBadlands extends RealisticBiomeEBBase
{
    public static Block ebDominantStoneBlock;
    public static byte ebDominantStoneMeta;
    public static Block ebDominantCobblestoneBlock;
    public static byte ebDominantCobblestoneMeta;
    
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
			    getDominantEBGrass(ebBiome), //Block top 
			    getDominantEBSoilMeta(ebBiome), //byte topByte
			    getDominantEBDirt(ebBiome), //Block filler, 
			    getDominantEBSoilMeta(ebBiome), //byte fillerByte
			    getDominantEBGrass(ebBiome), //Block mixTop, 
			    getDominantEBSoilMeta(ebBiome), //byte mixTopByte, 
			    getDominantEBDirt(ebBiome), //Block mixFill, 
			    getDominantEBSoilMeta(ebBiome), //byte mixFillByte, 
			    EnhancedBiomesMod.getDominantStone(ebBiome.biomeID), //Block cliff1, 
			    EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID), //byte cliff1Byte, 
			    EnhancedBiomesMod.getCobbleFromStone(EnhancedBiomesMod.getDominantStone(ebBiome.biomeID)), //Block cliff2, 
			    EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID), //byte cliff2Byte, 
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
        
        this.ebDominantStoneBlock = EnhancedBiomesMod.getDominantStone(ebBiome.biomeID);
        this.ebDominantStoneMeta = EnhancedBiomesMod.getDominantStoneMeta(ebBiome.biomeID);
        this.ebDominantCobblestoneBlock = EnhancedBiomesMod.getCobbleFromStone(ebDominantStoneBlock);
        this.ebDominantCobblestoneMeta = ebDominantStoneMeta;
    }
}
