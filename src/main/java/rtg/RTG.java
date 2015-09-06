package rtg;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import rtg.biomes.base.BaseBiomes;
import rtg.config.ConfigRTG;
import rtg.data.TreeReplacement;
import rtg.data.VillageMaterials;
import rtg.support.Support;
import rtg.world.WorldTypeRealistic;

@Mod(modid="RTG", name="RealisticWorldGen", version="Alpha 1.3.2", acceptableRemoteVersions="*")
public class RTG
{	
	@Instance("RTG")
	public static RTG instance;
	
	public static final WorldTypeRealistic worldtype = (new WorldTypeRealistic("RTG"));  
	
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		ConfigRTG.init(event);
		BaseBiomes.load();
		
		MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterials());
		//MinecraftForge.TERRAIN_GEN_BUS.register(new TreeReplacement());
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{

	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Support.init();
	}
}