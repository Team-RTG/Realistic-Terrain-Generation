package teamrtg.rtg.world.gen.deco;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import teamrtg.rtg.util.noise.CellNoise;
import teamrtg.rtg.util.noise.OpenSimplexNoise;
import teamrtg.rtg.world.gen.RealisticBiomeGenerator;
import teamrtg.rtg.world.gen.feature.WorldGenVinesRTG;

import java.util.Random;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

/**
 * @author WhichOnesPink
 */
public class DecoJungleLilypadVines extends DecoBase {

    public DecoJungleLilypadVines() {
        super();

        this.addDecoTypes(DecoType.LILYPAD, DecoType.VINE);
    }

    /**
     * No config options for this one yet. Just ripped it directly from the old code.
     */
    @Override
    public void generate(RealisticBiomeGenerator biomeGenerator, World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river) {
        if (this.allowed) {

            if (TerrainGen.decorate(world, rand, new BlockPos(chunkX, 0, chunkY), LILYPAD)) {

                for (int b33 = 0; b33 < 5; b33++) {
                    int j6 = chunkX + rand.nextInt(16) + 8;
                    int k10 = chunkY + rand.nextInt(16) + 8;
                    int z52 = world.getHeight(new BlockPos(j6, 1, k10)).getY();

                    for (int h44 = 0; h44 < 8; h44++) {

                        if (z52 > 64) {

                            WorldGenerator worldgenerator2 = new WorldGenWaterlily();
                            worldgenerator2.generate(world, rand, new BlockPos(j6, z52, k10));
                        }
                    }

                    for (int h44 = 0; h44 < 100; h44++) {
                        WorldGenerator worldgenerator4 = new WorldGenVinesRTG();
                        worldgenerator4.generate(world, rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
        }
    }
}
