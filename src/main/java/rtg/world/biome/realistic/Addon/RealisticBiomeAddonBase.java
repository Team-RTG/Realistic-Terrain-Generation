package rtg.world.biome.realistic.addon;

import cpw.mods.fml.common.Loader;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.RTG;
import rtg.config.addon.ConfigAddon;
import rtg.config.vanilla.ConfigVanilla;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.biome.realistic.addon.RealisticBiomeAddonBase;
import rtg.world.biome.realistic.addon.*;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;

public class RealisticBiomeAddonBase extends RealisticBiomeBase
{	
	
	//Snow
	public static RealisticBiomeBase polar = new RealisticBiomeAddonPolar();
	
	//Cold
	public static RealisticBiomeBase darkRedwoodPlains = new RealisticBiomeAddonDarkRedwoodPlains();
	
	//Hot
	public static RealisticBiomeBase hotForest = new RealisticBiomeAddonHotForest();
	public static RealisticBiomeBase redDesertMountains = new RealisticBiomeAddonRedDesertMountains();
	public static RealisticBiomeBase redOasis = new RealisticBiomeAddonRedOasis();
	public static RealisticBiomeBase savannaForest = new RealisticBiomeAddonSavannaForest();
	public static RealisticBiomeBase duneValleyForest = new RealisticBiomeAddonDuneValleyForest();	
	
	//Wet
	public static RealisticBiomeBase canyonForest = new RealisticBiomeAddonCanyonForest();
	public static RealisticBiomeBase jungleCanyon = new RealisticBiomeAddonJungleCanyon();
	public static RealisticBiomeBase mesaPlains = new RealisticBiomeAddonMesaPlains();
	
	
	public RealisticBiomeAddonBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
		this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		/*
		darkRedwoodPlains = new RealisticBiomeAddonDarkRedwoodPlains();
		duneValleyForest = new RealisticBiomeAddonDuneValleyForest();
		hotForest = new RealisticBiomeAddonHotForest();
		jungleCanyon = new RealisticBiomeAddonJungleCanyon();
		mesaPlains = new RealisticBiomeAddonMesaPlains();
		polar = new RealisticBiomeAddonPolar();
		redDesertMountains = new RealisticBiomeAddonRedDesertMountains();
		redOasis = new RealisticBiomeAddonRedOasis();
		savannaForest = new RealisticBiomeAddonSavannaForest(); 
		*/
		if (ConfigAddon.generateAddonBiomes)
		{
			
			
			if (ConfigAddon.generateCanyonForest) { BiomeBase.addBiome(canyonForest); }
			if (ConfigAddon.generateDarkRedwoodPlains) { BiomeBase.addBiome(darkRedwoodPlains); }
			if (ConfigAddon.generateDuneValleyForest) { BiomeBase.addBiome(duneValleyForest); }
			if (ConfigAddon.generateHotForest) { BiomeBase.addBiome(hotForest); }
			if (ConfigAddon.generateJungleCanyon) { BiomeBase.addBiome(jungleCanyon); }
			if (ConfigAddon.generateMesaPlains) { BiomeBase.addBiome(mesaPlains); }
			if (ConfigAddon.generatePolar) { BiomeBase.addBiome(polar); }
			if (ConfigAddon.generateRedDesertMountains) { BiomeBase.addBiome(redDesertMountains); }
			if (ConfigAddon.generateRedOasis) { BiomeBase.addBiome(redOasis); }
			if (ConfigAddon.generateSavannaForest) { BiomeBase.addBiome(savannaForest); }	
			
			if (ConfigAddon.villageDarkRedwoodPlains) { BiomeBase.addVillageBiome(darkRedwoodPlains); }
			if (ConfigAddon.villageDuneValleyForest) { BiomeBase.addVillageBiome(duneValleyForest); }
			if (ConfigAddon.villageHotForest) { BiomeBase.addVillageBiome(hotForest); }
			if (ConfigAddon.villageMesaPlains) { BiomeBase.addVillageBiome(mesaPlains); }
			if (ConfigAddon.villageRedOasis) { BiomeBase.addVillageBiome(redOasis); }
			if (ConfigAddon.villageSavannaForest) { BiomeBase.addVillageBiome(savannaForest); }
			 
		}
	}	
	
	public static RealisticBiomeBase getRealisticVanillaBiomeFromVanillaVariableName(String name)
	{
		if (name.equals("polar")) { return RealisticBiomeAddonBase.polar; }
		else if (name.equals("darkRedwoodPlains")) { return RealisticBiomeAddonBase.darkRedwoodPlains; }
		else if (name.equals("hotForest")) { return RealisticBiomeAddonBase.hotForest; }
		else if (name.equals("redDesertMountains")) { return RealisticBiomeAddonBase.redDesertMountains; }
		else if (name.equals("redOasis")) { return RealisticBiomeAddonBase.redOasis; }
		else if (name.equals("savannaForest")) { return RealisticBiomeAddonBase.savannaForest; }
		else if (name.equals("duneValleyForest")) { return RealisticBiomeAddonBase.duneValleyForest; }
		else if (name.equals("canyonForest")) { return RealisticBiomeAddonBase.canyonForest; }
		else if (name.equals("jungleCanyon")) { return RealisticBiomeAddonBase.jungleCanyon; }
		else if (name.equals("mesaPlains")) { return RealisticBiomeAddonBase.mesaPlains; }

		
		return null;
	}
}