package teamrtg.rtg.api.tools.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;

/**
 * @author WhichOnesPink
 */
public class DecoDeadBush extends DecoBase {

    public float strengthFactor;
    public int maxY;
    public int chance;
    public int loops;

    public DecoDeadBush() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // The higher the value, the more there will be.
        this.chance = 1;
        this.loops = 1;

        this.addDecoTypes(DecoType.DEAD_BUSH);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), DEAD_BUSH)) {

                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = chunkX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = chunkY + rand.nextInt(16) + 8;

                    if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        (new WorldGenDeadBush()).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}
