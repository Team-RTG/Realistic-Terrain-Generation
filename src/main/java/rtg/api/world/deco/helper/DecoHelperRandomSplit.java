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
public class DecoHelperRandomSplit extends DecoHelper {

    private DecoBase[] decos;
    private int[] chances;

    public DecoHelperRandomSplit(DecoBase[] decos, int[] chances) {

        super();

        this.decos = decos;
        this.chances = chances;

        for (DecoBase helperDeco : this.decos) {
            this.addHelperDecos(helperDeco);
        }
    }

    @Override
    public String friendlyName() {
        return "Helper Random Split";
    }

    @Override
    public void initConfig() {}

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

        if (this.config().ALLOW.get()) {

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
}
