package rtg.world;

import net.minecraft.world.World;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.ChunkProviderGenerate;

import rtg.world.biome.WorldChunkManagerRTG;
import rtg.world.gen.ChunkProviderRTG;

public class WorldTypeRTG extends WorldType {

    public WorldTypeRTG(String name) {

        super("RTG");

//        DimensionManager.unregisterProviderType(0);
//        DimensionManager.registerProviderType(0, WorldProviderSurfaceRTG.class, true);
    }

    @Override
    public WorldChunkManager getChunkManager(World world) {

        if (world.provider.getDimensionId() == 0) {
            return new WorldChunkManagerRTG(world, this);
        }
        else {
            return new WorldChunkManager(world);
        }
    }

    @Override
    public IChunkProvider getChunkGenerator(World world, String generatorOptions) {

        if (world.provider.getDimensionId() == 0) {
            return new ChunkProviderRTG(world, world.getSeed());
        }
        else {
            return new ChunkProviderGenerate(world, world.getSeed(), world.getWorldInfo().isMapFeaturesEnabled(), generatorOptions);
        }
    }

    @Override
    public float getCloudHeight() {

        return 256F;
    }
}
