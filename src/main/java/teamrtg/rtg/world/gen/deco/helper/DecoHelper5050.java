package teamrtg.rtg.world.gen.deco.helper;

import net.minecraft.world.World;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;
import teamrtg.rtg.world.gen.deco.DecoBase;

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
    public void generate(RealisticBiomeGenerator biomeGenerator, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        if (this.allowed) {

            if (rand.nextBoolean()) {
                this.deco1.generate(biomeGenerator, world, rand, chunkX, chunkY, simplex, cell, strength, river);
            } else {
                this.deco2.generate(biomeGenerator, world, rand, chunkX, chunkY, simplex, cell, strength, river);
            }
        }
    }
}
