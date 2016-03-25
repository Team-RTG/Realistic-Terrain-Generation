package rtg.world.biome.deco;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.math.RandomUtil;
import rtg.util.noise.CellNoise;
import rtg.util.noise.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.*;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoTree extends DecoBase
{
    
	public int loops;
	public float strengthFactorForLoops; // If set, this overrides and dynamically calculates 'loops' based on the strength parameter.
	public boolean strengthNoiseFactorForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * strength)
	public boolean strengthNoiseFactorXForLoops; // If true, this overrides and dynamically calculates 'loops' based on (noise * X * strength)
	public TreeType treeType; // Enum for the various tree presets.
	public DecoTree.Distribution distribution; // Parameter object for noise calculations.
	public TreeCondition treeCondition; // Enum for the various conditions/chances for tree gen.
	public float treeConditionNoise; // Only applies to a noise-related TreeCondition.
	public int treeConditionChance; // Only applies to a chance-related TreeCondition.
	public int maxY; // Height restriction.
    public int minY; // Height restriction.
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int minSize; // Min tree height (only used with certain tree presets)
	public int maxSize; // Max tree height (only used with certain tree presets)
	
	public DecoTree()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.strengthFactorForLoops = 0f;
		this.strengthNoiseFactorForLoops = false;
		this.strengthNoiseFactorXForLoops = false;
		this.treeType = TreeType.MEGA_JUNGLE_MANGROVE;
		this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionChance = 1;
		this.maxY = 255; // No height limit by default.
        this.minY = 0;
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
		
		this.addDecoTypes(DecoType.TREE);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 1, chunkY), TREE)) {
				
				float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int)(this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int)(noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int)(noise * this.strengthFactorForLoops * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeight(new BlockPos(intX, 1, intZ)).getY();
	                
	            	switch (this.treeType)
	            	{
	            	
		            	case BIG_PINES:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	
	                            if (rand.nextBoolean()) {
	                                WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 1, 1);
			                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                            }
	                            else {
	                                WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 0, 0);
			                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                            }
		            		}
		            		
		            		break;
		            		
		            	case BIRCH_TREES_FOREST:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	
		                        WorldGenerator worldgenerator =
			                        rand.nextInt(4) != 0
			                        ? new WorldGenTreeRTGBirch(4 + rand.nextInt(7), 8 + rand.nextInt(12))
			                        : rand.nextInt(10) != 0
			                        	? new WorldGenTreeRTGTrees(false)
			                        	: new WorldGenTrees(false);
		                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		            		}
		            		
		            		break;
		            		
		            	case DESERT_RIVER:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
		                        WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(Blocks.log.getStateFromMeta(0), Blocks.leaves.getStateFromMeta(0)) : new WorldGenTreeRTGSavanna(1);
		                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                        }
		            		
		            		break;
	            		
		            	case MEGA_JUNGLE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		                    	if (this.maxSize > this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenMegaJungle(false, this.minSize + rand.nextInt(this.maxSize - this.minSize), 0, Blocks.log.getStateFromMeta(3), Blocks.leaves.getStateFromMeta(3));
			                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		                    	}
		                    	else if (this.maxSize == this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenMegaJungle(false, this.minSize, 0, Blocks.log.getStateFromMeta(3), Blocks.leaves.getStateFromMeta(3));
			                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case MEGA_JUNGLE_MANGROVE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

			                    WorldGenerator worldgenerator =
			                        rand.nextInt(3) != 0
			                        ? new WorldGenMegaJungle(false, 10 + rand.nextInt(18), 20, Blocks.log.getStateFromMeta(3), Blocks.leaves.getStateFromMeta(3))
			                        : new WorldGenTreeRTGMangrove(Blocks.log, 3, Blocks.leaves, 3, 10 + rand.nextInt(18), 3 + rand.nextInt(2), 13f, RandomUtil.getRandomInt(rand, 4, 5),
			                        0.32f,
			                        0.2f);
			                    worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		            		}
		            		
		            		break;
		            		
		            	case PALM_CUSTOM:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		                    	if (this.maxSize > this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(this.minSize + rand.nextInt(this.maxSize - this.minSize)));
			                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		                    	}
		                    	else if (this.maxSize == this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(this.minSize));
			                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case SMALL_BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
	                            WorldGenerator worldgenerator = new WorldGenTreeRTGBirchSmall(4 + rand.nextInt(7), 8 + rand.nextInt(12), 2);
	                            worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                        }
		            		
		            		break;
		            		
		            	case SMALL_PINES_TREES_FORESTS:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		                        WorldGenerator worldgenerator =
		                        rand.nextInt(4) != 0
		                        	? new WorldGenTreeRTGPineSmall(4 + rand.nextInt(7), 6 + rand.nextInt(9), 0)
		                        	: rand.nextInt(10) != 0
		                        		? new WorldGenTreeRTGTrees(false)
		                        		: new WorldGenTrees(false);
		                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
		            		}
		            		
		            		break;
		            		
		            	case SUPER_TALL_BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
		                        WorldGenerator worldgenerator = new WorldGenTreeRTGBirch(16 + rand.nextInt(8), rand.nextInt(8) + 4);
		                        worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                        }
		            		
		            		break;

		            	case VANILLA_BEACH_PALM:
		            		
	                        if (intY <= this.maxY && intY >= this.minY && (rand.nextInt((int) (4f / strength)) == 0)) {
	                        	
	                            WorldGenerator worldgenerator = new WorldGenTreeRTGPalm();
	                            worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                        }
		            		
		            		break;
		            		
		            	case VANILLA_COLD_TAIGA:
		            		
	                        if (intY <= this.maxY && intY >= this.minY && (rand.nextInt((int) (4f / strength)) == 0)) {
	                        	
	                            WorldGenerator worldgenerator =
	                                    rand.nextInt(4) == 0 ? new WorldGenTreeRTGSpruceSmall(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? new WorldGenTreeRTGPineSmall(
	                                        1 + rand.nextInt(3), 4 + rand.nextInt(4)) : new WorldGenTreeRTGPineSmall(4 + rand.nextInt(6), 5 + rand.nextInt(10));

	                                worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
	                        }
		            		
		            		break;

                        case VANILLA_OAK:

                            if (intY <= this.maxY && intY >= this.minY && (rand.nextInt((int) (4f / strength)) == 0)) {
                                WorldGenerator worldgenerator = new WorldGenTreeRTGTrees(false);
                                worldgenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
                            }

                            break;

		            	default:
		            		break;
	            	}
	            }
			}
		}
	}
	
	/**
	 * Parameter object for noise calculations.
	 * 
	 * simplex.noise2(chunkX / noiseDivisor, chunkY / noiseDivisor) * noiseFactor + noiseAddend;
	 * 
	 * @author WhichOnesPink
	 * @author Zeno410
	 *
	 */
	public static class Distribution
	{
	    public float noiseDivisor;
	    public float noiseFactor;
	    public float noiseAddend;
	    
	    public Distribution(float noiseDivisor, float noiseFactor, float noiseAddend)
	    {
	    	this.noiseDivisor = noiseDivisor;
	    	this.noiseFactor = noiseFactor;
	    	this.noiseAddend = noiseAddend;
	    }
	}
	
	public enum TreeType
	{
		BIG_PINES,
		BIRCH_TREES_FOREST,
		DESERT_RIVER,
		MEGA_JUNGLE,
		MEGA_JUNGLE_MANGROVE,
		PALM_CUSTOM,
		SMALL_BIRCH,
		SMALL_PINES_TREES_FORESTS,
		SUPER_TALL_BIRCH,
		VANILLA_BEACH_PALM,
		VANILLA_COLD_TAIGA,
        VANILLA_OAK;
	}
	
	public enum TreeCondition
	{
		ALWAYS_GENERATE,
		NOISE_GREATER_AND_RANDOM_CHANCE,
		RANDOM_CHANCE;
	}
	
	public boolean isValidTreeCondition(float noise, Random rand)
	{
		switch (this.treeCondition)
		{
			case NOISE_GREATER_AND_RANDOM_CHANCE:
				
				return (noise > this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);
			
			case RANDOM_CHANCE:
				
				return rand.nextInt(this.treeConditionChance) == 0;
				
			case ALWAYS_GENERATE:
				
				return true;
				
			default:
				
				return false;
		}
	}
}
