package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenCacti;

/**
 * @author WhichOnesPink
 */
public class DecoCactus extends DecoBase {

    private int loops;
    private int chance;
    private float strengthFactor;
    private int maxY;
    private boolean sandOnly;
    private IBlockState soilBlock;

    public DecoCactus() {

        super();

        /*
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.setLoops(1);
        this.setChance(1);
        this.setMaxY(255); // No height limit by default.
        this.setStrengthFactor(0f); // The higher the value, the more there will be.
        this.setSandOnly(false);
        this.setSoilBlock(Blocks.SAND.getDefaultState());

        this.addDecoTypes(DecoType.CACTUS);
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), CACTUS)) {

                WorldGenerator worldGenerator = new WorldGenCacti(this.sandOnly, 0, this.soilBlock);

                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
                for (int i = 0; i < loopCount * 10; i++) {
                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = worldZ + rand.nextInt(16);// + 8;

                    if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        worldGenerator.generate(rtgWorld.world(), rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }

    public int getLoops() {

        return loops;
    }

    public DecoCactus setLoops(int loops) {

        this.loops = loops;
        return this;
    }

    public int getChance() {

        return chance;
    }

    public DecoCactus setChance(int chance) {

        this.chance = chance;
        return this;
    }

    public float getStrengthFactor() {

        return strengthFactor;
    }

    public DecoCactus setStrengthFactor(float strengthFactor) {

        this.strengthFactor = strengthFactor;
        return this;
    }

    public int getMaxY() {

        return maxY;
    }

    public DecoCactus setMaxY(int maxY) {

        this.maxY = maxY;
        return this;
    }

    public boolean isSandOnly() {

        return sandOnly;
    }

    public DecoCactus setSandOnly(boolean sandOnly) {

        this.sandOnly = sandOnly;
        return this;
    }

    public IBlockState getSoilBlock() {

        return soilBlock;
    }

    public DecoCactus setSoilBlock(IBlockState soilBlock) {

        this.soilBlock = soilBlock;
        return this;
    }
}
