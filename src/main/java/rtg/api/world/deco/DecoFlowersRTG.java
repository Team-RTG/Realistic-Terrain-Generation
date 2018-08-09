package rtg.api.world.deco;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;

import static net.minecraft.block.BlockFlower.EnumFlowerType.*;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.FLOWERS;

import rtg.api.util.Logger;
import rtg.api.util.RandomUtil;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenFlowersRTG;

/**
 * @author WhichOnesPink
 */
public class DecoFlowersRTG extends DecoBase {

    private final Collection<EnumFlowerType> flowers = Sets.newHashSet();
    private float strengthFactor; // Higher = more flowers.
    private int minY; // Height restriction.
    private int maxY; // Height restriction.
    private HeightType heightType; // How we determine the Y coord.
    private int chance; // Higher = more rare.
    private int notEqualsZeroChance;
    private int loops;

    /**
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
        this.addFlowers(POPPY, DANDELION); // Only POPPY and DANDELION by default.
        this.setChance(1); // 100% chance of generating by default.
        this.setNotEqualsZeroChance(1);
        this.setMinY(1); // No lower height limit by default - this should really be 63, but... backwards-compatibility. :/
        this.setMaxY(255);
        this.setHeightType(HeightType.NEXT_INT);
        this.setStrengthFactor(0f); // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.setLoops(1);

        this.addDecoTypes(DecoType.FLOWER);
    }

    @Override
    public void generate(IRealisticBiome biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        BlockPos pos = new BlockPos(worldX, 0, worldZ);

        if (this.flowers==null || this.flowers.isEmpty()) {
            Logger.error("DecoFlowerRTG called with a null or empty flower list in biome {} at {}", Biome.REGISTRY.getNameForObject(biome.baseBiome()), pos.toString());
            return;
        }


        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, pos, FLOWERS)) {

                WorldGenerator worldGenerator = new WorldGenFlowersRTG(this.flowers);

                this.setLoops((this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops);

                for (int i = 0; i < this.loops * 16; i++) {
                    int intX = worldX + rand.nextInt(16) + 8;
                    int intZ = worldZ + rand.nextInt(16) + 8;

                    int intY;
                    switch (this.heightType) {
                        case NEXT_INT:
                            intY = RandomUtil.getRandomInt(rand, this.minY, this.maxY);
                            break;

                        case GET_HEIGHT_VALUE:
                            intY = rtgWorld.world().getHeight(new BlockPos(intX, 0, intZ)).getY();
                            break;

                        default:
                            intY = rand.nextInt(this.maxY);
                            break;

                    }

                    if (this.notEqualsZeroChance > 1) {

                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {

                            worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                        }
                    }
                    else {

                        if (rand.nextInt(this.chance) == 0) {

                            worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                        }
                    }
                }
            }
        }
    }

    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE
    }

// TODO: [1.12] Maybe add a method for adding flowers with weight, so that some in a list are more predominant.
    public DecoFlowersRTG addFlowers(EnumFlowerType... flowers) {
        this.flowers.addAll(Lists.newArrayList(flowers));
        return this;
    }

    public Collection<EnumFlowerType> getFlowers() {
        return Collections.unmodifiableCollection(this.flowers);
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoFlowersRTG setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoFlowersRTG setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoFlowersRTG setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public HeightType getHeightType() {

        return heightType;
    }

    public DecoFlowersRTG setHeightType(HeightType heightType) {

        this.heightType = heightType;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoFlowersRTG setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public int getNotEqualsZeroChance() {

        return notEqualsZeroChance;
    }

    public DecoFlowersRTG setNotEqualsZeroChance(int notEqualsZeroChance) {

        this.notEqualsZeroChance = notEqualsZeroChance;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoFlowersRTG setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}
