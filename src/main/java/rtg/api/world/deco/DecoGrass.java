package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenTallGrass;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;


/*
 * @author WhichOnesPink
 */
public class DecoGrass extends DecoBase {

    private static final int MAX_LOOPS = 10;
    private float strengthFactor;
    private int minY;
    private int maxY;
    private int loops;
    private int chance;
    private int notEqualsZeroChance;
    private final BlockTallGrass.EnumType type;

    public DecoGrass() { this(BlockTallGrass.EnumType.GRASS); }

    public DecoGrass(BlockTallGrass.EnumType type) {

        super();

        this.type = type;

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setMinY(1); // No height limit by default.
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.setLoops(1);
        this.setChance(1);
        this.notEqualsZeroChance = 1;

        this.addDecoTypes(DecoType.GRASS);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, (this.type == BlockTallGrass.EnumType.DEAD_BUSH) ? Decorate.EventType.DEAD_BUSH : Decorate.EventType.GRASS)) {

            int loopCount = (this.strengthFactor > 0f) ? (int)(this.strengthFactor * strength) : this.loops;
            if (loopCount > MAX_LOOPS) { loopCount = MAX_LOOPS; }

            for (int i = 0; i < loopCount * 64; i++) {

                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));
                int y = this.minY + (rand.nextInt(this.maxY - this.minY) + 1);
                if ((this.notEqualsZeroChance > 1) ? rand.nextInt(this.notEqualsZeroChance) != 0 : rand.nextInt(this.chance) == 0) {
                    new WorldGenTallGrass(this.type)
                        .generate(rtgWorld.world(), rand, pos.up(y));
                }

            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoGrass setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMinY() {

        return minY;
    }

    public DecoGrass setMinY(int minY) {

        this.minY = minY;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoGrass setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoGrass setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoGrass setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public int getNotEqualsZerochance() {

        return notEqualsZeroChance;
    }

    public DecoGrass setNotEqualsZerochance(int notEqualsZeroChance) {

        this.notEqualsZeroChance = notEqualsZeroChance;
        return this;
    }

}
