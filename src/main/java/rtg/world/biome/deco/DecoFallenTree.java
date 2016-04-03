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
	public DecoFallenTree.Distribution distribution; // Parameter object for noise calculations.
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
		this.distribution = new DecoFallenTree.Distribution(100f, 5f, 0.8f);
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
		
		this.addDecoTypes(DecoType.FALLEN_TREE);
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
				
				float noise = simplex.noise2(chunkX / this.distribution.noiseDivisor, chunkY / this.distribution.noiseDivisor) * this.distribution.noiseFactor + this.distribution.noiseAddend;
				
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
