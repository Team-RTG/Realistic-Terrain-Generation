package teamrtg.rtg.api.world.biome.deco.helper;

import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

/**
 * This deco helper takes two deco objects and generates one of them at random.
 * @author WhichOnesPink
 */
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
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (rand.nextBoolean()) {
                this.deco1.generate(rtgWorld, rand, chunkX, chunkY, strength, river, mapGenGenerator);
            } else {
                this.deco2.generate(rtgWorld, rand, chunkX, chunkY, strength, river, mapGenGenerator);
            }
        }
    }
}
