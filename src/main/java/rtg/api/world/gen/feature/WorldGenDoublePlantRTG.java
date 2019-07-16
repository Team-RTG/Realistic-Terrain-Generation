package rtg.api.world.gen.feature;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.world.gen.feature.WorldGenDoublePlant;

// A helpful subclass that sets the plant type at object creation without needing a sepatate call to WorldGenDoublePlant#setPlantType
public class WorldGenDoublePlantRTG extends WorldGenDoublePlant
{
    public WorldGenDoublePlantRTG(final BlockDoublePlant.EnumPlantType type) {
        super();
        super.setPlantType(type);
    }
}