package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import net.minecraft.world.gen.feature.WorldGenerator;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoDesertWell extends DecoBase {

    private float strengthFactor;

    public DecoDesertWell() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setStrengthFactor(0f); // Not sure why it was done like this, but... the higher the value, the more there will be.

        this.addDecoTypes(DecoType.DESERT_WELL);
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
        this.config().addProperty(this.config().CHANCE).set(1);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            WorldGenerator worldGenerator = new WorldGenDesertWells();

            int loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.config().LOOPS.get();
            for (int i = 0; i < loops; i++) {
                if (rand.nextInt(this.config().CHANCE.get()) == 0) {

                    int intX = worldX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.config().MAX_Y.get());
                    int intZ = worldZ + rand.nextInt(16) + 8;

                    if (intY <= this.config().MAX_Y.get()) {
                        worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoDesertWell setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }
}
