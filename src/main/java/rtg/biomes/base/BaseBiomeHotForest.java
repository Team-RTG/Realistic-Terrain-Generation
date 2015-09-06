package rtg.biomes.base;

import rtg.data.VillageMaterialData;
import rtg.data.VillageMaterials;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.init.Blocks;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeHotForest extends BiomeGenBase
{
	public BaseBiomeHotForest(int id, String bn) 
	{
		super(id);
		setTemperatureRainfall(0.8f, 0.2f);
		setBiomeName(bn);
		setDisableRain();
		
		VillageMaterialData vmd = new VillageMaterialData(this);
		vmd.plankBlock = Blocks.planks;
		vmd.plankBlockMeta = 4;
		vmd.logBlock = Blocks.log2;
		vmd.logBlockMeta = 0;
		vmd.pathBlock = Blocks.cobblestone;
		vmd.stairsWoodBlock = Blocks.acacia_stairs;
		vmd.slabsBlock = Blocks.fence;
		VillageMaterials.registerVillageMaterial(vmd);
	}
	
    @SideOnly(Side.CLIENT)
    public int getBiomeGrassColor(int i, int dont, int care)
    {
        return ColorizerGrass.getGrassColor(1f, 0f);
    }

    @SideOnly(Side.CLIENT)
    public int getBiomeFoliageColor(int i, int dont, int care)
    {
        return ColorizerFoliage.getFoliageColor(0.8f, 0.2f);
    }
}
