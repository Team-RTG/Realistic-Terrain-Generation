package rtg.api.world.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;

import java.util.Random;


/**
 * @author WhichOnesPink
 */
public class DecoReed extends DecoBase {

    private float strengthFactor;
    private int maxY;
    private int loops;

    public DecoReed() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.setLoops(1);

        this.addDecoTypes(DecoType.REED);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.REED)) {

            final int loopCount = (this.strengthFactor > 0f) ? (int) this.strengthFactor : this.loops;
            for (int i = 0; i < loopCount; i++) {
                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), rand.nextInt(this.maxY), rand.nextInt(16));
                new WorldGenReed()
                    .generate(rtgWorld.world(), rand, pos);
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoReed setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoReed setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoReed setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}
