package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

import rtg.api.world.IRTGWorld;
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
    private byte sandMeta;

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
        this.setSandMeta((byte) 1);

        this.addDecoTypes(DecoType.CACTUS);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), CACTUS)) {

                WorldGenerator worldGenerator = new WorldGenJungleCacti(this.sandOnly, rand.nextInt(this.extraHeight), this.sandMeta);

                for (int i = 0; i < this.strengthFactor * strength; i++) {
                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intY = rand.nextInt(160);
                    int intZ = worldZ + rand.nextInt(16);// + 8;

                    if (intY < this.maxY) {
                        worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                    }
                }
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

    public byte getSandMeta() {

        return sandMeta;
    }

    public DecoJungleCacti setSandMeta(byte sandMeta) {

        this.sandMeta = sandMeta;
        return this;
    }
}
