package rtg.api.world.deco;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.util.ChunkInfo;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenCacti;

import java.util.Random;


/**
 * @author WhichOnesPink
 */
public class DecoCactus extends DecoBase {

    private int loops;
    private int chance;
    private float strengthFactor;
    private int maxY;
    private boolean sandOnly;
    private IBlockState soilBlock;

    public DecoCactus() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setLoops(1);
        this.setChance(1);
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // The higher the value, the more there will be.
        this.setSandOnly(false);
        this.setSoilBlock(Blocks.SAND.getDefaultState());
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage, ChunkInfo chunkInot) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.CACTUS)) {

            final int loopCount = (this.strengthFactor > 0f) ? (int) this.strengthFactor : this.loops;
            for (int i = 0; i < loopCount; i++) {

                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));
                final int y = rtgWorld.world().getHeight(pos).getY();
                if (y <= this.maxY && rand.nextInt(this.chance) == 0) {
                    new WorldGenCacti(this.sandOnly, 0, this.soilBlock)
                        .generate(rtgWorld.world(), rand, pos.up(y));
                }
            }
        }
    }

    public int getLoops() {

        return loops;
    }

    public DecoCactus setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoCactus setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoCactus setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoCactus setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public boolean isSandOnly() {

        return sandOnly;
    }

    public DecoCactus setSandOnly(boolean sandOnly) {

        this.sandOnly = sandOnly;
        return this;
    }

    public IBlockState getSoilBlock() {

        return soilBlock;
    }

    public DecoCactus setSoilBlock(IBlockState soilBlock) {

        this.soilBlock = soilBlock;
        return this;
    }
}
