package rtg.api.world.deco;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.util.ChunkInfo;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenDoublePlantRTG;

import java.util.Random;


/**
 * @author WhichOnesPink
 */
public class DecoGrass extends DecoBase {

    private float strengthFactor;
    private int maxY;
    private int loops;

    public DecoGrass() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // The higher the value, the more there will be.
        this.setLoops(1);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage, ChunkInfo chunkInot) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.GRASS)) {

            final int loopCount = (this.strengthFactor > 0f) ? (int) this.strengthFactor : this.loops;
            for (int i = 0; i < loopCount; i++) {
                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), rand.nextInt(this.maxY), rand.nextInt(16));
                new WorldGenDoublePlant()
                    .generate(rtgWorld.world(), rand, pos);
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoGrass setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoGrass setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoGrass setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}
