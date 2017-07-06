package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoBaseBiomeDecorations extends DecoBase {

    /**
     * This optional setting is useful when you want the base biome to decorate a majority of the biome's chunks.
     * Only used if greater than 0
     */
    protected int equalsZeroChance;

    /**
     * This optional setting is useful when you want the base biome to decorate a minority of the biome's chunks.
     * Only used if greater than 0
     */
    protected int notEqualsZeroChance;

    public DecoBaseBiomeDecorations() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setEqualsZeroChance(0); // Only used if greater than 0
        this.setNotEqualsZeroChance(0); // Only used if greater than 0

        this.addDecoTypes(DecoType.BASE_BIOME_DECORATION);
    }

    @Override
    public void initConfig() {
        this.config().addProperty(this.config().MIN_Y).set(1);
        this.config().addProperty(this.config().MAX_Y).set(255);
        this.config().addProperty(this.config().LOOPS).set(1); // You almost always want to loop only once.
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.config().ALLOW.get()) {

            int loops = this.config().LOOPS.get();
            for (int i = 0; i < loops; i++) {

                int intY = rtgWorld.world().getHeight(new BlockPos(worldX, 0, worldZ)).getY();

                if (intY >= this.config().MIN_Y.get() && intY <= this.config().MAX_Y.get()) {

                    if (this.equalsZeroChance > 0) {

                        if (rand.nextInt(this.equalsZeroChance) == 0) {
                            biome.rDecorator().rDecorateSeedBiome(rtgWorld.world(), rand, worldX, worldZ, rtgWorld.simplex(), rtgWorld.cell(), strength, river);
                        }
                    }
                    else if (this.notEqualsZeroChance > 0) {

                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
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

    public int getEqualsZeroChance() {

        return equalsZeroChance;
    }

    public DecoBaseBiomeDecorations setEqualsZeroChance(int equalsZeroChance) {

        this.equalsZeroChance = equalsZeroChance;
        return this;
    }

    public int getNotEqualsZeroChance() {

        return notEqualsZeroChance;
    }

    public DecoBaseBiomeDecorations setNotEqualsZeroChance(int notEqualsZeroChance) {

        this.notEqualsZeroChance = notEqualsZeroChance;
        return this;
    }
}