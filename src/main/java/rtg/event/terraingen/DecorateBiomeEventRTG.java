package rtg.event.terraingen;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;


public class DecorateBiomeEventRTG extends DecorateBiomeEvent
{
    public DecorateBiomeEventRTG(World world, Random rand, BlockPos pos)
    {
        super(world, rand, pos);
    }

    /**
     * This event is fired when a chunk is decorated with a biome feature.
     *
     * You can set the result to DENY to prevent the default biome decoration.
     */
    @HasResult
    public static class DecorateRTG extends DecorateBiomeEvent.Decorate
    {
        private final Decorate.EventType type;
        private int amount = -1;

        public DecorateRTG(World world, Random rand, BlockPos pos, Decorate.EventType type, int amount)
        {
            super(world, rand, pos, type);
            this.type = type;
            this.amount = amount;
        }

        public DecorateRTG(World world, Random rand, BlockPos pos, Decorate.EventType type)
        {
            this(world, rand, pos, type, -1);
        }

        public boolean hasAmountData() {
            return this.amount != -1;
        }

        public int getModifiedAmount() {
            return this.amount;
        }

        public void setModifiedAmount(int amount) {
            this.amount = amount;
        }
    }
}