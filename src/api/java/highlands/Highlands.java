package highlands;

import highlands.api.HighlandsBiomes;
import highlands.api.HighlandsBlocks;

import java.io.File;

import net.minecraft.block.Block;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.Mod.EventHandler;
import cpw.mods.fml.common.Mod.Instance;
import cpw.mods.fml.common.SidedProxy;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLPostInitializationEvent;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;

@Mod(modid="Highlands", name="Highlands", version="2.2.0",
		dependencies = "after:Forestry;after:MineFactoryReloaded;after:Thaumcraft;after:BuildCraft|Transport")
public class Highlands {

	public static String modid = "Highlands";
	
    // The instance of your mod that Forge uses.
	@Instance("Highlands")
	public static Highlands instance;
	
	// Says where the client and server 'proxy' code is loaded.
	@SidedProxy(clientSide="highlands.CommonProxy", serverSide="highlands.CommonProxy")
	public static CommonProxy proxy;
	
	//Highlands Worldtypes
	public static WorldType HL;// = (WorldType) new WorldTypeHighlands("Highlands");
	public static WorldType HLLB;// = (WorldType) new WorldTypeHighlands("HighlandsLB");
	
	public static int HighlandsBiomeSizeDefault;
	public static int HighlandsBiomeSizeLB;
	
	public static boolean mocreaturescomp = false;
	public static boolean improvedOceans = true;
	public static boolean borderBiomeFlag = true;
	
	public static boolean skyColorFlag = false;
	public static boolean vanillaBlocksFlag = false;
	public static boolean plantsFlag = true;
	
	public static int islandRarity = 14;
    
	public static boolean addBoPbiomes = false;
    
    public static boolean useOreGens = true;
    public static boolean useGenLayers = true;
    
    public static CreativeTabs tabHighlands = new HighLandsCreativeTab();
    
    @EventHandler
	public void preInit(FMLPreInitializationEvent event) {
		//new settings set-up
		Configuration config = new Configuration(new File(event.getModConfigurationDirectory() + File.separator + "Highlands" + File.separator + "General.cfg"));
		config.load();
		Config.setUpConfig(config);
		config.save();
		
		Initializer.constructBlocks();
		Initializer.initRecipes();
		for (Block block : HighlandsBlocks.logs)
			Initializer.setFireProperties(block, 5, 5);
		for (Block block : HighlandsBlocks.leaves)
			Initializer.setFireProperties(block, 30, 60);
		for (Block block : HighlandsBlocks.plants)
			Initializer.setFireProperties(block, 60, 100);
		for (Block block : HighlandsBlocks.planks)
			Initializer.setFireProperties(block, 5, 20);
	}
	
	@EventHandler
	public void load(FMLInitializationEvent event) {
		//construct all variables
		HighlandsBiomes.initBiomeArrays();
		Initializer.constructBiomes();
		Initializer.constructSettings();
		
		//set up sub-biomes
		Initializer.setUpAllSubBiomes();
		
		//register event manager
		MinecraftForge.TERRAIN_GEN_BUS.register(new HighlandsEventManager());
		MinecraftForge.EVENT_BUS.register(new HighlandsEventManager());
		
		// allow player spawning in biomes
		for(BiomeGenBase i : HighlandsBiomes.biomesForDefault){
			WorldChunkManager.allowedBiomes.add(i);
		}
		if(WorldChunkManager.allowedBiomes.size() == 0)WorldChunkManager.allowedBiomes.add(HighlandsBiomes.ocean2);
		
		//initiate all recipes and ore dictionary definitions
		
		
		
		
		proxy.registerRenderers();
	}
	
	@EventHandler
	public void postInit(FMLPostInitializationEvent event) {

	}	
}