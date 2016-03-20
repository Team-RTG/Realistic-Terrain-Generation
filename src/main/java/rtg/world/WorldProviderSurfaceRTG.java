package rtg.world;

import net.minecraft.world.DimensionType;
import net.minecraft.world.WorldProviderSurface;

public class WorldProviderSurfaceRTG extends WorldProviderSurface {
    @Override
    public DimensionType getDimensionType() {
        return DimensionType.OVERWORLD;
    }
}