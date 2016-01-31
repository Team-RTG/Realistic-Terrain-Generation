package code.elix_x.coremods.antiidconflict.api;

import cpw.mods.fml.common.Loader;
import cpw.mods.fml.relauncher.ReflectionHelper;
import net.minecraft.world.chunk.Chunk;

/**
 * This class wraps all methods that were changed (name or signature) in vanilla by AIC.
 * Methods here should be used instead of vanilla's, when AIC is loaded, or otherwise your calls to vanilla methods will be redirected and voided to not cause any crashes.
 * <br>
 * This part of AIC API can be redistributed freely, but not modified.
 * @author elix_x
 */
public class AICChangesWrapper {

	/**
	 * Simple method to check if AIC is loaded.
	 * @return if AIC is loaded or not.
	 */
	public static boolean isAICLoaded(){
		return Loader.isModLoaded("AIC");
	}

	/**
	 * Wrapper method to get chunk biomes.
	 * Use this instead of {@link Chunk#getBiomeArray()}.
	 * @param chunk {@link Chunk} to get biomes from.
	 * @return Array of biomes in specified chunk.
	 */
	public static int[] getBiomeArray(Chunk chunk){
		try {
			return (int[]) ReflectionHelper.findMethod(Chunk.class, chunk, new String[]{"getBiomeArray", "func_76605_m"}).invoke(chunk);
		} catch (Exception e){
			return new int[256];
		}
	}

	/**
	 * Wrapper method to set chunk biomes.
	 * Use this instead of {@link Chunk#setBiomeArray(byte[])}.
	 * @param chunk {@link Chunk} to set biomes in.
	 * @param biomes Array of biomes to set in specified chunk.
	 */
	public static void setBiomeArray(Chunk chunk, int[] biomes){
		try {
			ReflectionHelper.findMethod(Chunk.class, chunk, new String[]{"setBiomeArray", "func_76616_a"}, int[].class).invoke(chunk, biomes);
		} catch (Exception e){

		}
	}

}
