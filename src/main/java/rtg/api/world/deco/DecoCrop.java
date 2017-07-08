package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenCrops;

/**
 * @author lightningo7
 */
public class DecoCrop extends DecoBase {

    public DecoCrop() {

        super();

        this.addDecoTypes(DecoType.WHEAT);
    }

    @Override
    public String friendlyName() {
        return "Crops";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(63);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().CHANCE).set(10);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(2f);
        this.config().addProperty(this.config().CROP_TYPE).set(3);
        this.config().addProperty(this.config().CROP_SIZE).set(5);
        this.config().addProperty(this.config().CROP_DENSITY).set(50);
        this.config().addProperty(this.config().CROP_HEIGHT).set(2);
        this.config().addProperty(this.config().CROP_WATER).set(true);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenCrops(
                this.config().CROP_TYPE.get(), this.config().CROP_SIZE.get(), this.config().CROP_DENSITY.get(), this.config().CROP_HEIGHT.get(), this.config().CROP_WATER.get()
            );

            if (this.config().CHANCE.get() < 1) {
                return;
            }

            for (int l1 = 0; l1 < this.config().STRENGTH_FACTOR.get() * strength; ++l1) {
                int i1 = worldX + rand.nextInt(16);// + 8;
                int j1 = worldZ + rand.nextInt(16);// + 8;
                int k1 = rtgWorld.world().getHeight(new BlockPos(i1, 0, j1)).getY();

                if (k1 >= this.config().MIN_Y.get() && k1 <= this.config().MAX_Y.get() && rand.nextInt(this.config().CHANCE.get()) == 0) {

                    // If we're in a village, check to make sure the boulder has extra room to grow to avoid corrupting the village.
                    if (hasPlacedVillageBlocks) {
                        if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, WorldUtil.SurroundCheckType.CARDINAL, rand, i1, k1, j1)) {
                            return;
                        }
                    }

                    worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(i1, k1, j1));
                }
            }
        }
    }
}
