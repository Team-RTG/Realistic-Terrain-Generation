package enhancedbiomes.blocks;

import static enhancedbiomes.blocks.BlockWithMeta.*;

public class LandTypes {
	public static BlockWithMeta[] typeGeoGrass;
	public static BlockWithMeta[] typeGeoSandCanyons;
	public static BlockWithMeta[] typeGeoWoodland;
	public static BlockWithMeta[] typeGeoWetland;
	public static BlockWithMeta[] typeGeoMountains;
	public static BlockWithMeta[] typeGeoVolcanoSea;
	public static BlockWithMeta[] typeGeoIce;

	public static BlockWithMeta[] typeGeoDefault;
	
	public static void createLandTypes() {
		typeGeoGrass = 			new BlockWithMeta[]{stone, 		chalk};
		typeGeoSandCanyons = 	new BlockWithMeta[]{sandstone, 	marble};
		typeGeoWoodland = 		new BlockWithMeta[]{slate, 		dolomite};
		typeGeoWetland = 		new BlockWithMeta[]{shale, 		schist};
		typeGeoMountains = 		new BlockWithMeta[]{limestone, 	chert};
		typeGeoVolcanoSea = 	new BlockWithMeta[]{basalt, 	gabbro};
		typeGeoIce = 			new BlockWithMeta[]{rhyolite, 	dacite};

		typeGeoDefault =		new BlockWithMeta[]{stone,	 	stone};
	}
}
