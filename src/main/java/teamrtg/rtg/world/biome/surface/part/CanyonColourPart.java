package teamrtg.rtg.world.biome.surface.part;

import net.minecraft.world.chunk.ChunkPrimer;
import teamrtg.rtg.api.biome.RealisticBiomeBase;
import teamrtg.rtg.util.math.CanyonColour;

import static teamrtg.rtg.util.math.MathUtils.globalToLocal;

/**
 * @author topisani
 */
public class CanyonColourPart extends SurfacePart {

    public final CanyonColour colour;

    public CanyonColourPart(RealisticBiomeBase biome, CanyonColour colour) {
        super(biome);
        this.colour = colour;
    }

    @Override
    public void paintSurface(ChunkPrimer primer, int x, int y, int z, int depth, float[] noise, float river) {
        primer.setBlockState(globalToLocal(x), y, globalToLocal(z), colour.getAt(x, y, z));
    }
}
