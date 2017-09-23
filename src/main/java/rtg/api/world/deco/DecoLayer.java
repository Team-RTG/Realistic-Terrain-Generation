package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.util.WorldUtil;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenLayers;

/**
 * @author WhichOnesPink
 */
public class DecoLayer extends DecoBase {

    private IBlockState layerBlock;
    private PropertyInteger layerProperty;
    private int dropHeight;
    private int layerRange;
    private int layerScatter;

    private float strengthFactor; // Higher = more/bigger shrubs.
    private int minY; // Height restriction.
    private int maxY; // Height restriction.
    private int chance; // Higher = more rare.
    private int notEqualsZeroChance;
    private int loops;

    public DecoLayer(IBlockState layerBlock, PropertyInteger layerProperty) {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.layerBlock = layerBlock;
        this.layerProperty = layerProperty;
        this.dropHeight = 2;
        this.layerRange = 4;
        this.layerScatter = 3;
        this.setStrengthFactor(2f);
        this.setMinY(63); // Sensible height limit by default.
        this.setMaxY(255); // No height limit by default.
        this.setChance(1); // 100% chance of generating by default.
        this.notEqualsZeroChance = 1;
        this.setLoops(1);

        this.addDecoTypes(DecoType.LAYER, DecoType.LEAVES, DecoType.FALLEN_LEAVES);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenLayers(this.layerBlock, this.layerProperty, this.dropHeight, this.layerRange, this.layerScatter);

            int loopCount = this.loops;
            loopCount = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : loopCount;
            for (int i = 0; i < loopCount; i++) {
                int intX = worldX + rand.nextInt(16);// + 8;
                int intZ = worldZ + rand.nextInt(16);// + 8;
                int intY = rtgWorld.world().getHeight(new BlockPos(intX, 0, intZ)).getY();

                if (this.notEqualsZeroChance > 1) {

                    if (intY >= this.minY && intY <= this.maxY && rand.nextInt(this.notEqualsZeroChance) != 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, rtgWorld.world(), rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
                else {

                    if (intY >= this.minY && intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, rtgWorld.world(), rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
            }
        }
    }

    private boolean generateWorldGenerator(WorldGenerator worldGenerator, WorldUtil worldUtil, World world, Random rand, int x, int y, int z, boolean hasPlacedVillageBlocks) {
        // If we're in a village, check to make sure the layer has extra room to avoid corrupting the village.
        if (hasPlacedVillageBlocks) {
            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, WorldUtil.SurroundCheckType.CARDINAL, rand, x, y, z)) {
                return false;
            }
        }

        return worldGenerator.generate(world, rand, new BlockPos(x, y, z));
    }

    public IBlockState getLayerBlock() {

        return layerBlock;
    }

    public DecoLayer setLayerBlock(IBlockState layerBlock) {

        this.layerBlock = layerBlock;
        return this;
    }

    public PropertyInteger getLayerProperty() {

        return layerProperty;
    }

    public DecoLayer setLayerProperty(PropertyInteger layerProperty) {

        this.layerProperty = layerProperty;
        return this;
    }

    public int getDropHeight() {

        return dropHeight;
    }

    public DecoLayer setDropHeight(int dropHeight) {

        this.dropHeight = dropHeight;
        return this;
    }

    public int getLayerRange() {

        return layerRange;
    }

    public DecoLayer setLayerRange(int layerRange) {

        this.layerRange = layerRange;
        return this;
    }

    public int getLayerScatter() {

        return layerScatter;
    }

    public DecoLayer setLayerScatter(int layerScatter) {

        this.layerScatter = layerScatter;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoLayer setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoLayer setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoLayer setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoLayer setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public int getNotEqualsZerochance() {

        return notEqualsZeroChance;
    }

    public DecoLayer setNotEqualsZerochance(int notEqualsZeroChance) {

        this.notEqualsZeroChance = notEqualsZeroChance;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoLayer setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}