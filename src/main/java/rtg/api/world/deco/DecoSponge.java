package rtg.api.world.deco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.RandomUtil;
import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenSponge;

/**
 * @author WhichOnesPink
 */
public class DecoSponge extends DecoBase {

    private IBlockState spongeBlock; // This can be any block.
    private float strengthFactor; // Higher = more/bigger boulders.
    private HeightType heightType; // How we determine the Y coord.
    private boolean water;
    protected ArrayList<Block> validGroundBlocks;

    public DecoSponge() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setSpongeBlock(Blocks.SPONGE.getDefaultState().withProperty(BlockSponge.WET, true));
        this.setStrengthFactor(2f);
        this.setHeightType(HeightType.NEXT_INT);
        this.water = true;

        this.validGroundBlocks = new ArrayList<Block>(Arrays.asList(
            Blocks.GRASS,
            Blocks.DIRT,
            Blocks.STONE,
            Blocks.GRAVEL,
            Blocks.CLAY,
            Blocks.SAND
        ));

        this.addDecoTypes(DecoType.SPONGE);
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(20);
        this.config().addProperty(this.config().MAX_Y).set(45);
        this.config().addProperty(this.config().CHANCE).set(10);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenSponge(spongeBlock, 0, rand, validGroundBlocks);

            for (int l1 = 0; l1 < this.strengthFactor * strength; ++l1) {

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
                    worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(i1, k1, j1));
                }
            }
        }
    }

    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE;
    }

    public IBlockState getSpongeBlock() {

        return spongeBlock;
    }

    public DecoSponge setSpongeBlock(IBlockState spongeBlock) {

        this.spongeBlock = spongeBlock;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoSponge setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public boolean isWater() {

        return water;
    }

    public DecoSponge setWater(boolean water) {

        this.water = water;
        return this;
    }

    public HeightType getHeightType() {

        return heightType;
    }

    public DecoSponge setHeightType(HeightType heightType) {

        this.heightType = heightType;
        return this;
    }

    public ArrayList<Block> getValidGroundBlocks() {

        return validGroundBlocks;
    }

    public DecoSponge setValidGroundBlocks(ArrayList<Block> validGroundBlocks) {

        this.validGroundBlocks = validGroundBlocks;
        return this;
    }
}
