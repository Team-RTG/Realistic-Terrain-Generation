package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenJungleCacti;


/**
 * @author WhichOnesPink
 */
public class DecoJungleCacti extends DecoBase {

    private float strengthFactor;
    private int maxY;
    private boolean sandOnly;
    private int extraHeight;

    public DecoJungleCacti() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setStrengthFactor(8f);
        this.setMaxY(255); // No height limit by default.
        this.setSandOnly(false);
        this.setExtraHeight(7);

        this.addDecoTypes(DecoType.CACTUS);
    }

    @Override
    public void generate(final IRealisticBiome biome, final RTGWorld rtgWorld, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage) {

        if (TerrainGen.decorate(rtgWorld.world(), rand, chunkPos, Decorate.EventType.CACTUS)) {

            for (int i = 0; i < this.strengthFactor * strength; i++) {
                final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), rand.nextInt(maxY), rand.nextInt(16));
                new WorldGenJungleCacti(this.sandOnly, rand.nextInt(this.extraHeight))
                    .generate(rtgWorld.world(), rand, pos);
            }
        }
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoJungleCacti setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoJungleCacti setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public boolean isSandOnly() {

        return sandOnly;
    }

    public DecoJungleCacti setSandOnly(boolean sandOnly) {

        this.sandOnly = sandOnly;
        return this;
    }

    public int getExtraHeight() {

        return extraHeight;
    }

    public DecoJungleCacti setExtraHeight(int extraHeight) {

        this.extraHeight = extraHeight;
        return this;
    }
}
