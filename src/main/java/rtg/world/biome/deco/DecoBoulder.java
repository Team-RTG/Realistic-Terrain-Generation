package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.WorldUtil;
import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenBlob;

/**
 * @author WhichOnesPink
 */
public class DecoBoulder extends DecoBase {

    private IBlockState boulderBlock; // This can be any block.
    private float strengthFactor; // Higher = more/bigger boulders.
    private int minY; // Lower height restriction.
    private int maxY; // Upper height restriction.
    private int chance; // Higher = more rare.
    private boolean water;

    public DecoBoulder() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        this.setStrengthFactor(2f);
        this.setMinY(60); // Sensible lower height limit by default.
        this.setMaxY(255); // No upper height limit by default.
        this.setChance(10);
        this.water = true;

        this.addDecoTypes(DecoType.BOULDER);
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world);
            WorldGenerator worldGenerator = new WorldGenBlob(boulderBlock, 0, rand, this.water);

            for (int l1 = 0; l1 < this.strengthFactor * strength; ++l1) {
                int i1 = worldX + rand.nextInt(16);// + 8;
                int j1 = worldZ + rand.nextInt(16);// + 8;
                int k1 = rtgWorld.world.getHeight(new BlockPos(i1, 0, j1)).getY();

                if (k1 >= this.minY && k1 <= this.maxY && rand.nextInt(this.chance) == 0) {

                    // If we're in a village, check to make sure the boulder has extra room to grow to avoid corrupting the village.
                    if (hasPlacedVillageBlocks) {
                        if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, WorldUtil.SurroundCheckType.CARDINAL, rand, i1, k1, j1)) {
                            return;
                        }
                    }

                    worldGenerator.generate(rtgWorld.world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }
    }

    public IBlockState getBoulderBlock() {

        return boulderBlock;
    }

    public DecoBoulder setBoulderBlock(IBlockState boulderBlock) {

        this.setBoulderBlock(boulderBlock);
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoBoulder setStrengthFactor(float strengthFactor) {

        this.setStrengthFactor(strengthFactor);
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoBoulder setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoBoulder setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoBoulder setChance(int chance) {

        this.setChance(chance);
        return this;
    }

    public boolean isWater() {

        return water;
    }

    public DecoBoulder setWater(boolean water) {

        this.water = water;
        return this;
    }
}
