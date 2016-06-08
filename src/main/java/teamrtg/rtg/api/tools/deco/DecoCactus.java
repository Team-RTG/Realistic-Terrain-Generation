package teamrtg.rtg.api.tools.deco;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenCacti;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CACTUS;

/**
 * @author WhichOnesPink
 */
public class DecoCactus extends DecoBase {

    public int loops;
    public int chance;
    public float strengthFactor;
    public int maxY;
    public boolean sandOnly;
    /**
     * Block to place under cactus. Sand, Red sand and coarse dirt are possibilities.
     */
    public IBlockState soil;

    public DecoCactus() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.loops = 1;
        this.chance = 1;
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // The higher the value, the more there will be.
        this.sandOnly = false;
        this.soil = Blocks.SAND.getDefaultState();

        this.addDecoTypes(DecoType.CACTUS);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 0, chunkY), CACTUS)) {

                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = chunkX + rand.nextInt(16) + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = chunkY + rand.nextInt(16) + 8;

                    if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        (new WorldGenCacti(this.sandOnly, 0, soil)).generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}
