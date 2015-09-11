package rtg.init;

import rtg.event.TerrainGenHandler;
import rtg.world.gen.structure.MapGenScatteredFeatureModBiomes;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.common.MinecraftForge;

/**
 * Author: Choonster (https://github.com/Choonster)
 * Source: https://github.com/Choonster/TestMod2/blob/1575b85ad8949381215f3aeb6ca76ea2368074de/src/main/java/com/choonster/testmod2/init/ModMapGen.java
 */
public class ModMapGen {
	public static void registerMapGen() {
		MapGenStructureIO.registerStructure(MapGenScatteredFeatureModBiomes.Start.class, "rtg_MapGenScatteredFeatureModBiomes");

		MinecraftForge.TERRAIN_GEN_BUS.register(new TerrainGenHandler());
	}
}