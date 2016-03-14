package rtg.world.biome.deco;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLog;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoFallenTree extends DecoBase
{
    
	public int loops;
	public LogDistribution logDistribution; // Enum for the various noise calculations.
	public LogCondition logCondition; // Enum for the various conditions/chances for log gen.
	public float logConditionNoise; // Only applies to a noise-related LogCondition.
	public int logConditionChance; // Only applies to a chance-related LogCondition.
	public int maxY; // Height restriction.
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	public int minSize; // Min log height (only used with certain log presets)
	public int maxSize; // Max log height (only used with certain log presets)
	
	public DecoFallenTree()
	{
		super();
		
		/**
		 * Default values.
		 * These can be overridden when configuring the Deco object in the realistic biome.
		 */
		this.loops = 1;
		this.logDistribution = LogDistribution.MERCURY;
		this.logCondition = LogCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
		this.logConditionNoise = 0f;
		this.logConditionChance = 1;
		this.maxY = 255; // No height limit by default.
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
	}
	
	public enum LogDistribution
	{
		/**
		 * Why planets? Because that's what the enum tutorial used.
		 * If we need more planets, we can always seek inspiration from Star Wars.
		 * Feel free to refactor to something more semantic.
		 */
	    MERCURY (100f, 5f, 0.8f),	// float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 5f + 0.8f;
	    VENUS   (0f, 0f, 0f), 		// Unused placeholder
	    EARTH   (0f, 0f, 0f), 		// Unused placeholder
	    MARS    (0f, 0f, 0f), 		// Unused placeholder
	    JUPITER (0f, 0f, 0f), 		// Unused placeholder
	    SATURN  (0f, 0f, 0f), 		// Unused placeholder
	    URANUS  (0f, 0f, 0f), 		// Unused placeholder
	    NEPTUNE (0f, 0f, 0f), 		// Unused placeholder
	    PLUTO	(0f, 0f, 0f); 		// Unused placeholder

	    private final float noiseDivisor;
	    private final float noiseFactor;
	    private final float noiseAddend;

	    LogDistribution(float noiseDivisor, float noiseFactor, float noiseAddend) {
	        this.noiseDivisor = noiseDivisor;
	        this.noiseFactor = noiseFactor;
	        this.noiseAddend = noiseAddend;
	    }
	    float noiseDivisor() { return noiseDivisor; }
	    float noiseFactor() { return noiseFactor; }
	    float noiseAddend() { return noiseAddend; }
	}
	
	public enum LogCondition
	{
		ALWAYS_GENERATE,
		RANDOM_CHANCE,
		NOISE_GREATER_AND_RANDOM_CHANCE;
	}
	
	public boolean isValidLogCondition(float noise, Random rand)
	{
		switch (this.logCondition)
		{
			case ALWAYS_GENERATE:
				
				return true;
				
			case RANDOM_CHANCE:
				
				return (rand.nextInt(this.logConditionChance) == 0);
			
			case NOISE_GREATER_AND_RANDOM_CHANCE:
				
				return (noise > this.logConditionNoise && rand.nextInt(this.logConditionChance) == 0);
				
			default:
				
				return false;
		}
	}
}
