package teamrtg.rtg.api.tools.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenDesertWells;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

/**
 * @author WhichOnesPink
 */
public class DecoDesertWell extends DecoBase {

    public float strengthFactor;
    public int maxY;
    public int loops;
    public int chance;

    public DecoDesertWell() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.loops = 1;
        this.chance = 1;

        this.addDecoTypes(DecoType.DESERT_WELL);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
            for (int i = 0; i < this.loops; i++) {
                if (rand.nextInt(this.chance) == 0) {

                    int intX = chunkX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = chunkY + rand.nextInt(16) + 8;

                    if (intY <= this.maxY) {
                        (new WorldGenDesertWells()).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}
