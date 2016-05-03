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
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrubCustom;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoShrubCustom extends DecoShrub
{
	
	public Block logBlock;
	public byte logMeta;
	public Block leavesBlock;
	public byte leavesMeta;
	
	public DecoShrubCustom()
	{
		super();
		
		this.logBlock = Blocks.log;
		this.logMeta = (byte)0;
		this.leavesBlock = Blocks.leaves;
		this.leavesMeta = (byte)0;
	}
	
	@Override
	public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
	{
		if (this.allowed) {
			
			if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {

				int loopCount = this.loops;
				loopCount = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : loopCount;
	            for (int i = 0; i < loopCount; i++)
	            {
	                int intX = chunkX + rand.nextInt(16) + 8;
	                int intZ = chunkY + rand.nextInt(16) + 8;
	                int intY = world.getHeightValue(intX, intZ);
	                
	                if (this.notEqualsZerochance > 1) {
	                	
		                if (intY <= this.maxY && rand.nextInt(this.notEqualsZerochance) != 0) {
		                	(new WorldGenTreeRTGShrubCustom(rand.nextInt(4) + 1, this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta)).generate(world, rand, intX, intY, intZ);
		                }
	                }
	                else {
	                	
		                if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
		                	(new WorldGenTreeRTGShrubCustom(rand.nextInt(4) + 1, this.logBlock, this.logMeta, this.leavesBlock, this.leavesMeta)).generate(world, rand, intX, intY, intZ);
		                }
	                }
	            }
	        }
		}
	}
}
