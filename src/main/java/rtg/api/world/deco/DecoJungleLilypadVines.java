package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;

import net.minecraftforge.event.terraingen.TerrainGen;
import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.LILYPAD;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.gen.feature.WorldGenVinesRTG;

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
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), LILYPAD)) {

                WorldGenerator worldgeneratorLilypads = new WorldGenWaterlily();
                WorldGenerator worldgeneratorVines = new WorldGenVinesRTG();
                Block vb;

                for (int b33 = 0; b33 < 5; b33++) {
                    int j6 = worldX + rand.nextInt(16) + 8;
                    int k10 = worldZ + rand.nextInt(16) + 8;
                    int z52 = rtgWorld.world().getHeight(new BlockPos(j6, 0, k10)).getY();

                    for (int h44 = 0; h44 < 8; h44++) {

                        if (z52 > 64) {
                            worldgeneratorLilypads.generate(rtgWorld.world(), rand, new BlockPos(j6, z52, k10));
                        }
                    }

                    for (int h44 = 100; h44 > 0; h44--) {

                        worldgeneratorVines.generate(rtgWorld.world(), rand, new BlockPos(j6, z52, k10));
                    }
                }
            }
        }
    }
}
