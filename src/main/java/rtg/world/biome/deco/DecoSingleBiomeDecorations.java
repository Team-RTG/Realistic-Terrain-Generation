
package rtg.world.biome.deco;

import java.util.Random;
import net.minecraft.util.math.ChunkPos;
import rtg.api.world.RTGWorld;
import rtg.world.biome.realistic.RealisticBiomeBase;

/**
 *
 * @author Zeno410
 */
public class DecoSingleBiomeDecorations extends DecoBaseBiomeDecorations {

    @Override
    public void generate(RealisticBiomeBase biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {
        if (this.allowed) {
            // skip if already decorated
            ChunkPos position = new ChunkPos(worldX,worldZ);
            if (rtgWorld.decoratedChunks.contains(position)) return;
            rtgWorld.decoratedChunks.add(position);
            super.generate(biome, rtgWorld, rand, worldX, worldZ, strength, river, hasPlacedVillageBlocks); 
        }
    }

}
