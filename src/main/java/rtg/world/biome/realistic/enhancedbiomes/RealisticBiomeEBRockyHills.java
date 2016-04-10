package rtg.world.biome.realistic.enhancedbiomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoEBTree;
import rtg.world.biome.deco.DecoEBTree.TreeType;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.enhancedbiomes.SurfaceEBRockyHills;
import rtg.world.gen.terrain.enhancedbiomes.TerrainEBRockyHills;
import enhancedbiomes.api.EBAPI;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;

public class RealisticBiomeEBRockyHills extends RealisticBiomeEBBase
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

    public RealisticBiomeEBRockyHills(BiomeGenBase ebBiome, BiomeConfig config)
    {
    
        super(config, 
            ebBiome, BiomeGenBase.river,
            new TerrainEBRockyHills(100f, 30f),
            new SurfaceEBRockyHills(config,
                ebDominantStoneBlock[0],
                ebDominantStoneMeta[0],
                ebDominantStoneBlock[1],
                ebDominantStoneMeta[1],
                false,
                null,
                0f,
                1.5f,
                60f,
                65f,
                1.5f,
                ebDominantCobblestoneBlock[0],
                ebDominantCobblestoneMeta[0],
                ebDominantCobblestoneBlock[1],
                ebDominantCobblestoneMeta[1],
                0.08f
            )
        );
        
		DecoBoulder decoBoulder = new DecoBoulder();
		decoBoulder.boulderBlock = Blocks.cobblestone;
		decoBoulder.maxY = 80;
		decoBoulder.strengthFactor = 1f;
		this.addDeco(decoBoulder);
		
        DecoEBTree ebShrub = new DecoEBTree();
        ebShrub.checkRiver = true;
        ebShrub.minRiver = 0.86f;
		ebShrub.treeType = TreeType.EUCALYPTUS_SHRUB;
		ebShrub.strengthFactorForLoops = 10f;
		ebShrub.distribution.noiseDivisor = 80f;
		ebShrub.distribution.noiseFactor = 60f;
		ebShrub.distribution.noiseAddend = -15f;
		ebShrub.treeCondition = TreeCondition.ALWAYS_GENERATE;
		ebShrub.maxY = 100;
		
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.checkRiver = true;
        decoShrub.minRiver = 0.86f;            
        decoShrub.maxY = 100;
        decoShrub.chance = 1;
        decoShrub.strengthFactor = 10f;
        
        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoShrub, ebShrub};
        decoHelperRandomSplit.chances = new int[]{4, 1};
        this.addDeco(decoHelperRandomSplit);
        
        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
		decoReed.maxY = 68;
		decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);            
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
		decoFlowersRTG.flowers = new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.loops = 3;
        this.addDeco(decoFlowersRTG);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.maxY = 128;
        this.addDeco(decoGrassDoubleTallgrass);
    }
    
    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
        OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        this.getSurface().paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        
        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
