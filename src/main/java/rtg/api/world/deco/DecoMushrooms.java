package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenBush;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.SHROOM;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoMushrooms extends DecoBase {

    private float strengthFactor;
    private int maxY;
    private float randomFloat;
    private RandomType randomType;
    private int chance;
    private int loops;

    public DecoMushrooms() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // The higher the value, the more there will be. Disabled by default.
        this.setRandomType(RandomType.USE_CHANCE_VALUE);
        this.setRandomFloat(1f);
        this.setChance(1);
        this.setLoops(1);

        this.addDecoTypes(DecoType.MUSHROOM);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), SHROOM)) {

                // Let's figure out what the rand.nextInt() argument should be.
                switch (this.randomType) {
                    case ALWAYS_GENERATE:
                        this.setChance(1);
                        break;

                    case USE_CHANCE_VALUE:
                        break;

                    case X_DIVIDED_BY_STRENGTH:
                        this.setChance((int) (this.randomFloat / strength));
                        break;

                    default:
                        break;
                }

                WorldGenerator worldGeneratorBrownShrooms = new WorldGenBush(Blocks.BROWN_MUSHROOM);
                WorldGenerator worldGeneratorRedShrooms = new WorldGenBush(Blocks.RED_MUSHROOM);

                this.setLoops((this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops);
                for (int i = 0; i < this.loops; i++) {
                    if (rand.nextInt(this.chance) == 0) {

                        int intX = worldX + rand.nextInt(16);// + 8;
                        int intY = rand.nextInt(this.maxY);
                        int intZ = worldZ + rand.nextInt(16);// + 8;

                        if (intY <= this.maxY) {

                            if (rand.nextBoolean()) {
                                worldGeneratorBrownShrooms.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                            }
                            else {
                                worldGeneratorRedShrooms.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
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

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoMushrooms setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoMushrooms setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public float getRandomFloat() {

        return randomFloat;
    }

    public DecoMushrooms setRandomFloat(float randomFloat) {

        this.randomFloat = randomFloat;
        return this;
    }

    public RandomType getRandomType() {

        return randomType;
    }

    public DecoMushrooms setRandomType(RandomType randomType) {

        this.randomType = randomType;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoMushrooms setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoMushrooms setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}
