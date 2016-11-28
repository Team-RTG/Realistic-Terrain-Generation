package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.feature.WorldGenFlowersRTG;

/**
 * @author WhichOnesPink
 */
public class DecoFlowersRTG extends DecoBase {

    public int[] flowers; // Integer array of flower IDs.
    public float strengthFactor; // Higher = more flowers.
    public int minY; // Height restriction.
    public int maxY; // Height restriction.
    public HeightType heightType; // How we determine the Y coord.
    public int chance; // Higher = more rare.
    public int notEqualsZeroChance;
    public int loops;

    /*
     * FLOWER LIST:
     * 0	Poppy
     * 1	Blue Orchid
     * 2	Allium
     * 3	Azure Bluet
     * 4	Red Tulip
     * 5	Orange Tulip
     * 6	White Tulip
     * 7	Pink Tulip
     * 8	Oxeye Daisy
     * 9	Yellow Flower
     * 10	Sunflower
     * 11	Lilac
     * 12	Double Tallgrass
     * 13	Large Fern
     * 14	Rose Bush
     * 15	Peony
     */
    public DecoFlowersRTG() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.flowers = new int[]{0, 9}; // Only roses and dandelions by default.
        this.chance = 1; // 100% chance of generating by default.
        this.notEqualsZeroChance = 1;
        this.minY = 1; // No lower height limit by default - this should really be 63, but... backwards-compatibility. :/
        this.maxY = 253; // 2 below max build height to account for 2-block tall flowers.
        this.heightType = HeightType.NEXT_INT;
        this.strengthFactor = 0f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.loops = 1;

        this.addDecoTypes(DecoType.FLOWER);
    }

    @Override
    public void generate(RealisticBiomeBase biome, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), FLOWERS)) {

                WorldGenerator worldGenerator = new WorldGenFlowersRTG(this.flowers);

                this.loops = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;

                for (int i = 0; i < this.loops * 16; i++) {
                    int intX = chunkX + rand.nextInt(16);// + 8;
                    int intZ = chunkY + rand.nextInt(16);// + 8;

                    int intY;
                    switch (this.heightType) {
                        case NEXT_INT:
                            intY = RandomUtil.getRandomInt(rand, this.minY, this.maxY);
                            break;

                        case GET_HEIGHT_VALUE:
                            intY = world.getHeight(new BlockPos(intX, 0, intZ)).getY();
                            break;

                        default:
                            intY = rand.nextInt(this.maxY);
                            break;

                    }

                    if (this.notEqualsZeroChance > 1) {

                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {

                            worldGenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
                        }
                    }
                    else {

                        if (rand.nextInt(this.chance) == 0) {

                            worldGenerator.generate(world, rand, new BlockPos(intX, intY, intZ));
                        }
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
