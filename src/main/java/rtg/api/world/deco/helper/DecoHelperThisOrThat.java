package rtg.api.world.deco.helper;

/**
 * This deco helper has a one in x chance of called a given deco
 *
 * @author Zeno410
 */

import java.util.Random;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;

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
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            switch (this.chanceType) {
                case EQUALS_ZERO:

                    if (rand.nextInt(this.chance) == 0) {
                        this.decoThis.generate(biome, rtgWorld, rand, chunkX, chunkY, strength, river, hasPlacedVillageBlocks);
                    }
                    else {
                        this.decoThat.generate(biome, rtgWorld, rand, chunkX, chunkY, strength, river, hasPlacedVillageBlocks);
                    }

                    break;

                case NOT_EQUALS_ZERO:

                    if (rand.nextInt(this.chance) != 0) {
                        this.decoThis.generate(biome, rtgWorld, rand, chunkX, chunkY, strength, river, hasPlacedVillageBlocks);
                    }
                    else {
                        this.decoThat.generate(biome, rtgWorld, rand, chunkX, chunkY, strength, river, hasPlacedVillageBlocks);
                    }

                    break;

                default:
                    break;

            }
        }
    }

    public enum ChanceType {
        EQUALS_ZERO,
        NOT_EQUALS_ZERO;
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
}