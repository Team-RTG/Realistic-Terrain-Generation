package teamrtg.rtg.api.tools.deco.helper;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

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

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator, boolean hasPlacedVillageBlocks) {
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

                    this.decos[i].generate(rtgWorld, rand, chunkX, chunkY, strength, river, realisticBiomeGenerator, hasPlacedVillageBlocks);
                }
                // decrement chosen for the chances missed and continue;
                chosen -= chances[i];
            }
        }
    }
}
