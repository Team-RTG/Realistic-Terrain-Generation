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

        this.addDecoTypes(DecoType.LAYER, DecoType.LEAVES, DecoType.FALLEN_LEAVES);
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(63);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
        this.config().addProperty(this.config().NOT_EQUALS_ZERO_CHANCE).set(1);
        this.config().addProperty(this.config().CHANCE).set(1);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(2f);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldUtil worldUtil = new WorldUtil(rtgWorld.world());
            WorldGenerator worldGenerator = new WorldGenLayers(this.layerBlock, this.layerProperty, this.dropHeight, this.layerRange, this.layerScatter);

            int loopCount = this.config().LOOPS.get();
            loopCount = (this.config().STRENGTH_FACTOR.get() > 0f) ? (int)(this.config().STRENGTH_FACTOR.get() * strength) : loopCount;
            for (int i = 0; i < loopCount; i++) {
                int intX = worldX + rand.nextInt(16);// + 8;
                int intZ = worldZ + rand.nextInt(16);// + 8;
                int intY = rtgWorld.world().getHeight(new BlockPos(intX, 0, intZ)).getY();

                if (this.config().NOT_EQUALS_ZERO_CHANCE.get() > 1) {

                    if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().NOT_EQUALS_ZERO_CHANCE.get()) != 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, rtgWorld.world(), rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
                else {

                    if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().CHANCE.get()) == 0) {
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
}