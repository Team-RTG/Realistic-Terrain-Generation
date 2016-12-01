package rtg.world.biome.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.DEAD_BUSH;

import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 * @author WhichOnesPink
 */
public class DecoDeadBush extends DecoBase {

    public float strengthFactor;
    public int maxY;
    public int chance;
    public int loops;

    public DecoDeadBush() {

        super();

        /**
         * Default values.
         * These can be overridden when configuring the Deco object in the realistic biome.
         */
        this.maxY = 255; // No height limit by default.
        this.strengthFactor = 0f; // The higher the value, the more there will be.
        this.chance = 1;
        this.loops = 1;

        this.addDecoTypes(DecoType.DEAD_BUSH);
    }

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(worldX, 0, worldZ), DEAD_BUSH)) {

                WorldGenerator worldGenerator = new WorldGenDeadBush();

                int loopCount = this.loops;
                loopCount = (this.strengthFactor > 0f) ? (int) (this.strengthFactor * strength) : loopCount;
                for (int i = 0; i < loopCount; i++) {
                    int intX = worldX + rand.nextInt(16);// + 8;
                    int intY = rand.nextInt(this.maxY);
                    int intZ = worldZ + rand.nextInt(16);// + 8;

                    if (intY <= this.maxY && rand.nextInt(this.chance) == 0) {
                        worldGenerator.generate(rtgWorld.world, rand, new BlockPos(intX, intY, intZ));
                    }
                }
            }
        }
    }
}