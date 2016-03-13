package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalmCustom;

public class DecoTree extends DecoBase
{
    
	public int loops;
	public TreeType treeType;
	public TreeDistribution treeDistribution;
	public TreeCondition treeCondition;
	public float treeConditionNoise;
	public int treeConditionChance;
	public int maxY;
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int minSize;
	public int maxSize;
	
	public DecoTree()
	{
		super();
		
		this.loops = 1;
		this.treeType = TreeType.MEGA_JUNGLE_MANGROVE;
		this.treeDistribution = TreeDistribution.MERCURY;
		this.treeCondition = TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.treeConditionNoise = 0f;
		this.treeConditionChance = 1;
		this.maxY = 120;
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)-1;
		this.minSize = 2;
		this.maxSize = 4;
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
				
				float noise = simplex.noise2(chunkX / this.treeDistribution.noiseDivisor(), chunkY / this.treeDistribution.noiseDivisor()) * this.treeDistribution.noiseFactor() + this.treeDistribution.noiseAddend();
				
                int intX = chunkX + rand.nextInt(16) + 8;
                int intZ = chunkY + rand.nextInt(16) + 8;
                int intY = world.getHeightValue(intX, intZ);
                
	            for (int i = 0; i < this.loops; i++)
	            {
	            	switch (this.treeType)
	            	{
		            	case MEGA_JUNGLE:
		            		
		            		if (intY <= this.maxY && isValidLogCondition(noise, rand)) {

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
		            		
		            	case MEGA_JUNGLE_MANGROVE:
		            		
		            		if (intY <= this.maxY && isValidLogCondition(noise, rand)) {

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
		            		
		            	case PALM_CUSTOM:
		            		
		            		if (intY <= this.maxY && isValidLogCondition(noise, rand)) {

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
		            		
		            	default:
		            		break;
	            	}
	            }
			}
		}
	}
	
	public enum TreeDistribution
	{
	    MERCURY (100f, 5f, 0.8f),	// float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 5f + 0.8f;
	    VENUS   (80f, 60f, -15f),	// float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
	    EARTH   (0f, 0f, 0f),
	    MARS    (0f, 0f, 0f),
	    JUPITER (0f, 0f, 0f),
	    SATURN  (0f, 0f, 0f),
	    URANUS  (0f, 0f, 0f),
	    NEPTUNE (0f, 0f, 0f),
	    PLUTO	(0f, 0f, 0f);

	    private final float noiseDivisor;
	    private final float noiseFactor;
	    private final float noiseAddend;

	    TreeDistribution(float noiseDivisor, float noiseFactor, float noiseAddend) {
	        this.noiseDivisor = noiseDivisor;
	        this.noiseFactor = noiseFactor;
	        this.noiseAddend = noiseAddend;
	    }
	    private float noiseDivisor() { return noiseDivisor; }
	    private float noiseFactor() { return noiseFactor; }
	    private float noiseAddend() { return noiseAddend; }
	}
	
	public enum TreeType
	{
		MEGA_JUNGLE,
		MEGA_JUNGLE_MANGROVE,
		PALM_CUSTOM;
	}
	
	public enum TreeCondition
	{
		NOISE_GREATER_AND_RANDOM_CHANCE,
		RANDOM_CHANCE;
	}
	
	public boolean isValidLogCondition(float noise, Random rand)
	{
		switch (this.treeCondition)
		{
			case NOISE_GREATER_AND_RANDOM_CHANCE:
				
				return (noise > this.treeConditionNoise && rand.nextInt(this.treeConditionChance) == 0);
			
			case RANDOM_CHANCE:
				
				return rand.nextInt(this.treeConditionChance) == 0;
				
			default:
				
				return false;
		}
	}
}
