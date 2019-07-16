package rtg.api.world.deco.helper;

/**
 * This deco helper has a one in x chance of called a given deco
 *
 * @author Zeno410
 */

import java.util.Random;

import net.minecraft.util.math.ChunkPos;

import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;


// TODO: [1.12] This class can be removed and it's usage defered to DecoHelperRandomSplit with 2 Decos with different WeightedRandom values.
public class DecoHelperThisOrThat extends DecoBase {

    private int chance;
    private ChanceType chanceType;
    private DecoBase decoThis;
    private DecoBase decoThat;

    public DecoHelperThisOrThat(int chance, ChanceType chanceType, DecoBase decoThis, DecoBase decoThat) {

        super();

        this.chance = chance;
        this.chanceType = chanceType;
        this.decoThis = decoThis;
        this.decoThat = decoThat;
        if (!decoThis.properlyDefined()) {
            throw new RuntimeException();
        }
        if (!decoThat.properlyDefined()) {
            throw new RuntimeException();
        }
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        switch (this.chanceType) {
            case EQUALS_ZERO:

                if (rand.nextInt(this.chance) == 0) {
                    this.decoThis.generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
                }
                else {
                    this.decoThat.generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
                }

                break;

            case NOT_EQUALS_ZERO:

                if (rand.nextInt(this.chance) != 0) {
                    this.decoThis.generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
                }
                else {
                    this.decoThat.generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
                }

                break;

            default:
                break;

        }
    }

    public int getChance() {

        return chance;
    }

    public DecoHelperThisOrThat setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public ChanceType getChanceType() {

        return chanceType;
    }

    public DecoHelperThisOrThat setChanceType(ChanceType chanceType) {

        this.chanceType = chanceType;
        return this;
    }

    public enum ChanceType {
        EQUALS_ZERO,
        NOT_EQUALS_ZERO;
    }
}