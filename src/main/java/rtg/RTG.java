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
import rtg.config.ConfigRTG;
import rtg.config.RTGConfig;
import rtg.data.VillageMaterials;
import rtg.debug.DebugHandler;
import rtg.init.ModMapGen;
import rtg.proxy.CommonProxy;
import rtg.reference.ModInfo;
import rtg.world.WorldTypeRTG;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.biomesoplenty.RealisticBiomeBOPBase;
import rtg.world.biome.realistic.enhancedbiomes.RealisticBiomeEBBase;
import rtg.world.biome.realistic.extrabiomes.RealisticBiomeEBXLBase;
import rtg.world.biome.realistic.highlands.RealisticBiomeHighlandsBase;
import rtg.world.biome.realistic.thaumcraft.RealisticBiomeTCBase;
import rtg.world.biome.realistic.vanilla.RealisticBiomeVanillaBase;

@Mod(modid=ModInfo.MOD_ID, name=ModInfo.MOD_NAME, version=ModInfo.MOD_VERSION, acceptableRemoteVersions="*")
public class RTG
{	
	@Instance("RTG")
	public static RTG instance;
	
	public static String configPath;
	
	public static final WorldTypeRTG worldtype = (new WorldTypeRTG("RTG"));  
	
	@SidedProxy(serverSide = ModInfo.PROXY_COMMON, clientSide = ModInfo.PROXY_CLIENT)
	public static CommonProxy proxy;
	
	@EventHandler
	public void preInit(FMLPreInitializationEvent event) 
	{
		instance = this;
		
		configPath = event.getModConfigurationDirectory() + "/RTG/";
		RTGConfig.init(configPath);
				
		MinecraftForge.TERRAIN_GEN_BUS.register(new VillageMaterials());
		
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
		BiomeBase.init();
		
		RealisticBiomeVanillaBase.addBiomes();
		RealisticBiomeBOPBase.addBiomes();
		RealisticBiomeEBBase.addBiomes();
		RealisticBiomeEBXLBase.addBiomes();
		RealisticBiomeHighlandsBase.addBiomes();
		RealisticBiomeTCBase.addBiomes();
	}
}