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

public class DecoFallenTree5050 extends DecoFallenTree
{

	public Block logBlock2;
	public byte logMeta2;
	public Block leavesBlock2;
	public byte leavesMeta2;
	public int minSize2;
	public int maxSize2;
	
	public DecoFallenTree5050()
	{
		super();

		this.logBlock2 = Blocks.log;
		this.logMeta2 = (byte)1;
		this.leavesBlock2 = Blocks.leaves;
		this.leavesMeta2 = (byte)-1;
		this.minSize2 = 2;
		this.maxSize2 = 4;
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
	                    	
	                    	if (rand.nextBoolean()) {
		                    	if (this.maxSize > this.minSize) {
		                    		(new WorldGenLog(this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta, this.minSize + rand.nextInt(this.maxSize - this.minSize))).generate(world, rand, x22, y22, z22);
		                    	}
		                    	else if (this.maxSize == this.minSize) {
		                    		(new WorldGenLog(this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta, this.minSize)).generate(world, rand, x22, y22, z22);
		                    	}
	                    	}
	                    	else {
		                    	if (this.maxSize2 > this.minSize2) {
		                    		(new WorldGenLog(this.logBlock2, this.logMeta2, this.leavesBlock2, this.leavesMeta2, this.minSize2 + rand.nextInt(this.maxSize2 - this.minSize2))).generate(world, rand, x22, y22, z22);
		                    	}
		                    	else if (this.maxSize2 == this.minSize2) {
		                    		(new WorldGenLog(this.logBlock2, this.logMeta2, this.leavesBlock2, this.leavesMeta2, this.minSize2)).generate(world, rand, x22, y22, z22);
		                    	}
	                    	}
	                    }
	                }
	            }
			}
		}
	}
}
