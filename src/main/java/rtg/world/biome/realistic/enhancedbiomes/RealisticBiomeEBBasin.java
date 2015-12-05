package rtg.world.biome.realistic.enhancedbiomes;

import rtg.config.enhancedbiomes.ConfigEB;
import rtg.world.biome.BiomeBase;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBBasin;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBBasin;
import enhancedbiomes.EnhancedBiomesMod;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeEBBasin extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.CHERT, (byte)0),
        EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.CHERT, (byte)0),
        EBAPI.ebStonify(EBAPI.LIMESTONE, (byte)0)
    };
    
	public RealisticBiomeEBBasin(BiomeGenBase ebBiome)
	{
		super(
			ebBiome, BiomeBase.climatizedBiome(BiomeGenBase.river, Climate.TEMPERATE),
			new TerrainEBBasin(false, -15f, 0f, 0f, 0f, 63f),
			new SurfaceEBBasin(
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
		
		this.setRealisticBiomeName("EB Basin");
		this.biomeSize = BiomeSize.NORMAL;
		this.biomeWeight = ConfigEB.weightEBBasin;
		this.generateVillages = ConfigEB.villageEBBasin;
        
    }
}
