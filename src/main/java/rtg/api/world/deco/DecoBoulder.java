package rtg.api.world.deco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.RandomUtil;
import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenBlob;

/**
 * @author WhichOnesPink
 */
public class DecoBoulder extends DecoBase {

    private HeightType heightType; // How we determine the Y coord.
    protected ArrayList<Block> validGroundBlocks;

    public DecoBoulder() {

        super();

        this.setHeightType(HeightType.GET_HEIGHT_VALUE);

        this.validGroundBlocks = new ArrayList<Block>(Arrays.asList(
            Blocks.GRASS,
            Blocks.DIRT,
            Blocks.STONE,
            Blocks.GRAVEL,
            Blocks.CLAY,
            Blocks.SAND
        ));

        this.addDecoTypes(DecoType.BOULDER);
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(60);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().CHANCE).set(10);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(2f);
        this.config().addProperty(this.config().CHECK_WATER).set(true);
        this.config().addProperty(this.config().BOULDER_BLOCK).set(Blocks.COBBLESTONE.getDefaultState());
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenBlob(this.config().BOULDER_BLOCK.get(), 0, rand, this.config().CHECK_WATER.get(), validGroundBlocks);

            for (int l1 = 0; l1 < this.config().STRENGTH_FACTOR.get() * strength; ++l1) {

                int i1 = worldX + rand.nextInt(16);// + 8;
                int j1 = worldZ + rand.nextInt(16);// + 8;
                int k1;

                switch (this.heightType) {

                    case NEXT_INT:
                        k1 = RandomUtil.getRandomInt(rand, this.config().MIN_Y.get(), this.config().MAX_Y.get());
                        break;

                    case GET_HEIGHT_VALUE:
                        k1 = rtgWorld.world().getHeight(new BlockPos(i1, 0, j1)).getY();
                        break;

                    default:
                        k1 = rtgWorld.world().getHeight(new BlockPos(i1, 0, j1)).getY();
                        break;

                }

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

    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE;
    }

    public HeightType getHeightType() {

        return heightType;
    }

    public DecoBoulder setHeightType(HeightType heightType) {

        this.heightType = heightType;
        return this;
    }

    public ArrayList<Block> getValidGroundBlocks() {

        return validGroundBlocks;
    }

    public DecoBoulder setValidGroundBlocks(ArrayList<Block> validGroundBlocks) {

        this.validGroundBlocks = validGroundBlocks;
        return this;
    }
}
