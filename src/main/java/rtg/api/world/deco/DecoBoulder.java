package rtg.api.world.deco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
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

    private IBlockState boulderBlock; // This can be any block.
    private HeightType heightType; // How we determine the Y coord.
    private boolean water;
    protected ArrayList<Block> validGroundBlocks;

    public DecoBoulder() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        this.setHeightType(HeightType.GET_HEIGHT_VALUE);
        this.water = true;

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
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenBlob(boulderBlock, 0, rand, this.water, validGroundBlocks);

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

    public IBlockState getBoulderBlock() {

        return boulderBlock;
    }

    public DecoBoulder setBoulderBlock(IBlockState boulderBlock) {

        this.boulderBlock = boulderBlock;
        return this;
    }

    public boolean isWater() {

        return water;
    }

    public DecoBoulder setWater(boolean water) {

        this.water = water;
        return this;
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
