package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Biomes;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.chunk.ChunkPrimer;
import rtg.api.biome.BiomeConfig;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.biome.deco.DecoTree.TreeCondition;
import rtg.world.biome.deco.DecoTree.TreeType;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaDesert;
import rtg.world.gen.terrain.vanilla.TerrainVanillaDesert;

import java.util.Random;

public class RealisticBiomeVanillaDesert extends RealisticBiomeVanillaBase
{
    
    public static IBlockState topBlock = Biomes.desert.topBlock;
    public static IBlockState fillerBlock = Biomes.desert.fillerBlock;

    public RealisticBiomeVanillaDesert(BiomeConfig config)
    {
    
        super(config, 
            Biomes.desert,
            Biomes.river,
            new TerrainVanillaDesert(),
            new SurfaceVanillaDesert(config, topBlock, fillerBlock));
        
        this.waterSurfaceLakeChance = 0;
        this.noLakes=true;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {

		DecoTree riverTrees = new DecoTree();
		riverTrees.checkRiver = true;
		riverTrees.minRiver = 0.86f;
		riverTrees.strengthNoiseFactorForLoops = false;
		riverTrees.strengthFactorForLoops = 10f;
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
        
        DecoLargeFernDoubleTallgrass decoDoublePlants = new DecoLargeFernDoubleTallgrass();
        decoDoublePlants.checkRiver = true;
        decoDoublePlants.minRiver = 0.7f;
        decoDoublePlants.maxY = 128;
        decoDoublePlants.loops = 15;
        decoDoublePlants.fernChance = 6;
        this.addDeco(decoDoublePlants);
        
        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.maxY = 80;
        decoDesertWell.strengthFactor = 1f;
        decoDesertWell.chance = 120;
        this.addDeco(decoDesertWell);

		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 120;
		decoCactus.strengthFactor = 5f;
        this.addDeco(decoCactus);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.strengthFactor = 1f;
        this.addDeco(decoDeadBush);
    }
    
    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {
    
        this.getSurface().paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
        
        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
