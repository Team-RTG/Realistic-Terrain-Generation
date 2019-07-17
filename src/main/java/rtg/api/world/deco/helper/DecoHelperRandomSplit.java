package rtg.api.world.deco.helper;

import java.util.Random;

import net.minecraft.util.math.ChunkPos;

import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;


/**
 * This deco helper takes an array of deco objects and an array of chances and generates one accordingly.
 *
 * @author WhichOnesPink
 */
// TODO: [1.12] This class should use an implementation of net.minecraft.util.WeightedRandom$Item to simplify it,
//              and remove the chance of causing `ArrayOutOfBoundsException`s.
public class DecoHelperRandomSplit extends DecoBase {

    public DecoBase[] decos;
    public int[] chances;

    public DecoHelperRandomSplit() {

        super();

        this.decos = new DecoBase[]{};
        this.chances = new int[]{};
    }

    @Override
    public boolean properlyDefined() {

        for (int i = 0; i < decos.length; i++) {
            if (!decos[i].properlyDefined()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (this.decos.length < 1 || this.chances.length < 1 || this.decos.length != this.chances.length) {
            throw new RuntimeException("DecoHelperRandomSplit is confused.");
        }

        int totalChances = 0;
        for (int i = 0; i < this.decos.length; i++) {
            totalChances += chances[i];
        }
        int chosen = rand.nextInt(totalChances);

        for (int i = 0; i < this.decos.length; i++) {

            if (chosen < (this.chances[i])) {

                this.decos[i].generate(biome, rtgWorld, rand, chunkPos, river, hasVillage);
            }
            // decrement chosen for the chances missed and continue;
            chosen -= chances[i];
        }
    }

    public DecoBase[] getDecos() {

        return decos;
    }

    public DecoHelperRandomSplit setDecos(DecoBase[] decos) {

        this.decos = decos;
        return this;
    }

    public int[] getChances() {

        return chances;
    }

    public DecoHelperRandomSplit setChances(int[] chances) {

        this.chances = chances;
        return this;
    }
}
