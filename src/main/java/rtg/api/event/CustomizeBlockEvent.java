package rtg.api.event;


import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.fml.common.eventhandler.Event;

// TODO [1.12] Revisit the usefulness of having this event. Maybe replace with biome configs.
public class CustomizeBlockEvent extends Event {

    private final World       world;
    private final BlockPos    pos;
    private       IBlockState blockState;

    public CustomizeBlockEvent(final World world, final BlockPos pos, final IBlockState blockState) {
        super();
        this.world      = world;
        this.pos        = pos;
        this.blockState = blockState;
    }

    public World       getWorld()                            { return this.world; }
    public BlockPos    getPos()                              { return this.pos; }
    public IBlockState getBlockState()                       { return blockState; }
    public void        setBlockState(IBlockState blockState) { this.blockState = blockState; }
}