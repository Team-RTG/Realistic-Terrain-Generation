package rtg.world.biome.realistic.idt;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.idt.config.BiomeConfigIDT;
import rtg.util.Logger;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.registry.GameData;

public class RealisticBiomeIDTBase extends RealisticBiomeBase
{
	public static RealisticBiomeBase idtEbonyForest;
	public static RealisticBiomeBase idtSilkwoodForest;
	public static RealisticBiomeBase idtWillowForest;
    
    protected Block ebonyLogBlock = GameData.getBlockRegistry().getObject("mod_IDT:EbonyLog");
    protected byte ebonyLogMeta = (byte)0;
    protected Block ebonyLeavesBlock = GameData.getBlockRegistry().getObject("mod_IDT:EbonyLeaves");
    protected byte ebonyLeavesMeta = (byte)0;
    
    protected Block silkwoodLogBlock = GameData.getBlockRegistry().getObject("mod_IDT:SilkwoodLog");
    protected byte silkwoodLogMeta = (byte)0;
    protected Block silkwoodLeavesBlock = GameData.getBlockRegistry().getObject("mod_IDT:SilkwoodLeaves");
    protected byte silkwoodLeavesMeta = (byte)0;
    
    protected Block willowLogBlock = GameData.getBlockRegistry().getObject("mod_IDT:WillowLog");
    protected byte willowLogMeta = (byte)0;
    protected Block willowLeavesBlock = GameData.getBlockRegistry().getObject("mod_IDT:WillowLeaves");
    protected byte willowLeavesMeta = (byte)0;
    
    public RealisticBiomeIDTBase(BiomeConfig config, BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
    {
    
        super(config, b, riverbiome, t, s);
        
        this.waterSurfaceLakeChance = 0;
        this.lavaSurfaceLakeChance = 0;
    }
    
    public static void addBiomes()
    {
    
        if (Loader.isModLoaded("mod_IDT"))
        {
            BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
            
            for (int i = 0; i < 256; i++)
            {
                if (b[i] != null)
                {
                    if (b[i].biomeName == null) {
                        Logger.warn("Biome ID %d has no name.", b[i].biomeID);
                        continue;
                    }
                    
                    BiomeGenBase idtBiome = b[i];
                    String biomeName = b[i].biomeName;
                    String biomeClass = b[i].getBiomeClass().getName();
                    
                    if (biomeName == "Ebony Forest" && biomeClass == "com.mce.gen.biome.EbonyForest")
                    {
                    	idtEbonyForest = new RealisticBiomeIDTEbonyForest(idtBiome, BiomeConfigIDT.biomeConfigIDTEbonyForest);
                    }
                    else if (biomeName == "Silkwood Forest" && biomeClass == "com.mce.gen.biome.SilkwoodForest")
                    {
                    	idtSilkwoodForest = new RealisticBiomeIDTSilkwoodForest(idtBiome, BiomeConfigIDT.biomeConfigIDTSilkwoodForest);
                    }
                    else if (biomeName == "Willow Forest" && biomeClass == "com.mce.gen.biome.WillowForest")
                    {
                    	idtWillowForest = new RealisticBiomeIDTWillowForest(idtBiome, BiomeConfigIDT.biomeConfigIDTWillowForest);
                    }
                }
            }
        }
    }
}
