package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoDesertWell;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrassDoubleTallgrass;
import rtg.world.biome.deco.DecoReed;
import rtg.world.biome.deco.DecoTree;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesertM;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesertM;

public class RealisticBiomeVanillaDesertM extends RealisticBiomeVanillaBase
{
    public static BiomeGenBase standardBiome = BiomeGenBase.desert;
    public static BiomeGenBase mutationBiome = BiomeGenBase.getBiome(standardBiome.biomeID + MUTATION_ADDEND);
    
    public static Block topBlock = mutationBiome.topBlock;
    public static Block fillerBlock = mutationBiome.fillerBlock;

    public RealisticBiomeVanillaDesertM(BiomeConfig config)
    {
    
        super(config, 
            mutationBiome,
            BiomeGenBase.river,
            new TerrainVanillaDesertM(10f, 20f, 68f, 200f),
            new SurfaceVanillaDesertM(config, Blocks.sand, (byte)0, Blocks.sandstone, (byte)0, 0));
        
        this.waterSurfaceLakeChance = 0;
        this.noLakes=true;
        
		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
		DecoTree riverTrees = new DecoTree();
		riverTrees.checkRiver = true;
		riverTrees.minRiver = 0.86f;
		riverTrees.strengthNoiseFactorForLoops = false;
		riverTrees.strengthFactorForLoops = 20f;
		riverTrees.treeType = TreeType.DESERT_RIVER;
		riverTrees.treeCondition = TreeCondition.ALWAYS_GENERATE;
		riverTrees.maxY = 100;
		this.addDeco(riverTrees);
            
		DecoCactus decoRiverCactus = new DecoCactus();
		decoRiverCactus.checkRiver = true;
		decoRiverCactus.minRiver = 0.7f;
		decoRiverCactus.maxY = 80;
		decoRiverCactus.strengthFactor = 12f;
        this.addDeco(decoRiverCactus);
        
        DecoReed decoReed = new DecoReed();
        decoReed.checkRiver = true;
        decoReed.minRiver = 0.7f;
		decoReed.maxY = 68;
		decoReed.strengthFactor = 2f;
        this.addDeco(decoReed);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.checkRiver = true;
		decoFlowersRTG.minRiver = 0.7f;
		decoFlowersRTG.flowers = new int[] {9, 9, 9, 9, 3, 3, 3, 3, 3, 2, 2, 2, 11, 11, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.loops = 3;
        this.addDeco(decoFlowersRTG);
        
        DecoGrassDoubleTallgrass decoGrassDoubleTallgrass = new DecoGrassDoubleTallgrass();
        decoGrassDoubleTallgrass.checkRiver = true;
        decoGrassDoubleTallgrass.minRiver = 0.7f;
        decoGrassDoubleTallgrass.maxY = 128;
        decoGrassDoubleTallgrass.loops = 15;
        decoGrassDoubleTallgrass.doubleGrassChance = 3;
        this.addDeco(decoGrassDoubleTallgrass);
        
        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.maxY = 80;
        decoDesertWell.strengthFactor = 1f;
        decoDesertWell.chance = 160;
        this.addDeco(decoDesertWell);

		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 120;
		decoCactus.strengthFactor = 3f;
		decoCactus.chance = 2;
        this.addDeco(decoCactus);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.strengthFactor = 2f;
		decoCactus.chance = 3;
        this.addDeco(decoDeadBush);
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
