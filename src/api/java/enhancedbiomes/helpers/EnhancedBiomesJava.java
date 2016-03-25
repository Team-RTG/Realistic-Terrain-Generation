package enhancedbiomes.helpers;


public class EnhancedBiomesJava 
{
	public EnhancedBiomesJava()
	{
		
	}
	
	public static void nullCheck(Object... inputs) {
		int x = 0;
		for(Object input : inputs) {
			x++;
			if(input == null) System.err.println("Input " + x + " is null");
		}	
	}

	public static String[] createList(String[]... stringLists) {
		String[] ret = new String[0];
		
		for(String[] stringList : stringLists) {
			String[] newList = new String[ret.length + stringList.length];
			for(int a = 0; a < ret.length; a++) newList[a] = ret[a];
			for(int a = 0; a < stringList.length; a++) newList[a + ret.length] = stringList[a];
			ret = newList;
		}
		//for(int a = 0; a < ret.length; a++) System.err.println(ret[a]);
		return ret;
	}
}
