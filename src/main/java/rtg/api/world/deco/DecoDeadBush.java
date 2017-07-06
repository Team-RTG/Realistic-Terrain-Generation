package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoDeadBush extends DecoBase {

    public DecoDeadBush() {

        super();

        this.addDecoTypes(DecoType.DEAD_BUSH);
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

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), DEAD_BUSH)) {

                WorldGenerator worldGenerator = new WorldGenDeadBush();

                int loopCount = this.config().LOOPS.get();
                loopCount = (this.config().STRENGTH_FACTOR.get() > 0f) ? (int) (this.config().STRENGTH_FACTOR.get() * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intY = rand.nextInt(this.config().MAX_Y.get());
                    int intZ = worldZ + rand.nextInt(16);// + 8;

                    if (intY <= this.config().MAX_Y.get() && rand.nextInt(this.config().CHANCE.get()) == 0) {
                        worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}