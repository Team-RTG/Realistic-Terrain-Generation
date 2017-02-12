package rtg.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import rtg.config.rtg.ConfigRTG;

/**
 * Acacia Abyssinica (Flat-top Acacia)
 */
public class TreeRTGAcaciaAbyssinica extends TreeRTG
{

	/**
	 * <b>Acacia Abyssinica (Flat-top Acacia)</b><br><br>
	 * <u>Relevant variables:</u><br>
	 * logBlock, logMeta, leavesBlock, leavesMeta, trunkSize, <s>crownSize</s>, noLeaves<br><br>
	 * <u>DecoTree example:</u><br>
	 * DecoTree decoTree = new DecoTree(new TreeRTGAcaciaAbyssinica());<br>
	 * decoTree.treeType = DecoTree.TreeType.RTG_TREE;<br>
	 * decoTree.treeCondition = DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;<br>
	 * decoTree.distribution = new DecoTree.Distribution(100f, 6f, 0.8f);<br>
	 * decoTree.treeConditionNoise = 0f;<br>
	 * decoTree.treeConditionChance = 4;<br>
	 * decoTree.logBlock = Blocks.log2;<br>
	 * decoTree.logMeta = (byte)0;<br>
	 * decoTree.leavesBlock = Blocks.leaves2;<br>
	 * decoTree.leavesMeta = (byte)0;<br>
	 * decoTree.minTrunkSize = 6;<br>
	 * decoTree.maxTrunkSize = 16;<br>
	 * decoTree.noLeaves = false;<br>
	 * this.addDeco(decoTree);
	 * 
	 */
    public TreeRTGAcaciaAbyssinica()
    {
    	super();
    	
    	this.logBlock = Blocks.log2;
    	this.logMeta = (byte)0;
    	this.leavesBlock = Blocks.leaves2;
    	this.leavesMeta = (byte)0;
		this.trunkSize = 12;
    }

    @Override
    public boolean generate(World world, Random rand, int x, int y, int z)
    {
    	Block b = world.getBlock(x, y - 1, z);
    	
        if (b == Blocks.sand && !ConfigRTG.allowTreesToGenerateOnSand) {
            return false;
        }
    	
    	if(b != Blocks.grass && b != Blocks.dirt)
    	{
    		return false;
    	}

    	int h = this.trunkSize;
    	int bh = h - 6;
    	
    	for(int i = 0; i < h; i++)
    	{
    		this.placeLogBlock(world, x, y + i, z, this.logBlock, this.logMeta, this.generateFlag);
    	}
		genLeaves(world, rand, x, y + h, z);
		
		int sh, eh, dir;
		float xd, yd, c;
		
		for(int a = 6 + rand.nextInt(3); a > -1; a--)
		{
			sh = bh + rand.nextInt(4);
			eh = h - (int)((h - sh) * 1f);
			dir = rand.nextInt(360);
			xd = (float)Math.cos(dir * Math.PI / 180f) * 2f;
			yd = (float)Math.sin(dir * Math.PI / 180f) * 2f;
			c = 1;
			
			while(sh < h)
			{
				this.placeLogBlock(world, x + (int)(xd * c), y + sh, z + (int)(yd * c), this.logBlock, this.logMeta, this.generateFlag);
				sh++;
				c += 0.5f;
			}
			genLeaves(world, rand, x + (int)(xd * c), y + sh, z + (int)(yd * c));
		}
    	
    	return true;
    }
    
    public void genLeaves(World world, Random rand, int x, int y, int z)
    {
    	if (!this.noLeaves) {
    		
	    	int i;
	    	int j;
	    	for(i = -2; i <= 2; i++)
	    	{
	    		for(j = -2; j <= 2; j++)
	    		{
	    			if(world.isAirBlock(x + i, y + 1, z + j) && Math.abs(i) + Math.abs(j) < 4)
	    			{
	    				this.placeLeavesBlock(world, x + i, y + 1, z + j, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    			}
	    		}
	    	}
	    	
	    	for(i = -3; i <= 3; i++)
	    	{
	    		for(j = -3; j <= 3; j++)
	    		{
	    			if(world.isAirBlock(x + i, y, z + j) && Math.abs(i) + Math.abs(j) < 5)
	    			{
	    				this.placeLeavesBlock(world, x + i, y, z + j, this.leavesBlock, this.leavesMeta, this.generateFlag);
	    			}
	    		}
	    	}
    	}
    	
    	this.placeLogBlock(world, x, y, z, this.logBlock, this.logMeta, this.generateFlag);
    }
}
