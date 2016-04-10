package rtg.world.biome.realistic.enhancedbiomes;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBWastelands;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBWastelands;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBWastelands extends RealisticBiomeEBBase
{
    public static Block[] ebDominantStoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneEB, Blocks.stone)
    };
    
    public static byte[] ebDominantStoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.GABBRO, (byte)0),
        EBAPI.ebStonify(EBAPI.BASALT, (byte)0)
    };
    
    public static Block[] ebDominantCobblestoneBlock = new Block[]{
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone),
        EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone)
    };
    
    public static byte[] ebDominantCobblestoneMeta = new byte[]{
        EBAPI.ebStonify(EBAPI.GABBRO, (byte)0),
        EBAPI.ebStonify(EBAPI.BASALT, (byte)0)
    };
    
    private static Block cobbleBlock = EBAPI.ebStonify(EnhancedBiomesBlocks.stoneCobbleEB, Blocks.cobblestone);
    private static byte cobbleByte = EBAPI.ebStonify(EBAPI.GABBRO, (byte)0);
    
	public RealisticBiomeEBWastelands(BiomeGenBase ebBiome, BiomeConfig config)
	{
		super(config, 
			ebBiome, BiomeGenBase.river,
			new TerrainEBWastelands(),
			new SurfaceEBWastelands(config, 
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
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = cobbleBlock;
		decoBoulder.boulderMeta = cobbleByte;
		decoBoulder.strengthFactor = 3f;
		decoBoulder.chance = 8;
		decoBoulder.maxY = 95;
		this.addDeco(decoBoulder);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);        
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.chance = 16;
		decoGrass.strengthFactor = 12f;
        this.addDeco(decoGrass);
    }
}
