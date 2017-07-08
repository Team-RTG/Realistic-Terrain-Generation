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
public class DecoDoubleGrass extends DecoBase {

    public DecoDoubleGrass() {

        super();

        this.addDecoTypes(DecoType.GRASS_DOUBLE);
    }

    @Override
    public String friendlyName() {
        return "Double Grass";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1);
        this.config().addProperty(this.config().STRENGTH_FACTOR).set(0f);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), GRASS)) {

                WorldGenerator worldGenerator = new WorldGenGrass(Blocks.DOUBLE_PLANT.getStateFromMeta(2), 2);

                int loops = (this.config().STRENGTH_FACTOR.get() > 0f) ? (int) (this.config().STRENGTH_FACTOR.get() * strength) : this.config().LOOPS.get();
                for (int i = 0; i < loops; i++) {
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
