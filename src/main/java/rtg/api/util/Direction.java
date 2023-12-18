package rtg.api.util;

/**
*
* @author Zeno410
*/

import java.util.ArrayList;

import net.minecraft.util.math.BlockPos;

public  class Direction {
   public static Direction UP = new Direction(0,-1,0);
   public static Direction UP_RIGHT = new Direction(1,-1,1);
   public static Direction RIGHT = new Direction(1,0,2);
   public static Direction DOWN_RIGHT = new Direction(1,1,3);
   public static Direction DOWN = new Direction(0,1,4);
   public static Direction DOWN_LEFT = new Direction(-1,1,5);
   public static Direction LEFT = new Direction(-1,0,6);
   public static Direction UP_LEFT = new Direction(-1,-1,7);
   public final int xOffset;
   public final int zOffset;
   public final int index;
   private Direction (int _xMultiple, int _zMultiple, int _index) {
       xOffset = _xMultiple;
       zOffset = _zMultiple;
       index = _index;
   }
   
   private static ArrayList<Direction> storedDirections() {
	   ArrayList<Direction> result = new ArrayList<>(8);
	   result.add(UP);
	   result.add(UP_RIGHT);
	   result.add(RIGHT);
	   result.add(DOWN_RIGHT);
	   result.add(DOWN);
	   result.add(DOWN_LEFT);
	   result.add(LEFT);
	   result.add(UP_LEFT);
	   return result;
   }
   
   private static ArrayList<Direction> storedDirections = storedDirections();
   
   public static Iterable<Direction> list() {return storedDirections;}
   
   public BlockPos moved(BlockPos moved) {
	   return new BlockPos(moved.getX()+xOffset,moved.getY(),moved.getZ()+zOffset);
   }
   
}
