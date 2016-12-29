package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;

import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * This deco replaces the cumbersome rDecorateSeedBiome & rOreGenSeedBiome logic.
 * Instead of having to remember when to use (and not use) rDecorateSeedBiome/rOreGenSeedBiome,
 * now all you have to do is add this configured deco to the realistic biome wherever you want the base biome
 * to decorate itself. You no longer need to worry about ore gen because that gets handled automatically.
 *
 * @author WhichOnesPink
 */
public class DecoBaseBiomeDecorations extends DecoBase {

    /**
     * This optional setting is useful when you want the base biome to decorate a majority of the biome's chunks.
     * Only used if greater than 0
     */
    private int equalsZeroChance;

    /**
     * This optional setting is useful when you want the base biome to decorate a minority of the biome's chunks.
     * Only used if greater than 0
     */
    private int notEqualsZeroChance;

    /**
     * How many times per chunk do we want the base biome to decorate itself? (Usually only once)
     */
    private int loops;

    /**
     * Height restriction.
     */
    private int minY;
    private int maxY;

    public DecoBaseBiomeDecorations() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setEqualsZeroChance(0); // Only used if greater than 0
        this.setNotEqualsZeroChance(0); // Only used if greater than 0
        this.setLoops(1); // You almost always want to loop only once.
        this.setMinY(1); // No height limit by default.
        this.setMaxY(255); // No height limit by default.

        this.addDecoTypes(DecoType.BASE_BIOME_DECORATION);
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        // The X and Z coords need to be randomized here to prevent massive ore veins.
        int intX = worldX + rand.nextInt(16);// + 8;
        int intZ = worldZ + rand.nextInt(16);// + 8;

        if (this.allowed) {

            for (int i = 0; i < loops; i++) {

                intX = worldX + rand.nextInt(16);// + 8;
                intZ = worldZ + rand.nextInt(16);// + 8;
                int intY = rtgWorld.world.getHeight(new BlockPos(intX, 0, intZ)).getY();

                if (intY >= this.minY && intY <= this.maxY) {

                    if (this.equalsZeroChance > 0) {

                        if (rand.nextInt(this.equalsZeroChance) == 0) {
                            biome.rDecorator.rDecorateSeedBiome(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river);
                        }
                        else {
                            biome.rDecorator.decorateOres(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river, hasPlacedVillageBlocks);
                        }
                    }
                    else if (this.notEqualsZeroChance > 0) {

                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
                            biome.rDecorator.rDecorateSeedBiome(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river);
                        }
                        else {
                            biome.rDecorator.decorateOres(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river, hasPlacedVillageBlocks);
                        }
                    }
                    else {

                        biome.rDecorator.rDecorateSeedBiome(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river);
                    }
                }
                else {
                    biome.rDecorator.decorateOres(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river, hasPlacedVillageBlocks);
                }
            }
        }
        else {
            biome.rDecorator.decorateOres(rtgWorld.world, rand, intX, intZ, rtgWorld.simplex, rtgWorld.cell, strength, river, hasPlacedVillageBlocks);
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

    public int getLoops() {

        return loops;
    }

    public DecoBaseBiomeDecorations setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoBaseBiomeDecorations setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoBaseBiomeDecorations setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }
}
