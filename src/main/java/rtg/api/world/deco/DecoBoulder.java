package rtg.api.world.deco;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.util.BlockUtil;
import rtg.api.util.BlockUtil.MatchType;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenBlob;

import java.util.*;


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
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        // TODO: [1.12] Boulders are a decoration and not a terrain feature. This should probably have a config setting, not a generator setting.
        if (rtgWorld.getGeneratorSettings().useBoulders && TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.ROCK)) {
            for (int i = 0; i < this.strengthFactor; ++i) {

                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));
                int y;

                switch (this.heightType) {
                    case NEXT_INT:
                        y = getRangedRandom(rand, this.minY, this.maxY);
                        break;
                    case GET_HEIGHT_VALUE:
                    default:
                        y = rtgWorld.world().getHeight(pos).getY();
                        break;
                }

                if (y >= this.minY && y <= this.maxY && rand.nextInt(this.chance) == 0) {

                    // If we're in a village, check to make sure the boulder has extra room to grow to avoid corrupting the village.
                    if (!hasVillage || BlockUtil.checkAreaMaterials(MatchType.ALL_IGNORE_REPLACEABLE, rtgWorld.world(), pos.up(y), 2)) {
                        new WorldGenBlob(boulderBlock, 0, validGroundBlocks, this.water)
                            .generate(rtgWorld.world(), rand, pos.up(y));
                    }
                }
            }
        }
    }

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

    // TODO: [1.12] To be removed. Not very useful. This is a surface feature and should use the surface height always.
    @Deprecated
    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE
    }
}
