package rtg.biomes.base;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.world.ColorizerFoliage;
import net.minecraft.world.ColorizerGrass;
import net.minecraft.world.biome.BiomeGenBase;

public class BaseBiomeTropicalIsland extends BiomeGenBase
{
	public BaseBiomeTropicalIsland(int id, String bn) 
	{
		super(id);
		setTemperatureRainfall(0.8f, 0.8f);
		setBiomeName(bn);
	}
}
