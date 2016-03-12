package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;

public class DecoFallenTree extends DecoBase
{
    
	public int loops;
	public LogDistribution logDistribution;
	public LogCondition logCondition;
	public float logConditionNoise;
	public int logConditionChance;
	public int maxY;
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int minSize;
	public int maxSize;
	
	public DecoFallenTree()
	{
		super();
		
		this.loops = 1;
		this.logDistribution = LogDistribution.MERCURY;
		this.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.logConditionNoise = 0f;
		this.logConditionChance = 1;
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
			
			float noise = simplex.noise2(chunkX / this.logDistribution.noiseDivisor(), chunkY / this.logDistribution.noiseDivisor()) * this.logDistribution.noiseFactor() + this.logDistribution.noiseAddend();
			
            for (int i = 0; i < this.loops; i++)
            {
                if (isValidLogCondition(noise, rand))
                {
                    int x22 = chunkX + rand.nextInt(16) + 8;
                    int z22 = chunkY + rand.nextInt(16) + 8;
                    int y22 = world.getHeightValue(x22, z22);
                    
                    if (y22 <= this.maxY) {
                    	
                    	if (this.maxSize > this.minSize) {
                    		(new WorldGenLog(this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta, this.minSize + rand.nextInt(this.maxSize - this.minSize))).generate(world, rand, x22, y22, z22);
                    	}
                    	else if (this.maxSize == this.minSize) {
                    		(new WorldGenLog(this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta, this.minSize)).generate(world, rand, x22, y22, z22);
                    	}
                    }
                }
            }
		}
	}
	
	public enum LogDistribution
	{
	    MERCURY (100f, 5f, 0.8f),
	    VENUS   (0f, 0f, 0f),
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

	    LogDistribution(float noiseDivisor, float noiseFactor, float noiseAddend) {
	        this.noiseDivisor = noiseDivisor;
	        this.noiseFactor = noiseFactor;
	        this.noiseAddend = noiseAddend;
	    }
	    private float noiseDivisor() { return noiseDivisor; }
	    private float noiseFactor() { return noiseFactor; }
	    private float noiseAddend() { return noiseAddend; }
	}
	
	public enum LogCondition
	{
		NOISE_GREATER_AND_RANDOM_CHANCE;
	}
	
	public boolean isValidLogCondition(float noise, Random rand)
	{
		switch (this.logCondition)
		{
			case NOISE_GREATER_AND_RANDOM_CHANCE:
				
				return (noise > this.logConditionNoise && rand.nextInt(this.logConditionChance) == 0);
				
			default:
				
				return false;
		}
	}
}
