package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.gen.feature.WorldGenDeadBush;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;


/**
 * @author WhichOnesPink
 */
public class DecoDeadBush extends DecoBase {

    private float strengthFactor;
    private int maxY;
    private int chance;
    private int loops;

    public DecoDeadBush() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // The higher the value, the more there will be.
        this.setChance(1);
        this.setLoops(1);

        this.addDecoTypes(DecoType.DEAD_BUSH);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.DEAD_BUSH)) {

            final int loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : this.loops;
            for (int i = 0; i < loopCount; i++) {
                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));
                int y = rand.nextInt(this.maxY);
                if (y <= this.maxY && rand.nextInt(this.chance) == 0) {
                    new WorldGenDeadBush().generate(rtgWorld.world(), rand, pos.up(y));
                }
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoDeadBush setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoDeadBush setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoDeadBush setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public int getLoops() {

        return loops;
    }

    public DecoDeadBush setLoops(int loops) {

        this.loops = loops;
        return this;
    }
}