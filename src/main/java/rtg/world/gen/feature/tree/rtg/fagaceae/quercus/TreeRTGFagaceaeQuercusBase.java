package rtg.world.gen.feature.tree.rtg.fagaceae.quercus;

import java.util.Random;

import net.minecraft.world.World;
import rtg.world.gen.feature.tree.rtg.fagaceae.TreeRTGFagaceaeBase;

public class TreeRTGFagaceaeQuercusBase extends TreeRTGFagaceaeBase
{

	public TreeRTGFagaceaeQuercusBase()
	{
		super();
	}
	
	@Override
    public void buildTrunk(World world, Random rand, int x, int y, int z)
    {

    }
    
	@Override
    public void buildBranch(World world, Random rand, int x, int y, int z, int dX, int dZ, int logLength, int leaveSize)
    {

    }
	
	@Override
    public void buildLeaves(World world, int x, int y, int z)
    {
		if (!this.noLeaves) {

		}
    }
}
