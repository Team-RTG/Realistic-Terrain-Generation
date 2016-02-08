
package rtg.world.gen.genlayer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.GenLayerRiverMix;

/**
 * A replacement for GenLayerRiverMix that doesn't place rivers
 * @author Zeno410
 */
public class GenLayerNoRivers extends GenLayerRiverMix {

    public GenLayerNoRivers(long par1, GenLayer par3GenLayer)
    {
        super(par1,par3GenLayer,par3GenLayer);
        super.parent = par3GenLayer;
    }

    @Override
    public void initWorldGenSeed(long arg0) {
        parent.initWorldGenSeed(arg0);
    }
    public int [] getInts(int x,int z,int xSize,int zSize){
        return parent.getInts(x, z, xSize, zSize);
    }

}
