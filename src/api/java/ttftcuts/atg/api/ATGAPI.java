package ttftcuts.atg.api;

import com.google.common.base.Optional;

import net.minecraft.world.World;

public class ATGAPI {

	public static boolean WorldIsATG(World world) {
		return world.getWorldType().getWorldTypeName() == "ATG";
	}
	
	public static Optional<Integer> sealevel = Optional.absent();
}
