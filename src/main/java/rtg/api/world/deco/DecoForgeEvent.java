package rtg.api.world.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IRealisticBiome;

/**
 * @author WhichOnesPink
 */
public class DecoForgeEvent extends DecoBase {

    private DecorateBiomeEvent.Decorate.EventType event;

    public DecoForgeEvent(DecorateBiomeEvent.Decorate.EventType event) {

        super();

        this.event = event;
    }

    @Override
    public boolean properlyDefined() {

        try {
            if (this.event != null) {
                return true;
            }
        }
        catch (Exception e) {
            return false;
        }

        return false;
    }

    @Override
    public void generate(IRealisticBiome biome, IRTGWorld rtgWorld, Random rand, int worldX, int worldZ, float strength, float river, boolean hasPlacedVillageBlocks) {

        if (this.allowed) {

            if (TerrainGen.decorate(rtgWorld.world(), rand, new BlockPos(worldX, 0, worldZ), this.event)) {
                ;
            }
        }
    }
}
