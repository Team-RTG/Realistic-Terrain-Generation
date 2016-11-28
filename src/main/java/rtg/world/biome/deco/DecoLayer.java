package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.block.properties.PropertyInteger;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.util.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.util.WorldUtil;
import rtg.util.WorldUtil.SurroundCheckType;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenLayers;

/**
 * @author WhichOnesPink
 */
public class DecoLayer extends DecoBase {

    public IBlockState layerBlock;
    public PropertyInteger layerProperty;
    public int dropHeight;
    public int layerRange;
    public int layerScatter;

    public float strengthFactor; // Higher = more/bigger shrubs.
    public int minY; // Height restriction.
    public int maxY; // Height restriction.
    public int chance; // Higher = more rare.
    public int notEqualsZerochance;
    public int loops;

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
        this.strengthFactor = 2f;
        this.minY = 63; // Sensible height limit by default.
        this.maxY = 255; // No height limit by default.
        this.chance = 1; // 100% chance of generating by default.
        this.notEqualsZerochance = 1;
        this.loops = 1;

        this.addDecoTypes(DecoType.LAYER, DecoType.LEAVES, DecoType.FALLEN_LEAVES);
    }

    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            WorldUtil worldUtil = new WorldUtil(world);
            WorldGenerator worldGenerator = new WorldGenLayers(this.layerBlock, this.layerProperty, this.dropHeight, this.layerRange, this.layerScatter);

            int loopCount = this.loops;
            loopCount = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : loopCount;
            for (int i = 0; i < loopCount; i++) {
                int intX = chunkX + rand.nextInt(16);// + 8;
                int intZ = chunkY + rand.nextInt(16);// + 8;
                int intY = world.getHeight(new BlockPos(intX, 0, intZ)).getY();

                if (this.notEqualsZerochance > 1) {

                    if (intY >= this.minY && intY <= this.maxY && rand.nextInt(this.notEqualsZerochance) != 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, world, rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
                else {

                    if (intY >= this.minY && intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        generateWorldGenerator(worldGenerator, worldUtil, world, rand, intX, intY, intZ, hasPlacedVillageBlocks);
                    }
                }
            }
        }
    }

    private boolean generateWorldGenerator(WorldGenerator worldGenerator, WorldUtil worldUtil, World world, Random rand, int x, int y, int z, boolean hasPlacedVillageBlocks) {
        // If we're in a village, check to make sure the layer has extra room to avoid corrupting the village.
        if (hasPlacedVillageBlocks) {
            if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, SurroundCheckType.CARDINAL, rand, x, y, z)) {
                return false;
            }
        }

        return worldGenerator.generate(world, rand, new BlockPos(x, y, z));
    }
}