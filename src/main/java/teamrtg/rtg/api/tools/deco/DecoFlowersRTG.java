package teamrtg.rtg.api.tools.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenFlowersRTG;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

/**
 * @author WhichOnesPink
 */
public class DecoFlowersRTG extends DecoBase {

    public int[] flowers; // Integer array of flower IDs.
    public float strengthFactor; // Higher = more flowers.
    public int maxY; // Height restriction.
    public HeightType heightType; // How we determine the Y coord.
    public int chance; // Higher = more rare.
    public int loops;

    /**
     * FLOWER LIST:
     * <p>
     * 0	Rose -
     * 1	Blue Orchid -
     * 2	Allium -
     * 3	Azure Bluet -
     * 4	Red Tulip -
     * 5	Orange Tulip -
     * 6	White Tulip -
     * 7	Pink Tulip -
     * 8	Oxeye Daisy -
     * <p>
     * 9	yellow Flower -
     * <p>
     * 10	Sunflower -
     * 11	Lilac -
     * 12	Double Tallgrass -
     * 13	Large Fern -
     * 14	Rose Bush -
     * 15	Peony
     */
    public DecoFlowersRTG() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.flowers = new int[] {0, 9}; // Only roses and dandelions by default.
        this.chance = 1; // 100% chance of generating by default.
        this.maxY = 255; // No height limit by default.
        this.heightType = HeightType.NEXT_INT;
        this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.loops = 1;

        this.addDecoTypes(DecoType.FLOWER);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), FLOWERS)) {

                this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
                for (int i = 0; i < this.loops; i++) {
                    int intX = chunkX + rand.nextInt(16) + 8;
                    int intZ = chunkY + rand.nextInt(16) + 8;

                    int intY;
                    switch (this.heightType) {
                        case NEXT_INT:
                            intY = rand.nextInt(this.maxY);
                            break;

                        case GET_HEIGHT_VALUE:
                            intY = rtgWorld.world.getHeight(new BlockPos(intX, 1, intZ)).getY();
                            break;

                        default:
                            intY = rand.nextInt(this.maxY);
                            break;

                    }

                    if (rand.nextInt(this.chance) == 0) {

                        (new WorldGenFlowersRTG(this.flowers)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }

    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE;
    }
}
