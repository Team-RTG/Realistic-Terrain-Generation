package enhancedbiomes.helpers;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;

public class EnhancedBiomesMath 
{
	public static double average(double ... inputs)
	{
		ArrayList<Double> avg = new ArrayList<Double>();
		
		for(double input : inputs)
		{
			avg.add(input);
		}
		
		double a = 0;
		double b = avg.size();
		
		for(int y = 0; y < avg.size(); y++)
		{
			a += avg.get(y);
		}
		
		double x = a / b;
		return x;		
	}
	
	public static boolean isInputInList(int input, int... list) {
		for(int test : list) {
			if(input == test) return true;
		}
		return false;
	}
}
