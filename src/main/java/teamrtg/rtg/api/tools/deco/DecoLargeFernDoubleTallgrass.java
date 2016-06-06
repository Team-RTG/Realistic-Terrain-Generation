package teamrtg.rtg.api.tools.deco;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenGrass;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

/**
 * @author WhichOnesPink
 */
public class DecoLargeFernDoubleTallgrass extends DecoBase {

    private final int GRASS_META = 2;
    private final int FERN_META = 3;

    public float strengthFactor;
    public int maxY;
    public int loops;
    public int grassChance;
    public int fernChance;

    public DecoLargeFernDoubleTallgrass() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.loops = 1;
        this.grassChance = 0; // 50% chance for both grass & ferns by default.
        this.fernChance = 0; // 50% chance for both grass & ferns by default. (If set, overrides grass chance.)

        this.addDecoTypes(DecoType.GRASS_DOUBLE, DecoType.FERN_DOUBLE);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), GRASS)) {

                this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
                for (int i = 0; i < this.loops; i++) {
                    int intX = chunkX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = chunkY + rand.nextInt(16) + 8;

                    if (intY <= this.maxY) {

                        if (this.fernChance > 0) {

                            if (rand.nextInt(this.fernChance) == 0) {

                                (new WorldGenGrass(Blocks.DOUBLE_PLANT, FERN_META)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            } else {

                                (new WorldGenGrass(Blocks.DOUBLE_PLANT, GRASS_META)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            }
                        } else if (this.grassChance > 0) {

                            if (rand.nextInt(this.grassChance) == 0) {

                                (new WorldGenGrass(Blocks.DOUBLE_PLANT, GRASS_META)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            } else {

                                (new WorldGenGrass(Blocks.DOUBLE_PLANT, FERN_META)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                            }
                        }
                    }
                }
            }
        }
    }
}
