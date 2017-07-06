package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.GRASS;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenGrass;

/**
 * @author WhichOnesPink
 */
public class DecoLargeFernDoubleTallgrass extends DecoBase {

    private final int GRASS_META = 2;
    private final int FERN_META = 3;

    public float strengthFactor;
    public int grassChance;
    public int fernChance;

    public DecoLargeFernDoubleTallgrass() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setStrengthFactor(0f); // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.grassChance = 0; // 50% chance for both grass & ferns by default.
        this.fernChance = 0; // 50% chance for both grass & ferns by default. (If set, overrides grass chance.)

        this.addDecoTypes(DecoType.GRASS_DOUBLE, DecoType.FERN_DOUBLE);
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), GRASS)) {

                WorldGenerator worldgeneratorDoubleTallgrass = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(GRASS_META), GRASS_META);
                WorldGenerator worldgeneratorLargeFern = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(FERN_META), FERN_META);

                int loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.config().LOOPS.get();
                for (int i = 0; i < loops; i++) {
                    int intX = worldX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.config().MAX_Y.get());
                    int intZ = worldZ + rand.nextInt(16) + 8;

                    if (intY <= this.config().MAX_Y.get()) {

                        if (this.fernChance > 0) {

                            if (rand.nextInt(this.fernChance) == 0) {

                                worldgeneratorLargeFern.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                            else {

                                worldgeneratorDoubleTallgrass.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                        }
                        else if (this.grassChance > 0) {

                            if (rand.nextInt(this.grassChance) == 0) {

                                worldgeneratorDoubleTallgrass.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                            else {

                                worldgeneratorLargeFern.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                        }
                        else {

                            if (rand.nextBoolean()) {

                                worldgeneratorDoubleTallgrass.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                            else {

                                worldgeneratorLargeFern.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                        }
                    }
                }
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoLargeFernDoubleTallgrass setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getGrassChance() {

        return grassChance;
    }

    public DecoLargeFernDoubleTallgrass setGrassChance(int grassChance) {

        this.grassChance = grassChance;
        return this;
    }

    public int getFernChance() {

        return fernChance;
    }

    public DecoLargeFernDoubleTallgrass setFernChance(int fernChance) {

        this.fernChance = fernChance;
        return this;
    }
}
