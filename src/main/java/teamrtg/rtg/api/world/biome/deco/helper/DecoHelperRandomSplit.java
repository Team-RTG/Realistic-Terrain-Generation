package teamrtg.rtg.api.world.biome.deco.helper;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

/**
 * This deco helper takes an array of deco objects and an array of chances and generates one accordingly.
 * @author WhichOnesPink
 */
public class DecoHelperRandomSplit extends DecoBase {

    public DecoBase[] decos;
    public int[] chances;

    public DecoHelperRandomSplit() {
        super();

        this.decos = new DecoBase[] {};
        this.chances = new int[] {};
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (this.decos.length < 1 || this.chances.length < 1 || this.decos.length != this.chances.length) {
                throw new RuntimeException("DecoHelperRandomSplit is confused.");
            }

            for (int i = 0; i < this.decos.length; i++) {

                if (rand.nextInt(this.chances[i]) == 0) {

                    this.decos[i].generate(rtgWorld, rand, chunkX, chunkY, strength, river, mapGenGenerator);
                }
            }
        }
    }
}
