/*
 */

package exterminatorjeff.undergroundbiomes.api.names;

import net.minecraft.block.Block;

/**
 *
 * @author Zeno410
 */
public class VanillaBlockAccess implements BlockAccess {

    private Block block;
    public VanillaBlockAccess(Block block) {
        this.block = block;
    }
    @Override
    public Block getBlock() {
        return block;
    }

}
