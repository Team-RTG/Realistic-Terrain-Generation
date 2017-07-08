package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoBaseBiomeDecorations extends DecoBase {

    public DecoBaseBiomeDecorations() {

        super();

        this.addDecoTypes(DecoType.BASE_BIOME_DECORATION);
    }

    @Override
    public String friendlyName() {
        return "Base Biome Decorations";
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(1);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1); // You almost always want to loop only once.
        this.config().addProperty(this.config().EQUALS_ZERO_CHANCE).set(0); // Only used if greater than 0
        this.config().addProperty(this.config().NOT_EQUALS_ZERO_CHANCE).set(0); // Only used if greater than 0
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            int loops = this.config().LOOPS.get();
            for (int i = 0; i < loops; i++) {

                int intY = rtgWorld.world().getHeight(new BlockPos(worldX, 0, worldZ)).getY();

                if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get()) {

                    if (this.config().EQUALS_ZERO_CHANCE.get() > 0) {

                        if (rand.nextInt(this.config().EQUALS_ZERO_CHANCE.get()) == 0) {
                            biome.rDecorator().rDecorateSeedBiome(rtgWorld.world(), rand, worldX, worldZ, rtgWorld.simplex(), rtgWorld.cell(), strength, river);
                        }
                    }
                    else if (this.config().NOT_EQUALS_ZERO_CHANCE.get() > 0) {

                        if (rand.nextInt(this.config().NOT_EQUALS_ZERO_CHANCE.get()) != 0) {
                            biome.rDecorator().rDecorateSeedBiome(rtgWorld.world(), rand, worldX, worldZ, rtgWorld.simplex(), rtgWorld.cell(), strength, river);
                        }
                    }
                    else {

                        biome.rDecorator().rDecorateSeedBiome(rtgWorld.world(), rand, worldX, worldZ, rtgWorld.simplex(), rtgWorld.cell(), strength, river);
                    }
                }
            }
        }
    }
}