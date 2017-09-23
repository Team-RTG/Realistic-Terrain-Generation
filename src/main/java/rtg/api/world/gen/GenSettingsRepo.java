package rtg.api.world.gen;

import javax.annotation.Nullable;

import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
import net.minecraft.world.World;
import net.minecraftforge.common.DimensionManager;
import rtg.api.util.Logger;
import rtg.world.WorldTypeRTG;
import rtg.world.gen.ChunkProviderSettingsRTG;

/**
 * @author srs-bsns
 * @since 1.0.0
 */
public final class GenSettingsRepo {

    private GenSettingsRepo() {} // This is a utility class, so prevent instantiation.

    /**
     * A repository to store {@link rtg.world.gen.ChunkProviderSettingsRTG} instances for each {@link net.minecraft.world.World} loaded of type {@link rtg.world.WorldTypeRTG}.
     *
     * @since 1.0.0
     */
    private static final Int2ObjectOpenHashMap<ChunkProviderSettingsRTG> SETTINGS_REPOSITORY = new Int2ObjectOpenHashMap<>();

    /**
     * This method will retrieve an instance of ChunkProviderSettingsRTG specific to the World instance that is passed to it.
     * If an instance does not exist in the repository, one is first created.
     *
     * @param world This is the {@link net.minecraft.world.World} to get an {@link rtg.world.gen.ChunkProviderSettingsRTG} for.
     *
     * @return returns the {@link rtg.world.gen.ChunkProviderSettingsRTG} for the passed {@link net.minecraft.world.World} object.
     *
     * @since 1.0.0
     */
    public static ChunkProviderSettingsRTG getSettingsForWorld(final World world) {

        if (!SETTINGS_REPOSITORY.containsKey(world.provider.getDimension())) {
            addSettingsForWorld(world);
        }
        return SETTINGS_REPOSITORY.get(world.provider.getDimension());
    }

    /**
     * This method will retrieve an instance of {@link ChunkProviderSettingsRTG} for the {@link net.minecraft.world.World} of the passed Dimension ID, if one exists.
     * If an instance of {@link rtg.world.gen.ChunkProviderSettingsRTG} does not exist in the repository for the passed Dim ID,
     * a check is made against the World of the Dimension ID. If a World exists and the WorldType of the world is of WorldTypeRTG,
     * then a new instance of {@link rtg.world.gen.ChunkProviderSettingsRTG} is created for the World and added to the repository.
     * If anything fails, null is returned.
     *
     * @param dimID This is the Dimension ID to get an {@link rtg.world.gen.ChunkProviderSettingsRTG} for.
     *
     * @return returns the {@link rtg.world.gen.ChunkProviderSettingsRTG} instance for the passed Dimension ID (int), or returns null.
     *
     * @since 1.0.0
     */
    @Nullable
    public static ChunkProviderSettingsRTG getSettingsForDim(final int dimID) {

        if (SETTINGS_REPOSITORY.containsKey(dimID)) {
            return SETTINGS_REPOSITORY.get(dimID);
        }
        else {
            World world = DimensionManager.getWorld(dimID);
            if (world != null && world.getWorldType() instanceof WorldTypeRTG) {
                addSettingsForWorld(world);
                return SETTINGS_REPOSITORY.get(dimID);
            }
        }
        return null;
    }

    /**
     * This method adds a new {@link rtg.world.gen.ChunkProviderSettingsRTG} instance, for the passed {@link net.minecraft.world.World} object,
     * to the settings repository if one doesn't already exist, else an existing instance is replaced by a new one.
     *
     * @param world The {@link net.minecraft.world.World} to add a {@link rtg.world.gen.ChunkProviderSettingsRTG} instance for.
     *
     * @since 1.0.0
     */
    public static void addSettingsForWorld(final World world) {

        ChunkProviderSettingsRTG entry = SETTINGS_REPOSITORY.put(
            world.provider.getDimension(),
            ChunkProviderSettingsRTG.Factory.jsonToFactory(world.getWorldInfo().getGeneratorOptions()).build()
        );

        if (entry == null) {
            Logger.debug("Adding a new entry in the generator settings repository for dimension {}", world.provider.getDimension());
        }
        else {
            Logger.debug("Replacing the entry in the generator settings repository for dimension {}", world.provider.getDimension());
        }
    }

//    /**
//     * This method removes an {@link rtg.world.gen.ChunkProviderSettingsRTG} instance from the settings repository.
//     *
//     * @param world
//     *     The {@link net.minecraft.world.World} to remove an {@link rtg.world.gen.ChunkProviderSettingsRTG} instance for.
//     *
//     * @since 1.0.0
//     */
//    public static void removeSettingsForWorld(final World world) {
//
//        ChunkProviderSettingsRTG entry = SETTINGS_REPOSITORY.remove(world.provider.getDimension());
//
//        if (entry == null) {
//            Logger.error("Attempted to remove a non-existant entry in the generator settings repository for dimension {}", world.provider.getDimension());
//        }
//        else {
//            Logger.debug("Removing the entry in the generator settings repository for dimension {}", world.provider.getDimension());
//        }
//    }
}
