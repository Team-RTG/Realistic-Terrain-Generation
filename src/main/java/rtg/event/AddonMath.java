package rtg.event;

import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.structure.MapGenVillage;

public class AddonMath
{
	public static double sqrtMagnitude(double input) {
		if(input < 0) return -Math.sqrt(Math.abs(input));
		else return Math.sqrt(input);
	}
	
	public static double increaseMagnitude(double input, double increase) {
		return input + (input > 0 ? increase : -increase);
	}
	
	public static double average(double... inputs) {
		ArrayList<Double> avg = new ArrayList<Double>();

		for(double input : inputs) {
			avg.add(input);
		}

		double a = 0;
		double b = avg.size();

		for(int y = 0; y < avg.size(); y++) {
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