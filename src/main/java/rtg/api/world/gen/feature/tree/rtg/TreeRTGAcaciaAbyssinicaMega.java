package rtg.api.world.gen.feature.tree.rtg;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class TreeRTGAcaciaAbyssinicaMega extends TreeRTGAcaciaAbyssinica {
	// same but with bigger leaf blobs
	
    public void genLeaves(World world, Random rand, int x, int y, int z, SkylightTracker lightTracker) {

        if (!this.noLeaves) {

            int i;
            int j;
            for (i = -2; i <= 2; i++) {
                for (j = -2; j <= 2; j++) {
                    if (Math.abs(i) + Math.abs(j) < 4) {
                        this.placeLeavesBlock(world, new BlockPos(x + i, y + 1, z + j), this.leavesBlock, this.generateFlag, lightTracker);
                    }
                }
            }

            for (i = -4; i <= 4; i++) {
                for (j = -4; j <= 4; j++) {
                    if (Math.abs(i) + Math.abs(j) < 6) {
                        this.placeLeavesBlock(world, new BlockPos(x + i, y, z + j), this.leavesBlock, this.generateFlag, lightTracker);
                    }
                }
            }
        }

        this.placeLogBlock(world, new BlockPos(x, y, z), this.logBlock, this.generateFlag, lightTracker);
    }

}
