package rtg.api.world.deco;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import net.minecraft.block.BlockDoublePlant.EnumPlantType;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.util.ChunkInfo;
import rtg.api.util.Logger;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenDoublePlantRTG;
import rtg.api.world.gen.feature.WorldGenFlowersRTG;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;


/**
 * @author WhichOnesPink
 */
public class DecoFlowersRTG extends DecoBase {

    private final Collection<EnumFlowerType> flowers = Sets.newHashSet();
    private final Collection<EnumPlantType> plants = Sets.newHashSet();
    private float strengthFactor; // Higher = more flowers.
    private int minY; // Height restriction.
    private int maxY; // Height restriction.
    // TODO: [1.12] This is a surface feature, so only World#getHeight should be used.
    @Deprecated
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
        this.setChance(1); // 100% chance of generating by default.
        this.setNotEqualsZeroChance(1);
        this.setMinY(1); // No lower height limit by default - this should really be 63, but... backwards-compatibility. :/
        this.setMaxY(255);
        this.setHeightType(HeightType.NEXT_INT);
        this.setStrengthFactor(0f); // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.setLoops(1);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage, ChunkInfo chunkInot) {

        if ((this.flowers == null || this.flowers.isEmpty()) && (this.plants == null || this.plants.isEmpty())) {
            Logger.error("DecoFlowersRTG called with a null or empty flower/plant list in biome {} at chunk {}", Biome.REGISTRY.getNameForObject(biome.baseBiome()), chunkPos.toString());
            return;
        }

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.FLOWERS)) {

            final int loopCount = (this.strengthFactor > 0f) ? (int) this.strengthFactor : this.loops;
            for (int i = 0; i < loopCount * 16; i++) {
                BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));

                int y;
                switch (this.heightType) {
                    case NEXT_INT:
                        y = getRangedRandom(rand, this.minY, this.maxY);
                        break;

                    case GET_HEIGHT_VALUE:
                        y = rtgWorld.world().getHeight(pos).getY();
                        break;

                    default:
                        y = rand.nextInt(this.maxY);
                        break;
                }

                if (this.flowers != null && !this.flowers.isEmpty()) {

                    if (this.notEqualsZeroChance > 1) {
                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
                            new WorldGenFlowersRTG(this.flowers).generate(rtgWorld.world(), rand, pos.up(y));
                        }
                    } else {
                        if (rand.nextInt(this.chance) == 0) {
                            new WorldGenFlowersRTG(this.flowers).generate(rtgWorld.world(), rand, pos.up(y));
                        }
                    }
                }

                if (this.plants != null && !this.plants.isEmpty()) {

                    if (this.notEqualsZeroChance > 1) {
                        if (rand.nextInt(this.notEqualsZeroChance) != 0) {
                            new WorldGenDoublePlantRTG(Iterables.get(this.plants, rand.nextInt(this.plants.size()))).generate(rtgWorld.world(), rand, pos.up(y));
                        }
                    } else {
                        if (rand.nextInt(this.chance) == 0) {
                            new WorldGenDoublePlantRTG(Iterables.get(this.plants, rand.nextInt(this.plants.size()))).generate(rtgWorld.world(), rand, pos.up(y));
                        }
                    }
                }
            }
        }
    }

    // TODO: [1.12] Maybe add a method for adding flowers with weight, so that some in a list are more predominant.
    public DecoFlowersRTG addFlowers(EnumFlowerType... flowers) {
        this.flowers.addAll(Lists.newArrayList(flowers));
        return this;
    }

    public DecoFlowersRTG addPlants(EnumPlantType... plants) {
        this.plants.addAll(Lists.newArrayList(plants));
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

    // TODO: [1.12] This is a surface feature, so only World#getHeight should be used.
    @Deprecated
    public enum HeightType {
        NEXT_INT,
        GET_HEIGHT_VALUE
    }
}
