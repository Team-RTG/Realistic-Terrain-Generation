package rtg.api.world.deco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSponge;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenSponge;


/**
 * @author WhichOnesPink
 */
// TODO: [1.12] This Deco should probably be removed. In 1.12 sponge is meant to be aquired by raiding ocean monuments
//              and having it generate anywhere in oceans will throw off the balance of the game, and my also negatively
//              affect other mods that rely on sponge being rare.
public class DecoSponge extends DecoBase {

    protected ArrayList<Block> validGroundBlocks;
    private IBlockState spongeBlock; // This can be any block.
    private float strengthFactor; // Higher = more/bigger boulders.
    private int minY; // Lower height restriction.
    private int maxY; // Upper height restriction.
    private HeightType heightType; // How we determine the Y coord.
    private int chance; // Higher = more rare.
    private boolean water;

    public DecoSponge() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setSpongeBlock(Blocks.SPONGE.getDefaultState().withProperty(BlockSponge.WET, true));
        this.setStrengthFactor(2f);
        this.setMinY(20);
        this.setMaxY(45);
        this.setHeightType(HeightType.NEXT_INT);
        this.setChance(10);
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
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        for (int i = 0; i < this.strengthFactor * strength; ++i) {

            final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));

            int y;
            switch (this.heightType) {
                case NEXT_INT:
                    y = getRangedRandom(rand, this.minY, this.maxY);
                    break;

                case GET_HEIGHT_VALUE:
                    y = rtgWorld.world().getHeight(pos).getY();
                    break;

                default:
                    y = rtgWorld.world().getHeight(pos).getY();
                    break;
            }

            if (y >= this.minY && y <= this.maxY && rand.nextInt(this.chance) == 0) {
                new WorldGenSponge(spongeBlock, 0, rand, validGroundBlocks)
                    .generate(rtgWorld.world(), rand, pos.up(y));
            }
        }
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

    public int getMinY() {

        return minY;
    }

    public DecoSponge setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoSponge setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoSponge setChance(int chance) {

        this.chance = chance;
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

    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE;
    }
}
