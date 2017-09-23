package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.PUMPKIN;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoPumpkin extends DecoBase {

    private float randomFloat;
    private RandomType randomType;

    public DecoPumpkin() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setRandomType(RandomType.USE_CHANCE_VALUE);
        this.setRandomFloat(1f);

        this.addDecoTypes(DecoType.PUMPKIN);
    }

    @Override
    public String friendlyName() {
        return "Pumpkins";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
        this.config().addProperty(this.config().CHANCE).set(1);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(0f);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), PUMPKIN)) {

                // Let's figure out what the rand.nextInt() argument should be.
                switch (this.randomType) {
                    case ALWAYS_GENERATE:
                        this.config().CHANCE.set(1);
                        break;

                    case USE_CHANCE_VALUE:
                        break;

                    case X_DIVIDED_BY_STRENGTH:
                        this.config().CHANCE.set((int) (this.randomFloat / strength));
                        break;

                    default:
                        break;
                }

                WorldGenerator worldGenerator = new WorldGenPumpkin();

                int loops = (this.config().STRENGTH_FACTOR.get() > 0f) ? (int) (this.config().STRENGTH_FACTOR.get() * strength) : this.config().LOOPS.get();
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
    }

    public enum RandomType {
        ALWAYS_GENERATE,
        USE_CHANCE_VALUE,
        X_DIVIDED_BY_STRENGTH
    }

    public float getRandomFloat() {

        return randomFloat;
    }

    public DecoPumpkin setRandomFloat(float randomFloat) {

        this.randomFloat = randomFloat;
        return this;
    }

    public RandomType getRandomType() {

        return randomType;
    }

    public DecoPumpkin setRandomType(RandomType randomType) {

        this.randomType = randomType;
        return this;
    }
}
