package enhancedbiomes.handlers;

import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.world.gen.*;

public class PreDecorationHandler 
{
	@SubscribeEvent
	public void correctBiomeDecorations(DecorateBiomeEvent.Pre e) {
		BiomeDecorator dec = e.world.getBiomeGenForCoords(e.chunkX * 16, e.chunkZ * 16).theBiomeDecorator;
		dec.bigMushroomGen = new WorldGenBigMushroomEB();
		dec.clayGen = new WorldGenClayEB(4);
        dec.sandGen = new WorldGenSandEB(Blocks.sand, 7);
        dec.gravelAsSandGen = new WorldGenSandEB(Blocks.gravel, 6);
	}
}
