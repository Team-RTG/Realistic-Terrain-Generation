package rtg.world.biome.realistic.chromaticraft;

import rtg.config.chromaticraft.ConfigCC;
import rtg.world.biome.BiomeBase;
import rtg.world.biome.realistic.RealisticBiomeBase;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.terrain.TerrainBase;
import cpw.mods.fml.common.Loader;

import net.minecraft.world.biome.BiomeGenBase;

public class RealisticBiomeCCBase extends RealisticBiomeBase
{	
    public static RealisticBiomeBase ccEnderForest;
    public static RealisticBiomeBase ccRainbowForest;
    
	public RealisticBiomeCCBase(BiomeGenBase b, BiomeGenBase riverbiome, TerrainBase t, SurfaceBase s)
	{
		super(b, riverbiome, t, s);
		
        this.waterLakeFrequency = 0;
        this.lavaLakeFrequency = 0;
	}
	
	public static void addBiomes()
	{
		if (Loader.isModLoaded("ChromatiCraft") && ConfigCC.generateCCBiomes)
		{
			BiomeGenBase[] b = BiomeGenBase.getBiomeGenArray();
			
			for(int i = 0; i < 256; i++)
			{
				if(b[i] != null)
				{
					BiomeGenBase ccBiome = b[i];
					String biomeName = ccBiome.biomeName;
					String biomeClass = ccBiome.getBiomeClass().getName();
					
					if (biomeName == "Ender Forest" && biomeClass == "Reika.ChromatiCraft.World.BiomeEnderForest")
					{
						if (ConfigCC.generateCCEnderForest) {
						    
						    ccEnderForest = new RealisticBiomeCCEnderForest(ccBiome);
						    
						    BiomeBase.addBiome(ccEnderForest);
						    BiomeBase.addVillageBiome(ccEnderForest);
						}
					}
					else if (biomeName == "Rainbow Forest" && biomeClass == "Reika.ChromatiCraft.World.BiomeRainbowForest")
					{
						if (ConfigCC.generateCCRainbowForest) {
						    
						    ccRainbowForest = new RealisticBiomeCCRainbowForest(ccBiome);
						    
						    BiomeBase.addBiome(ccRainbowForest);
						    BiomeBase.addVillageBiome(ccRainbowForest);
						}
					}
				}
			}
		}
	}
}
