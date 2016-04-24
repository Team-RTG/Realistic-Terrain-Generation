package teamrtg.rtg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;

/**
 * A replacement for GenLayerRiverMix that doesn't place rivers
 * @author Zeno410
 */
public class GenLayerNoRivers extends GenLayerRiverMix {

    public GenLayerNoRivers(long par1, GenLayer par3GenLayer) {
        super(par1, par3GenLayer, par3GenLayer);
        super.parent = par3GenLayer;
    }

    @Override
    public void initWorldGenSeed(long seed) {
        parent.initWorldGenSeed(seed);
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
        return parent.getInts(areaX, areaY, areaWidth, areaHeight);
    }

}
