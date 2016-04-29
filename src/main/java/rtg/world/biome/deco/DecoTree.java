package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirch;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirchSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSavanna;
import rtg.world.gen.feature.tree.deprecated.WorldGenTreeRTGSprucePineBig;
import rtg.world.gen.feature.tree.deprecated.WorldGenTreeRTGSpruceSmall;
import rtg.world.gen.feature.tree.rtg.TreeRTG;
import rtg.world.gen.feature.tree.rtg.TreeRTGPiceaSitchensis;
import rtg.world.gen.feature.tree.vanilla.WorldGenTreesRTG;

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
	public TreeRTG tree;
	public WorldGenerator worldGen;
	public DecoTree.Distribution distribution; // Parameter object for noise calculations.
	public TreeCondition treeCondition; // Enum for the various conditions/chances for tree gen.
	public float treeConditionNoise; // Only applies to a noise-related TreeCondition.
	public int treeConditionChance; // Only applies to a chance-related TreeCondition.
	public float treeConditionFloat; // Multi-purpose float.
	public int minY; // Lower height restriction.
	public int maxY; // Upper height restriction.
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int minSize; // Min tree height (only used with certain tree presets)
	public int maxSize; // Max tree height (only used with certain tree presets)
	public int minTrunkSize; // Min tree height (only used with certain tree presets)
	public int maxTrunkSize; // Max tree height (only used with certain tree presets)
	public int minCrownSize; // Min tree height (only used with certain tree presets)
	public int maxCrownSize; // Max tree height (only used with certain tree presets)
	public boolean noLeaves;
	
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
		this.tree = null;
		this.worldGen = null;
		this.distribution = new DecoTree.Distribution(100f, 5f, 0.8f);
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionFloat = 0f;
		this.treeConditionChance = 1;
		this.minY = 1; // No lower height limit by default.
		this.maxY = 255; // No upper height limit by default.
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
		this.minTrunkSize = 2;
		this.maxTrunkSize = 4;
		this.minCrownSize = 2;
		this.maxCrownSize = 4;
		this.noLeaves = false;
		
		this.addDecoTypes(DecoType.TREE);
	}
	
	public DecoTree(DecoTree source) {
		this();
		this.loops = source.loops;
		this.strengthFactorForLoops = source.strengthFactorForLoops;
		this.strengthNoiseFactorForLoops = source.strengthNoiseFactorForLoops;
		this.strengthNoiseFactorXForLoops = source.strengthNoiseFactorXForLoops;
		this.treeType = source.treeType;
		this.tree = source.tree;
		this.distribution = source.distribution;
		this.treeCondition = source.treeCondition;
		this.treeConditionNoise = source.treeConditionNoise;
		this.treeConditionChance = source.treeConditionChance;
		this.minY = source.minY;
		this.maxY = source.maxY;
		this.logBlock = source.logBlock;
		this.logMeta = source.logMeta;
		this.leavesBlock = source.leavesBlock;
		this.leavesMeta = source.leavesMeta;
		this.minSize = source.minSize;
		this.maxSize = source.maxSize;
		this.minTrunkSize = source.minTrunkSize;
		this.maxTrunkSize = source.maxTrunkSize;
		this.minCrownSize = source.minCrownSize;
		this.maxCrownSize = source.maxCrownSize;
		this.noLeaves = source.noLeaves;
	}
	
	public DecoTree(TreeRTG tree)
	{
		this();
		this.tree = tree;
		this.logBlock = tree.logBlock;
		this.logMeta = tree.logMeta;
		this.leavesBlock = tree.leavesBlock;
		this.leavesMeta = tree.leavesMeta;
	}
	
	public DecoTree(WorldGenerator worldGen)
	{
		this();
		this.worldGen = worldGen;
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
				
				float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;

                int loopCount = this.loops;
                loopCount = (this.strengthFactorForLoops > 0f) ? (int)(this.strengthFactorForLoops * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorForLoops) ? (int)(noise * strength) : loopCount;
                loopCount = (this.strengthNoiseFactorXForLoops) ? (int)(noise * this.strengthFactorForLoops * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	            	switch (this.treeType)
	            	{

		            	case BIRCH_TREES_FOREST:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {
	
		                        WorldGenerator worldgenerator =
			                        rand.nextInt(4) != 0
			                        ? new WorldGenTreeRTGBirch(4 + rand.nextInt(7), 8 + rand.nextInt(12))
			                        : rand.nextInt(10) != 0
			                        	? new WorldGenTreesRTG(false)
			                        	: new WorldGenForest(false, false);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case BOP_LAND_OF_LAKES:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

	                        	TreeRTGPiceaSitchensis smallPine = new TreeRTGPiceaSitchensis();
	                        	smallPine.setLogBlock(Blocks.log)
	                        		.setLogMeta((byte)1)
	                        		.setLeavesBlock(Blocks.leaves)
	                        		.setLeavesMeta((byte)1)
	                        		.setTrunkSize(4 + rand.nextInt(6))
	                        		.setCrownSize(5 + rand.nextInt(10));
		            			
		                        WorldGenerator worldgenerator =
		                                rand.nextBoolean() ? new WorldGenTreeRTGBirch(4 + rand.nextInt(7), 8 + rand.nextInt(12))
		                                : smallPine;
		                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                            worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
	            		
		            	case HL_WINDY_ISLAND:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                    	if (this.maxSize > this.minSize) {
	                                WorldGenerator worldgenerator = new WorldGenTreeRTGSpruceSmall(this.minSize + rand.nextInt(this.maxSize - this.minSize));
	                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
	                                worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
	                                WorldGenerator worldgenerator = new WorldGenTreeRTGSpruceSmall(this.minSize);
	                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
	                                worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case MEGA_JUNGLE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                    	if (this.maxSize > this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenMegaJungle(false, this.minSize + rand.nextInt(this.maxSize - this.minSize), 0, 3, 3);
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenMegaJungle(false, this.minSize, 0, 3, 3);
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case MANGROVE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		                    	if (this.maxSize > this.minSize) {
		                            WorldGenerator worldgenerator = new WorldGenTreeRTGMangrove(
		                                    Blocks.log2, 1, Blocks.leaves2, 1, 7 + rand.nextInt(6), 3 + rand.nextInt(2), 13f, 3, 0.32f, 0.1f
		                                );
		                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                                worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
		                            WorldGenerator worldgenerator = new WorldGenTreeRTGMangrove(
		                                    Blocks.log2, 1, Blocks.leaves2, 1, this.minSize, 3 + rand.nextInt(2), 13f, 3, 0.32f, 0.1f
		                                );
		                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                                worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case MEGA_JUNGLE_MANGROVE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

			                    WorldGenerator worldgenerator =
			                        rand.nextInt(3) != 0
			                        ? new WorldGenMegaJungle(false, 10 + rand.nextInt(18), 20, 3, 3)
			                        : new WorldGenTreeRTGMangrove(Blocks.log, 3, Blocks.leaves, 3, 10 + rand.nextInt(18), 3 + rand.nextInt(2), 13f, RandomUtil.getRandomInt(rand, 4, 5),
			                        0.32f,
			                        0.2f);
			                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                    worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case MEGA_TAIGA:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

	                        	TreeRTGPiceaSitchensis smallPine = new TreeRTGPiceaSitchensis();
	                        	smallPine.setLogBlock(Blocks.log)
	                        		.setLogMeta((byte)1)
	                        		.setLeavesBlock(Blocks.leaves)
	                        		.setLeavesMeta((byte)1)
	                        		.setTrunkSize(1 + rand.nextInt(3))
	                        		.setCrownSize(4 + rand.nextInt(4));
		            			
		                        WorldGenerator worldgenerator =
		                            rand.nextInt(4) == 0
		                            ? new WorldGenTreeRTGSpruceSmall(1 + rand.nextInt(2))
		                        	: rand.nextInt(6) == 0
		                        	    ? smallPine
		                                : new WorldGenTreeRTGSprucePineBig(4 + rand.nextInt(6), 12 + rand.nextInt(10));
					            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
						        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case RTG_TREE:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

		            			this.tree.setLogBlock(this.logBlock);
	            				this.tree.setLogMeta(this.logMeta);
	            				this.tree.setLeavesBlock(this.leavesBlock);
	            				this.tree.setLeavesMeta(this.leavesMeta);
	            				this.tree.setTrunkSize(RandomUtil.getRandomInt(rand, this.minTrunkSize, this.maxTrunkSize));
	            				this.tree.setCrownSize(RandomUtil.getRandomInt(rand, this.minCrownSize, this.maxCrownSize));
	            				this.tree.setNoLeaves(this.noLeaves);
		                        this.tree.setScale(1.0D, 1.0D, 1.0D);
		                        this.tree.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;

		            	case SMALL_BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {
	                        	
	                            WorldGenerator worldgenerator = new WorldGenTreeRTGBirchSmall(4 + rand.nextInt(7), 8 + rand.nextInt(12), 2);
	                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
	                            worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
		            	case SAVANNA:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {
	                        	
		                        if (rand.nextInt(9) == 0) {
		                            WorldGenerator worldgenerator = new WorldGenShrub(0, 0);
		                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                            worldgenerator.generate(world, rand, intX, intY, intZ);
		                        }
		                        
		                        if (rand.nextInt(9) == 0) {
		                            WorldGenerator worldgenerator = new WorldGenTreeRTGSavanna(1);
		                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                            worldgenerator.generate(world, rand, intX, intY, intZ);
		                        }
		                        
		                        if (rand.nextInt(9) == 0) {
		                            WorldGenerator worldgenerator = new WorldGenTreeRTGSavanna(2);
		                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                            worldgenerator.generate(world, rand, intX, intY, intZ);
		                        }
	                        }
		            		
		            		break;
		            		
		            	case SMALL_PINES_TREES_FORESTS:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

	                        	TreeRTGPiceaSitchensis oakPine = new TreeRTGPiceaSitchensis();
	                        	oakPine.setLogBlock(Blocks.log)
	                        		.setLogMeta((byte)0)
	                        		.setLeavesBlock(Blocks.leaves)
	                        		.setLeavesMeta((byte)0)
	                        		.setTrunkSize(4 + rand.nextInt(7))
	                        		.setCrownSize(6 + rand.nextInt(9));
	                        	
		                        WorldGenerator worldgenerator =
		                        rand.nextInt(4) != 0
		                        	? oakPine
		                        	: rand.nextInt(10) != 0
		                        		? new WorldGenTreesRTG(false)
		                        		: new WorldGenForest(false, false);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case SUPER_TALL_BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {
	                        	
		                        WorldGenerator worldgenerator = new WorldGenTreeRTGBirch(16 + rand.nextInt(8), rand.nextInt(8) + 4);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
		            	case TAIGA_PINE_TALL:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {

	                        	TreeRTGPiceaSitchensis smallPine = new TreeRTGPiceaSitchensis();
	                        	smallPine.setLogBlock(Blocks.log)
	                        		.setLogMeta((byte)1)
	                        		.setLeavesBlock(Blocks.leaves)
	                        		.setLeavesMeta((byte)1)
	                        		.setTrunkSize(4 + rand.nextInt(6))
	                        		.setCrownSize(5 + rand.nextInt(10));
	                        	smallPine.setScale(1.0D, 1.0D, 1.0D);
	                        	smallPine.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
                        case WORLDGEN:

                            if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand, strength)) {
                            	
                                WorldGenerator worldgenerator = this.worldGen;
                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                                worldgenerator.generate(world, rand, intX, intY, intZ);
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
		BIRCH_TREES_FOREST,
		BOP_LAND_OF_LAKES,
		HL_WINDY_ISLAND,
		MANGROVE,
		MEGA_JUNGLE,
		MEGA_JUNGLE_MANGROVE,
		MEGA_TAIGA,
		RTG_TREE,
		SAVANNA,
		SMALL_BIRCH,
		SMALL_PINES_TREES_FORESTS,
		SUPER_TALL_BIRCH,
		TAIGA_PINE_TALL,
		WORLDGEN;
	}
	
	public enum TreeCondition
	{
		ALWAYS_GENERATE,
		NOISE_GREATER_AND_RANDOM_CHANCE,
		RANDOM_CHANCE,
		X_DIVIDED_BY_STRENGTH;
	}
	
	public boolean isValidTreeCondition(float noise, Random rand, float strength)
	{
		switch (this.treeCondition)
		{
			case ALWAYS_GENERATE:
				return true;
			
			case NOISE_GREATER_AND_RANDOM_CHANCE:
				return (noise > this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);
				
			case RANDOM_CHANCE:
				return rand.nextInt(this.treeConditionChance) == 0;
				
			case X_DIVIDED_BY_STRENGTH:
				return rand.nextInt((int) (this.treeConditionFloat / strength)) == 0;

			default:
				return false;
		}
	}
}
