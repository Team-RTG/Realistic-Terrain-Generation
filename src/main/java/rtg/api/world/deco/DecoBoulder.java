package rtg.api.world.deco;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.BlockUtil;
import rtg.api.util.BlockUtil.MatchType;
import rtg.api.util.RandomUtil;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenBlob;
import rtg.api.world.gen.RTGChunkGenSettings;

/**
 * @author WhichOnesPink
 */
public class DecoBoulder extends DecoBase {

    private IBlockState boulderBlock; // This can be any block.
    private float strengthFactor; // Higher = more/bigger boulders.
    private int minY; // Lower height restriction.
    private int maxY; // Upper height restriction.
    private HeightType heightType; // How we determine the Y coord.
    private int chance; // Higher = more rare.
    private boolean water;
    private List<Block> validGroundBlocks;

    public DecoBoulder() {
        super();
        /*Default values*/
        this.setBoulderBlock(Blocks.COBBLESTONE.getDefaultState());
        this.setStrengthFactor(2f);
        this.setMinY(60); // Sensible lower height limit by default.
        this.setMaxY(255); // No upper height limit by default.
        this.setHeightType(HeightType.GET_HEIGHT_VALUE);
        this.setChance(10);
        this.water = true;
        this.validGroundBlocks = Arrays.asList(Blocks.GRASS, Blocks.DIRT, Blocks.STONE, Blocks.GRAVEL, Blocks.CLAY, Blocks.SAND);
        this.addDecoTypes(DecoType.BOULDER);
    }

    @Override
    public void generate(IRealisticBiome biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        RTGChunkGenSettings settings = rtgWorld.getGeneratorSettings();
        if (settings.useBoulders) {

            WorldGenerator worldGenerator = new WorldGenBlob(boulderBlock, 0, validGroundBlocks, this.water);

            MutableBlockPos mpos = new MutableBlockPos();
            for (int i = 0; i < this.strengthFactor * strength; ++i) {

                int x = worldX + rand.nextInt(16);// + 8;
                int z = worldZ + rand.nextInt(16);// + 8;
                int y;

                switch (this.heightType) {
                    case NEXT_INT        : y = RandomUtil.getRandomInt(rand, this.minY, this.maxY);break;
                    case GET_HEIGHT_VALUE:
                    default              : y = rtgWorld.world().getHeight(new BlockPos(x, 0, z)).getY();break;
                }

                if (y >= this.minY && y <= this.maxY && rand.nextInt(this.chance) == 0) {

                    // If we're in a village, check to make sure the boulder has extra room to grow to avoid corrupting the village.
                    if (!hasPlacedVillageBlocks || BlockUtil.checkAreaMaterials(MatchType.ALL_IGNORE_REPLACEABLE, rtgWorld.world(), mpos.setPos(x, y, z), 2)) {
                        worldGenerator.generate(rtgWorld.world(), rand, mpos);
                    }
                }
            }
        }
    }

    public enum HeightType {NEXT_INT, GET_HEIGHT_VALUE}

    public IBlockState getBoulderBlock() {

        return boulderBlock;
    }

    public DecoBoulder setBoulderBlock(IBlockState boulderBlock) {

        this.boulderBlock = boulderBlock;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoBoulder setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
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

        this.chance = chance;
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

    public List<Block> getValidGroundBlocks() {

        return Collections.unmodifiableList(this.validGroundBlocks);
    }

    public DecoBoulder setValidGroundBlocks(ArrayList<Block> validGroundBlocks) {

        this.validGroundBlocks = Collections.unmodifiableList(validGroundBlocks);
        return this;
    }
}
