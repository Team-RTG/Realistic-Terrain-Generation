package teamrtg.rtg.api.world.gen;

import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.world.RTGWorld;
import teamrtg.rtg.api.world.biome.IMapGen;
import teamrtg.rtg.api.world.biome.deco.DecoBase;
import teamrtg.rtg.api.world.biome.deco.DecoBaseBiomeDecorations;

import java.util.ArrayList;
import java.util.Random;

/**
 * @author topisani
 */
public interface IMapGenGenerator {

    IMapGen get();

    void paintSurface(ChunkPrimer primer, int bx, int by, int bz, int depth, float[] noise, float river, RTGWorld rtgWorld);

    default void decorate(RTGWorld rtgWorld, Random rand, int chunkY, int chunkX, float strength, float river) {
        boolean baseDecorated = false;
        ArrayList<DecoBase> decos = this.get().getDecos();
        for (int i = decos.size() - 1; i >= 0; i--) {
            DecoBase deco = decos.get(i);
            if (deco instanceof DecoBaseBiomeDecorations) {
                if (baseDecorated) continue;
                baseDecorated = true;
            }
            if (deco.preGenerate(rtgWorld, rand, chunkX, chunkY, strength, river, this)) {
                deco.generate(rtgWorld, rand, chunkX, chunkY, strength, river, this);
            }
        }
        // Generate ores
        if (!baseDecorated) {
            DecoBaseBiomeDecorations deco = new DecoBaseBiomeDecorations();
            deco.allowed = false;
            if (deco.preGenerate(rtgWorld, rand, chunkX, chunkY, strength, river, this)) {
                deco.generate(rtgWorld, rand, chunkX, chunkY, strength, river, this);
            }
        }
    }
}
