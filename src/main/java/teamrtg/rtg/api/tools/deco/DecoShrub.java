package teamrtg.rtg.api.tools.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.tree.WorldGenTreeRTGShrub;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

/**
 * @author WhichOnesPink
 */
public class DecoShrub extends DecoBase {

    public int size; // Higher = bigger shrubs.
    public int LOG; // Yes, this is an integer, not a block. Voooodooooo.
    public int LEAVES; // Yes, this is an integer, not a block. Voooodooooo.
    public float strengthFactor; // Higher = more/bigger shrubs.
    public int maxY; // Height restriction.
    public int chance; // Higher = more rare.
    public int loops;

    public DecoShrub() {
        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.size = -1; // Voodoo by default. (See WorldGenTreeRTGShrub if you dare.)
        this.LOG = -1; // Voodoo by default. (See WorldGenTreeRTGShrub if you dare.)
        this.LEAVES = -1; // Voodoo by default. (See WorldGenTreeRTGShrub if you dare.)
        this.strengthFactor = 3f; // Not sure why it was done like this, but... the higher the value, the more there will be.
        this.maxY = 255; // No height limit by default.
        this.chance = 1; // 100% chance of generating by default.
        this.loops = 1;

        this.addDecoTypes(DecoType.SHRUB);
    }

    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator mapGenGenerator) {
        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX, 1, chunkY), TREE)) {

                // Voodoo unless explicitly configured.
                this.size = (this.size == -1) ? rand.nextInt(4) + 1 : this.size;
                this.LOG = (this.LOG == -1) ? 0 : this.LOG;
                this.LEAVES = (this.LEAVES == -1) ? rand.nextInt(3) : this.LEAVES;

                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = chunkX + rand.nextInt(16) + 8;
                    int intZ = chunkY + rand.nextInt(16) + 8;
                    int intY = rtgWorld.world.getHeight(new BlockPos(intX, 1, intZ)).getY();

                    if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        (new WorldGenTreeRTGShrub(this.size, this.LOG, this.LEAVES)).generate(rtgWorld.world, rand, intX, intY, intZ);
                    }
                }
            }
        }
    }
}
