package rtg.world.biome.deco;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.WorldUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenWildWheat;

import java.util.Random;

/**
 * @author lightningo7
 */
public class DecoWheat extends DecoBase {

    public int type; // This can be any block.
    public float strengthFactor; // Higher = something or another.
    public int minY; // Lower height restriction.
    public int maxY; // Upper height restriction.
    public int chance; // Higher = more rare.
    public boolean water;

    public DecoWheat() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.type = 2;
        this.strengthFactor = 2f;
        this.minY = 60; // Sensible lower height limit by default.
        this.maxY = 255; // No upper height limit by default.
        this.chance = 10;
        this.water = true;

        this.addDecoTypes(DecoType.WHEAT);
    }

    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            WorldUtil worldUtil = new WorldUtil(world);
            WorldGenerator worldGenerator = new WorldGenWildWheat(type);

            for (int l1 = 0; l1 < this.strengthFactor * strength; ++l1) {
                int i1 = chunkX + rand.nextInt(16);// + 8;
                int j1 = chunkY + rand.nextInt(16);// + 8;
                int k1 = world.getHeight(new BlockPos(i1, 0, j1)).getY();

                if (k1 >= this.minY && k1 <= this.maxY && rand.nextInt(this.chance) == 0) {

                    // If we're in a village, check to make sure the boulder has extra room to grow to avoid corrupting the village.
                    if (hasPlacedVillageBlocks) {
                        if (!worldUtil.isSurroundedByBlock(Blocks.AIR.getDefaultState(), 2, WorldUtil.SurroundCheckType.CARDINAL, rand, i1, k1, j1)) {
                            return;
                        }
                    }

                    worldGenerator.generate(world, rand, new BlockPos(i1, k1, j1));
                }
            }
        }
    }
}
