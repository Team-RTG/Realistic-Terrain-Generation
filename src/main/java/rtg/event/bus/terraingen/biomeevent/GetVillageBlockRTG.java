package rtg.event.bus.terraingen.biomeevent;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.event.terraingen.BiomeEvent;
import rtg.RTG;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class GetVillageBlockRTG
{

    public GetVillageBlockRTG()
    {

    }

    @SubscribeEvent
    public void onGetVillageBlockID(BiomeEvent.GetVillageBlockID event)
    {

        // Use event.biome, if that's null, fall back to our own copy
        if (this.isDesertVillageBiome((event.biome == null) ? RTG.eventMgr.biome.baseBiome : event.biome)) {

            Block originalBlock = event.original;

            if (originalBlock == Blocks.cobblestone || originalBlock == Blocks.planks || originalBlock == Blocks.log
                    || originalBlock == Blocks.log2 || originalBlock == Blocks.gravel) {

                event.replacement = Blocks.sandstone;
            } else if (originalBlock == Blocks.oak_stairs || originalBlock == Blocks.stone_stairs) {

                event.replacement = Blocks.sandstone_stairs;
            }

            // The event has to be cancelled in order to override the original block.
            if (event.replacement != null) {

                event.setResult(Result.DENY);
            }
        }
    }
    
    @SubscribeEvent
    public void onGetVillageBlockMeta(BiomeEvent.GetVillageBlockMeta event)
    {
        boolean replaced = false;

        // Use event.biome, if that's null, fall back to our own copy
        if (this.isDesertVillageBiome((event.biome == null) ? RTG.eventMgr.biome.baseBiome : event.biome)) {

            Block originalBlock = event.original;

            if (originalBlock == Blocks.log || originalBlock == Blocks.log2 || originalBlock == Blocks.cobblestone) {

                event.replacement = 0;
                replaced = true;
            }

            if (originalBlock == Blocks.planks) {

                event.replacement = 2;
                replaced = true;
            }
        }

        // The event has to be cancelled in order to override the original block.
        if (replaced) {

            event.setResult(Result.DENY);
        }
    }
    
    private boolean isDesertVillageBiome(BiomeGenBase biome)
    {
        if(biome == null) return false;
        if (
            BiomeDictionary.isBiomeOfType(biome, Type.HOT)
            &&
            BiomeDictionary.isBiomeOfType(biome, Type.DRY)
            &&
            BiomeDictionary.isBiomeOfType(biome, Type.SANDY)
        ) {
            return true;
        }

        return false;
    }
}