package teamrtg.rtg.api.tools.deco;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;

/**
 * @author WhichOnesPink
 */
public class DecoMushrooms extends DecoBase {

    public float strengthFactor;
    public int maxY;
    public float randomFloat;
    public RandomType randomType;
    public int chance;
    public int loops;

    public DecoMushrooms() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // The higher the value, the more there will be. Disabled by default.
        this.randomType = RandomType.USE_CHANCE_VALUE;
        this.randomFloat = 1f;
        this.chance = 1;
        this.loops = 1;

        this.addDecoTypes(DecoType.MUSHROOM);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), SHROOM)) {

                // Let's figure out what the rand.nextInt() argument should be.
                switch (this.randomType) {
                    case ALWAYS_GENERATE:
                        this.chance = 1;
                        break;

                    case USE_CHANCE_VALUE:
                        break;

                    case X_DIVIDED_BY_STRENGTH:
                        this.chance = (int) (this.randomFloat / strength);
                        break;

                    default:
                        break;
                }

                this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
                for (int i = 0; i < this.loops; i++) {
                    if (rand.nextInt(this.chance) == 0) {

                        int intX = chunkX + rand.nextInt(16) + 8;
                        int intY = rand.nextInt(this.maxY);
                        int intZ = chunkY + rand.nextInt(16) + 8;

                        if (intY <= this.maxY) {

                            if (rand.nextBoolean()) {
                                (new WorldGenBush(Blocks.BROWN_MUSHROOM)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            } else {
                                (new WorldGenBush(Blocks.RED_MUSHROOM)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            }
                        }
                    }
                }
            }
        }
    }

    public enum RandomType {
        ALWAYS_GENERATE,
        USE_CHANCE_VALUE,
        X_DIVIDED_BY_STRENGTH
    }
}
