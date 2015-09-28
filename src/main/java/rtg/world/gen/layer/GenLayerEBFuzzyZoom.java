package rtg.world.gen.layer;

import net.minecraft.world.gen.layer.GenLayer;

public class GenLayerEBFuzzyZoom extends GenLayerEBZoom
{
	public GenLayerEBFuzzyZoom(long par1, GenLayer par3GenLayerEB) {
		super(par1, par3GenLayerEB);
	}

	/**
	 * returns the most frequently occurring number of the set, or a random number from those provided
	 */
	protected int selectModeOrRandom(int p_151617_1_, int p_151617_2_, int p_151617_3_, int p_151617_4_) {
		return this.selectRandom(new int[] {p_151617_1_, p_151617_2_, p_151617_3_, p_151617_4_});
	}
}