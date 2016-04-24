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
import rtg.world.gen.feature.tree.TreeRTG;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirch;
import rtg.world.gen.feature.tree.WorldGenTreeRTGBirchSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalm;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalmCustom;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSavanna;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSpruceCustom;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSprucePineBig;
import rtg.world.gen.feature.tree.WorldGenTreeRTGSpruceSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGTrees;
import rtg.world.gen.feature.tree.WorldGenTreeRTGWillow;
import rtg.world.gen.feature.tree.rtg.pinaceae.picea.TreeRTGPinaceaePiceaSitchensis;
import rtg.world.gen.feature.tree.rtg.pinaceae.pinus.TreeRTGPinaceaePinusNigra;
import rtg.world.gen.feature.tree.rtg.pinaceae.pinus.TreeRTGPinaceaePinusPonderosa;

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
		
		this.addDecoTypes(DecoType.TREE);
	}
	
	public DecoTree(DecoTree source) {
		this();
		this.loops = source.loops;
		this.strengthFactorForLoops = source.strengthFactorForLoops;
		this.strengthNoiseFactorForLoops = source.strengthNoiseFactorForLoops;
		this.strengthNoiseFactorXForLoops = source.strengthNoiseFactorXForLoops;
		this.treeType = source.treeType;
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
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	
		                        WorldGenerator worldgenerator =
			                        rand.nextInt(4) != 0
			                        ? new WorldGenTreeRTGBirch(4 + rand.nextInt(7), 8 + rand.nextInt(12))
			                        : rand.nextInt(10) != 0
			                        	? new WorldGenTreeRTGTrees(false)
			                        	: new WorldGenForest(false, false);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case BOP_LAND_OF_LAKES:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

	                        	TreeRTGPinaceaePiceaSitchensis smallPine = new TreeRTGPinaceaePiceaSitchensis();
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
		            		
		            	case DESERT_RIVER:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
		                        WorldGenerator worldgenerator = rand.nextInt(4) != 0 ? new WorldGenShrub(0, 0) : new WorldGenTreeRTGSavanna(1, false);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
	            		
		            	case HL_WINDY_ISLAND:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

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
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

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
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

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
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

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
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

	                        	TreeRTGPinaceaePiceaSitchensis smallPine = new TreeRTGPinaceaePiceaSitchensis();
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
		            		
		            	case PALM_CUSTOM:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		                    	if (this.maxSize > this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(this.minSize + rand.nextInt(this.maxSize - this.minSize)));
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
			                        WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(this.minSize));
			                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
			                        worldgenerator.generate(world, rand, intX, intY, intZ);
		                    	}
		            		}
		            		
		            		break;
		            		
		            	case PINACEAE_PICEA_SITCHENSIS:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		            			TreeRTG worldgenerator = new TreeRTGPinaceaePiceaSitchensis()
		            				.setLogBlock(this.logBlock)
		            				.setLogMeta(this.logMeta)
		            				.setLeavesBlock(this.leavesBlock)
		            				.setLeavesMeta(this.leavesMeta)
		            				.setTrunkSize(RandomUtil.getRandomInt(rand, this.minTrunkSize, this.maxTrunkSize))
		            				.setCrownSize(RandomUtil.getRandomInt(rand, this.minCrownSize, this.maxCrownSize));
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case PINACEAE_PINUS_NIGRA:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		            			TreeRTG worldgenerator = new TreeRTGPinaceaePinusNigra()
		            				.setLogBlock(this.logBlock)
		            				.setLogMeta(this.logMeta)
		            				.setLeavesBlock(this.leavesBlock)
		            				.setLeavesMeta(this.leavesMeta)
		            				.setTrunkSize(RandomUtil.getRandomInt(rand, this.minTrunkSize, this.maxTrunkSize))
		            				.setCrownSize(RandomUtil.getRandomInt(rand, this.minCrownSize, this.maxCrownSize));
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case PINACEAE_PINUS_PONDEROSA:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		            			TreeRTG worldgenerator = new TreeRTGPinaceaePinusPonderosa()
		            				.setLogBlock(this.logBlock)
		            				.setLogMeta(this.logMeta)
		            				.setLeavesBlock(this.leavesBlock)
		            				.setLeavesMeta(this.leavesMeta)
		            				.setTrunkSize(RandomUtil.getRandomInt(rand, this.minTrunkSize, this.maxTrunkSize))
		            				.setCrownSize(RandomUtil.getRandomInt(rand, this.minCrownSize, this.maxCrownSize));
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
          		
		            	case SMALL_BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
	                            WorldGenerator worldgenerator = new WorldGenTreeRTGBirchSmall(4 + rand.nextInt(7), 8 + rand.nextInt(12), 2);
	                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
	                            worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
		            	case SAVANNA:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
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
		            		
		            	case SAVANNA_RIVER:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
		                        WorldGenerator worldgenerator =
		                                rand.nextInt(3) != 0 ? new WorldGenShrub(0, 0) : rand.nextInt(9) == 0 ? new WorldGenTreeRTGSavanna(1)
		                                    : new WorldGenTreeRTGSavanna(2);
		                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                            worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
		            	case SMALL_PINES_TREES_FORESTS:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

	                        	TreeRTGPinaceaePiceaSitchensis oakPine = new TreeRTGPinaceaePiceaSitchensis();
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
		                        		? new WorldGenTreeRTGTrees(false)
		                        		: new WorldGenForest(false, false);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case SUPER_TALL_BIRCH:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
		                        WorldGenerator worldgenerator = new WorldGenTreeRTGBirch(16 + rand.nextInt(8), rand.nextInt(8) + 4);
		                        worldgenerator.setScale(1.0D, 1.0D, 1.0D);
		                        worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
		            	case TAIGA_PINE_TALL:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

	                        	TreeRTGPinaceaePiceaSitchensis smallPine = new TreeRTGPinaceaePiceaSitchensis();
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
		            		
		            	case TAIGA_SPRUCE_SMALL:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		            			int spruceTrunkSize = rand.nextInt(2) + 1;
		            			int spruceLeavesSize = rand.nextInt(2) + 1;
		            			byte spruceLeavesMeta = (rand.nextInt(4) != 0) ? (byte)1 : (byte)0;
		            			
		                        WorldGenerator worldgenerator = new WorldGenTreeRTGSpruceCustom(Blocks.log, (byte)1, Blocks.leaves, spruceLeavesMeta, spruceTrunkSize, spruceLeavesSize, spruceLeavesSize);
			                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				                worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;
		            		
		            	case TAIGA_SPRUCE_TALL:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {

		            			int spruceTrunkSize = rand.nextInt(5) + 3;
		            			int spruceLeavesSize = rand.nextInt(6) + 5 + (int)Math.floor(spruceTrunkSize / 3);
		            			byte spruceLeavesMeta = (rand.nextInt(4) != 0) ? (byte)1 : (byte)0;
		            			
		                        WorldGenerator worldgenerator = new WorldGenTreeRTGSpruceCustom(Blocks.log, (byte)1, Blocks.leaves, spruceLeavesMeta, spruceTrunkSize, spruceLeavesSize, spruceLeavesSize);
			                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
				                worldgenerator.generate(world, rand, intX, intY, intZ);
		            		}
		            		
		            		break;		            		

		            	case VANILLA_BEACH_PALM:
		            		
	                        if (intY <= this.maxY && intY >= this.minY && (rand.nextInt((int) (4f / strength)) == 0)) {
	                        	
	                            WorldGenerator worldgenerator = new WorldGenTreeRTGPalm();
	                            worldgenerator.setScale(1.0D, 1.0D, 1.0D);
	                            worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
		            	case VANILLA_COLD_TAIGA:
		            		
	                        if (intY <= this.maxY && intY >= this.minY && (rand.nextInt((int) (4f / strength)) == 0)) {
	                        	
	                        	TreeRTGPinaceaePiceaSitchensis smallPine = new TreeRTGPinaceaePiceaSitchensis();
	                        	smallPine.setLogBlock(Blocks.log)
	                        		.setLogMeta((byte)1)
	                        		.setLeavesBlock(Blocks.leaves)
	                        		.setLeavesMeta((byte)1)
	                        		.setTrunkSize(4 + rand.nextInt(6))
	                        		.setCrownSize(5 + rand.nextInt(10));
	                        	
	                        	TreeRTGPinaceaePiceaSitchensis smallerPine = new TreeRTGPinaceaePiceaSitchensis();
	                        	smallerPine.setLogBlock(Blocks.log)
	                        		.setLogMeta((byte)1)
	                        		.setLeavesBlock(Blocks.leaves)
	                        		.setLeavesMeta((byte)1)
	                        		.setTrunkSize(1 + rand.nextInt(3))
	                        		.setCrownSize(4 + rand.nextInt(4));
	                        	
	                            WorldGenerator worldgenerator = rand.nextInt(4) == 0 ? new WorldGenTreeRTGSpruceSmall(1 + rand.nextInt(2)) : rand.nextInt(6) == 0 ? smallerPine : smallPine;
                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                                worldgenerator.generate(world, rand, intX, intY, intZ);
	                        }
		            		
		            		break;
		            		
                        case VANILLA_OAK:

                            if (intY <= this.maxY && intY >= this.minY && (rand.nextInt((int) (4f / strength)) == 0)) {
                                WorldGenerator worldgenerator = new WorldGenTreeRTGTrees(false);
                                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                                worldgenerator.generate(world, rand, intX, intY, intZ);
                            }

                            break;
		            		
		            	case WILLOW:
		            		
		            		if (intY <= this.maxY && intY >= this.minY && isValidTreeCondition(noise, rand)) {
	                        	
		            			WorldGenerator worldgenerator = new WorldGenTreeRTGWillow();
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
		DESERT_RIVER,
		HL_WINDY_ISLAND,
		MANGROVE,
		MEGA_JUNGLE,
		MEGA_JUNGLE_MANGROVE,
		MEGA_TAIGA,
		PALM_CUSTOM,
		PINACEAE_PICEA_SITCHENSIS,
		PINACEAE_PINUS_NIGRA,
		PINACEAE_PINUS_PONDEROSA,
		SAVANNA,
		SAVANNA_RIVER,
		SMALL_BIRCH,
		SMALL_PINES_TREES_FORESTS,
		SUPER_TALL_BIRCH,
		TAIGA_PINE_TALL,
		TAIGA_SPRUCE_SMALL,
		TAIGA_SPRUCE_TALL,
		VANILLA_BEACH_PALM,
		VANILLA_COLD_TAIGA,
		VANILLA_OAK,
		WILLOW;
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
