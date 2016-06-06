package teamrtg.rtg.api.tools.deco;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.api.tools.feature.WorldGenClay;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.gen.RealisticBiomeGenerator;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.CLAY;

/**
 * @author topisani
 */
public class DecoClay extends DecoBase {

    public int clayPerVein = 20;

    /**
     * Generates the decoration.
     * The parameters are virtually the same as the ones passed to the legacy rDecorate() method.
     * This method should be overridden in the individual deco objects.
     * @param rand
     * @param chunkX
     * @param chunkY
     * @param strength
     * @param river
     * @param realisticBiomeGenerator
     */
    @Override
    public void generate(RTGWorld rtgWorld, Random rand, int chunkX, int chunkY, float strength, float river, RealisticBiomeGenerator realisticBiomeGenerator) {
        if (TerrainGen.decorate(rtgWorld.world, rand, new BlockPos(chunkX * 16, 0, chunkY * 16), CLAY)) {

            if (river > 0.85f) {

                for (int j2 = 0; j2 < 3; j2++) {

                    int l5 = chunkX * 16 + rand.nextInt(16);
                    int i9 = 53 + rand.nextInt(15);
                    int l11 = chunkY * 16 + rand.nextInt(16);

                    (new WorldGenClay(Blocks.CLAY, 0, clayPerVein)).generate(rtgWorld.world, rand, new BlockPos(l5, i9, l11));
                }
            }
        }
    }
}
