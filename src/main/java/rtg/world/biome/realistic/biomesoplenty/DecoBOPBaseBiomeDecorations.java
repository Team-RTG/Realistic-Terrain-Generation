package rtg.world.biome.realistic.biomesoplenty;

import java.util.Random;

import net.minecraft.util.math.ChunkPos;

import rtg.api.world.RTGWorld;
import rtg.api.world.biome.IRealisticBiome;
import rtg.api.world.deco.DecoBaseBiomeDecorations;

/**
 *
 * @author WhichOnesPink
 */
public class DecoBOPBaseBiomeDecorations extends DecoBaseBiomeDecorations {

    public DecoBOPBaseBiomeDecorations() {
        super();
    }

    @Override
    public void generate(IRealisticBiome biome, RTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed && biome instanceof IRealisticBOPBiome) {

            // skip if already decorated (@author Zeno410, lifted from DecoSingleBiomeDecorations)
            ChunkPos position = new ChunkPos(worldX,worldZ);
            if (rtgWorld.decoratedChunks.contains(position)) return;
            rtgWorld.decoratedChunks.add(position);

            IRealisticBOPBiome bopBiome = (IRealisticBOPBiome) biome;
            bopBiome.addBOPGenerators();
            super.generate(biome, rtgWorld, rand, worldX, worldZ, strength, river, hasPlacedVillageBlocks);
            bopBiome.removeBOPGenerators();
        }
    }
}