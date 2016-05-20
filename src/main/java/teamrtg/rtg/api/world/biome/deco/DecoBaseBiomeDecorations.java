package teamrtg.rtg.api.world.biome.deco;

import net.minecraft.util.math.BlockPos;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

/**
 * This deco replaces the cumbersome decorateBaseBiome & generateOres logic.
 * Instead of having to remember when to use (and not use) decorateBaseBiome/generateOres,
 * now all you have to do is add this configured deco to the biome wherever you want the base biome
 * to decorate itself. You no longer need to worry about ore gen because that gets handled automatically.
 * @author WhichOnesPink
 */
public class DecoBaseBiomeDecorations extends DecoBase {

    /**
     * This optional setting is useful when you want the base biome to decorate a majority of the biome's chunks.
     * Only used if greater than 0
     */
    public int equalsZeroChance;

    /**
     * This optional setting is useful when you want the base biome to decorate a minority of the biome's chunks.
     * Only used if greater than 0
     */
    public int notEqualsZeroChance;

    /**
     * How many times per chunk do we want the base biome to decorate itself? (Usually only once)
     */
    public int loops;

    /**
     * Height restriction.
     */
    public int maxY;

    public DecoBaseBiomeDecorations() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.equalsZeroChance = 0; // Only used if greater than 0
        this.notEqualsZeroChance = 0; // Only used if greater than 0
        this.loops = 1; // You almost always want to loop only once.
        this.maxY = 255; // No height limit by default.

        this.addDecoTypes(DecoType.BASE_BIOME_DECORATION);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator biomeGenerator) {
        if (this.allowed) {

            for (int i = 0; i < loops; i++) {

                int intX = chunkX + rand.nextInt(16) + 8;
                int intZ = chunkY + rand.nextInt(16) + 8;
                int intY = rtgWorld.world.getHeight(new BlockPos(intX, 1, intZ)).getY();

                if (intY <= this.maxY) {

                    if (this.equalsZeroChance > 0) {

                        if (rand.nextInt(this.equalsZeroChance) == 0) {
                            biomeGenerator.decorateBaseBiome(rtgWorld, chunkX, chunkY, strength, river);
                        } else {
                            biomeGenerator.generateOres(rtgWorld, new BlockPos(chunkX, 1, chunkY), strength, river);
                        }
                    } else if (this.notEqualsZeroChance > 0) {

                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
                            biomeGenerator.decorateBaseBiome(rtgWorld, chunkX, chunkY, strength, river);
                        } else {
                            biomeGenerator.generateOres(rtgWorld, new BlockPos(chunkX, 1, chunkY), strength, river);
                        }
                    } else {

                        biomeGenerator.decorateBaseBiome(rtgWorld, chunkX, chunkY, strength, river);
                    }
                } else {
                    biomeGenerator.generateOres(rtgWorld, new BlockPos(chunkX, 1, chunkY), strength, river);
                }
            }
        } else {
            biomeGenerator.generateOres(rtgWorld, new BlockPos(chunkX, 1, chunkY), strength, river);
        }
    }
}
