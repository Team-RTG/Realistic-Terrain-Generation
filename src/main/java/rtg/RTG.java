package rtg;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.DimensionManager;
import net.minecraftforge.common.MinecraftForge;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import cpw.mods.fml.relauncher.Side;
import rtg.biomes.base.BaseBiomes;
import rtg.biomes.vanilla.VanillaBiomes;
import rtg.config.ConfigRTG;
import rtg.data.TreeReplacement;
import rtg.data.VillageMaterials;
import rtg.debug.DebugHandler;
import rtg.init.ModMapGen;
import rtg.proxy.CommonProxy;
import rtg.reference.ModInfo;
import rtg.support.Support;
import rtg.world.WorldTypeRealistic;

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION, acceptableRemoteVersions="*")
public class RTG
{	
	@Instance("RTG")
	public static RTG instance;
	
	public static final WorldTypeRealistic worldtype = (new WorldTypeRealistic("RTG"));  
	
	@SidedProxy(serverSide = ModInfo.PROXY_COMMON, clientSide = ModInfo.PROXY_CLIENT)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		ConfigRTG.init(event);
				
		BaseBiomes.load();
				
		MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterials());
		//MinecraftForge.TERRAIN_GEN_BUS.register(new TreeReplacement());
		
		ModMapGen.registerMapGen();
	}
	
	@EventHandler
	public void Init(FMLInitializationEvent event)
	{
		if ( event.getSide() == Side.CLIENT ) {
			MinecraftForge.EVENT_BUS.register(new DebugHandler());
		}
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event)
	{
		Support.init();
	}
}