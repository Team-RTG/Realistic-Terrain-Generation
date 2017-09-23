package rtg.api.world.deco.helper;

import java.util.Random;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBase;

/**
 * This deco helper takes an array of deco objects and an array of chances and generates one accordingly.
 *
 * @author WhichOnesPink
 */
public class DecoHelperRandomSplit extends DecoBase {

    public DecoBase[] decos;
    public int[] chances;

    public DecoHelperRandomSplit() {

        super();

        this.decos = new DecoBase[]{};
        this.chances = new int[]{};
    }

    public boolean properlyDefined() {

        for (int i = 0; i < decos.length; i++) {
            if (!decos[i].properlyDefined()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

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

                    this.decos[i].generate(biome, rtgWorld, rand, chunkX, chunkY, strength, river, hasPlacedVillageBlocks);
                }
                // decrement chosen for the chances missed and continue;
                chosen -= chances[i];
            }
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
