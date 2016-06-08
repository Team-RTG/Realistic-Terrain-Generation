package teamrtg.rtg.api.util.math;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import teamrtg.rtg.api.util.noise.IBlockAt;
import teamrtg.rtg.api.world.RTGWorld;

import java.util.HashMap;
import java.util.Map;

public enum CanyonColour implements IBlockAt {
    MESA(new byte[] {-1, -1, -1, 1, 1, 1, 0, -1, -1, 6, 1, 1, 8, 0, -1, -1, 14, -1, -1, 6, 1, 1, 4}),
    MESA_WHITE(new byte[] {-1, -1, 0, 1, 0, 0, 0, 14, 0, 8, 0, 1, 8, 0, -1, 0, 14, 0, 0, 14, 0, 0, 8}),
    SAVANNA(new byte[] {0, 0, 0, 0, 8, 8, 12, 12, 8, 0, 8, 12, 12, 8, 12, 8, 0, 0, 8, 12, 12,});

    // If you remove that U, i will locate and dismember you.
    private static Map<CanyonColour, IBlockState[]> colours = new HashMap<>();
    public byte[] bytes;

    CanyonColour(byte[] bytes) {
        this.bytes = bytes;
    }

    public static void init() {
        for (CanyonColour colour : CanyonColour.values()) {
            IBlockState[] c = new IBlockState[256];
            int j;
            for (int i = 0; i < 256; i++) {
                byte b = colour.bytes[i % colour.bytes.length];
                c[i] = (b == -1) ? Blocks.HARDENED_CLAY.getDefaultState() : Blocks.STAINED_HARDENED_CLAY.getStateFromMeta(b);
            }
            colours.put(colour, c);
        }
    }

    public IBlockState getAt(int x, int y, int z, RTGWorld rtgWorld) {
        float y1 = y + (rtgWorld.simplex.noise3((float) x / 80f, (float) y / 6, (float) z / 80f) * 3f);
        y1 += rtgWorld.simplex.noise2((float) x / 70f, (float) z / 70f) * 3f;
        y1 = (y1 < 0) ? 0 : (y1 > 255) ? 255 : y1;
        return colours.get(this)[Math.round(y1)];
    }
}
