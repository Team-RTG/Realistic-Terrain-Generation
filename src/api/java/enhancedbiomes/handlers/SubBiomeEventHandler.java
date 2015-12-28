package enhancedbiomes.handlers;

import java.util.Random;

import enhancedbiomes.world.gen.layer.GenLayerEB;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public class SubBiomeEventHandler
{
    @SubscribeEvent
    public void onInitBiomeGens(WorldTypeEvent.InitBiomeGens e)
    {
        e.newBiomeGens = GenLayerEB.initializeAllBiomeGenerators(e.seed, e.worldType);
    }
}