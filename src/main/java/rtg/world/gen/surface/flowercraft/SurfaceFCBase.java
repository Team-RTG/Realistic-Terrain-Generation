package rtg.world.gen.surface.flowercraft;

import net.minecraft.block.Block;
import rtg.api.biome.BiomeConfig;
import rtg.world.gen.surface.SurfaceBase;


public class SurfaceFCBase extends SurfaceBase {

    public SurfaceFCBase(BiomeConfig config, Block top, byte topMeta, Block fill, byte fillMeta) {
        super(config, top, topMeta, fill, fillMeta);
    }
}