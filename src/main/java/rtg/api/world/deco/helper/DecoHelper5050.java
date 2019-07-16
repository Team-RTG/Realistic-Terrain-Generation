package rtg.api.world.deco.helper;

import java.util.Random;

import net.minecraft.util.math.ChunkPos;

import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;


/**
 * This deco helper takes two deco objects and generates one of them at random.
 *
 * @author WhichOnesPink
 */
// TODO: [1.12] This class can be removed and it's usage defered to DecoHelperRandomSplit with 2 Decos with equal WeightedRandom values.
public class DecoHelper5050 extends DecoBase {

    private DecoBase deco1;
    private DecoBase deco2;

    public DecoHelper5050(DecoBase deco1, DecoBase deco2) {

        super();

        if (deco1 instanceof DecoHelper5050 || deco2 instanceof DecoHelper5050) {
            throw new RuntimeException("DecoHelper5050 cannot accept itself as a parameter.");
        }

        this.deco1 = deco1;
        this.deco2 = deco2;
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (rand.nextBoolean()) {
            this.deco1.generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
        }
        else {
            this.deco2.generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
        }
    }

    public DecoBase getDeco1() {

        return deco1;
    }

    public DecoHelper5050 setDeco1(DecoBase deco1) {

        this.deco1 = deco1;
        return this;
    }

    public DecoBase getDeco2() {

        return deco2;
    }

    public DecoHelper5050 setDeco2(DecoBase deco2) {

        this.deco2 = deco2;
        return this;
    }
}
