package enhancedbiomes.handlers;

import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.gen.MapGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable;
import net.minecraftforge.event.terraingen.OreGenEvent.GenerateMinable.EventType;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;
import enhancedbiomes.blocks.EnhancedBiomesBlocks;
import enhancedbiomes.world.MapGenCavesEnhancedBiomes;
import enhancedbiomes.world.MapGenRavineEnhancedBiomes;
import enhancedbiomes.world.gen.WorldGenMinableEnhancedBiomesOres;

import static enhancedbiomes.blocks.EnhancedBiomesBlocks.*;
import static net.minecraft.init.Blocks.*;

public class OreHandler 
{
	@SubscribeEvent
	public void replaceOreGens(GenerateMinable e) {
		if(e.type == EventType.COAL) {
			genStandardOre1(20, new WorldGenMinableEnhancedBiomesOres(oreCoalEB, coal_ore, 16), 0, 128, e.worldX, e.worldZ, e.world, e.rand);
			e.setResult(Result.DENY);
		}
		else if(e.type == EventType.IRON) {
        	genStandardOre1(20, new WorldGenMinableEnhancedBiomesOres(oreIronEB, iron_ore, 8), 0, 64, e.worldX, e.worldZ, e.world, e.rand);
			e.setResult(Result.DENY);
		}
		else if(e.type == EventType.GOLD) {
        	genStandardOre1(2, new WorldGenMinableEnhancedBiomesOres(oreGoldEB, gold_ore, 8), 0, 32, e.worldX, e.worldZ, e.world, e.rand);
			e.setResult(Result.DENY);
		}
		else if(e.type == EventType.REDSTONE) {
        	genStandardOre1(8, new WorldGenMinableEnhancedBiomesOres(oreRedstoneEB, redstone_ore, 7), 0, 16, e.worldX, e.worldZ, e.world, e.rand);
			e.setResult(Result.DENY);
		}
		else if(e.type == EventType.DIAMOND) {
        	genStandardOre1(1, new WorldGenMinableEnhancedBiomesOres(oreDiamondEB, diamond_ore, 7), 0, 16, e.worldX, e.worldZ, e.world, e.rand);
			e.setResult(Result.DENY);
		}
		else if(e.type == EventType.LAPIS) {
        	genStandardOre2(1, new WorldGenMinableEnhancedBiomesOres(oreLapisEB, lapis_ore, 6), 16, e.worldX, e.worldZ, e.world, e.rand);
			e.setResult(Result.DENY);
		}
	}
	
    /**
     * Standard ore generation helper. Generates most ores.
     */
    protected static void genStandardOre1(int rate, WorldGenerator par2WorldGenerator, int min, int max, int chunk_X, int chunk_Z, World world, Random rand)
    {
        for (int l = 0; l < rate; ++l)
        {
            int i1 = chunk_X + rand.nextInt(16);
            int j1 = rand.nextInt(max - min) + min;
            int k1 = chunk_Z + rand.nextInt(16);
            par2WorldGenerator.generate(world, rand, i1, j1, k1);
        }
    }

    /**
     * Standard ore generation helper. Generates Lapis Lazuli.
     */
    protected static void genStandardOre2(int rate, WorldGenerator par2WorldGenerator, int midHeight, int chunk_X, int chunk_Z, World world, Random rand)
    {
        for (int l = 0; l < rate; ++l)
        {
            int i1 = chunk_X + rand.nextInt(16);
            int j1 = rand.nextInt(midHeight) + rand.nextInt(midHeight);
            int k1 = chunk_Z + rand.nextInt(16);
            par2WorldGenerator.generate(world, rand, i1, j1, k1);
        }
    }
}
