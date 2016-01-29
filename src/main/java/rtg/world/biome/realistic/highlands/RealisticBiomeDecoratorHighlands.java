
package rtg.world.biome.realistic.highlands;

/**
 *
 * @author Zeno410
 */

import java.util.Random;

import org.apache.logging.log4j.Level;

//import highlands.Highlands;
//import highlands.Logs;
//import highlands.api.HighlandsBiomes;
//import highlands.biome.BiomeGenBaseHighlands;
//import highlands.worldgen.WorldGenUnderground2;
//import highlands.worldgen.WorldGenWatermelon;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenBigMushroom;
import net.minecraft.world.gen.feature.WorldGenCactus;
import net.minecraft.world.gen.feature.WorldGenClay;
import net.minecraft.world.gen.feature.WorldGenDeadBush;
import net.minecraft.world.gen.feature.WorldGenFlowers;
import net.minecraft.world.gen.feature.WorldGenLiquids;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenPumpkin;
import net.minecraft.world.gen.feature.WorldGenReed;
import net.minecraft.world.gen.feature.WorldGenWaterlily;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.feature.WorldGenSand;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class RealisticBiomeDecoratorHighlands extends BiomeDecorator
{
//	private int highlandsPlantsPerChunk;
//
//	//Ore Generators
//	//Used in Biomes directly
//	public static WorldGenerator HLsand = new WorldGenMinable(Blocks.sand, 32);
//	public static WorldGenerator HLice = new WorldGenMinable(Blocks.ice, 32);
//	public static WorldGenerator HLwater = new WorldGenUnderground2(Blocks.water, 4);
//	public static WorldGenerator HLlava = new WorldGenUnderground2(Blocks.lava, 8);
//	public static WorldGenerator HLdirt = new WorldGenUnderground2(Blocks.dirt, 72, Blocks.sand);
//	public static WorldGenerator HLrock = new WorldGenUnderground2(Blocks.stone, 72, Blocks.dirt);
//	public static WorldGenerator HLobsidian = new WorldGenMinable(Blocks.obsidian, 8);
//
//	public RealisticBiomeDecoratorHighlands(BiomeGenBase par1BiomeGenBase, int trees, int grass, int flowers, int hlPlants) {
//		super();
//		this.treesPerChunk = trees;
//		this.grassPerChunk = grass;
//		this.flowersPerChunk = flowers;
//		this.highlandsPlantsPerChunk = hlPlants;
//		this.sandPerChunk2 = 0;
//		this.sandPerChunk = 15;
//		this.generateLakes = false;
//	}
//
//	public RealisticBiomeDecoratorHighlands(BiomeGenBase par1BiomeGenBase, int trees, int grass, int flowers) {
//		this(par1BiomeGenBase, trees, grass, flowers, 0);
//	}
//
//	@Override
//	public void decorateChunk (World world, Random random, BiomeGenBase biome, int x, int z) {
//		this.decorate(world, random, (BiomeGenBaseHighlands)biome, x, z);
//	}
//
//	private void decorate(World par1World, Random par2Random, BiomeGenBaseHighlands biome, int x, int z){
//		if (this.currentWorld != null)
//        {
//            throw new RuntimeException("Already decorating!! at:"+x+","+z);
//        }
//
//		super.decorateChunk(par1World, par2Random, (BiomeGenBase)biome, x, z);
//		this.currentWorld = par1World;
//        this.randomGenerator = par2Random;
//
//		//
//
//		this.chunk_X = x;
//        this.chunk_Z = z;
//
//        if (biome == HighlandsBiomes.autumnForest || biome == HighlandsBiomes.bog)
//        {
//            int var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
//            int var3 = this.randomGenerator.nextInt(128);
//            int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
//            (new WorldGenPumpkin()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
//        }
//
//        if (biome == HighlandsBiomes.tropics || biome == HighlandsBiomes.tropicalIslands)
//        {
//            int var2 = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
//            int var3 = this.randomGenerator.nextInt(128);
//            int var4 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
//            (new WorldGenWatermelon()).generate(this.currentWorld, this.randomGenerator, var2, var3, var4);
//        }
//
//		// highlands plants generator
//        if(Highlands.plantsFlag){
//	        for (int j = 0; j < this.highlandsPlantsPerChunk; ++j)
//	        {
//	            int k = this.chunk_X + this.randomGenerator.nextInt(16) + 8;
//	            int l = this.randomGenerator.nextInt(128);
//	            int i1 = this.chunk_Z + this.randomGenerator.nextInt(16) + 8;
//	            WorldGenerator worldgenerator1 = biome.getRandomWorldGenForHighlandsPlants(this.randomGenerator);
//	            worldgenerator1.generate(this.currentWorld, this.randomGenerator, k, l, i1);
//	        }
//        }
//
//
//        // cleanup, prevents "Already decorating!!" crashes
//        this.currentWorld = null;
//        this.randomGenerator = null;
//	}
//
//	public void genOreHighlands(World world, Random random, int x, int z, int timesPerChunk, WorldGenerator oreGen, int minH, int maxH) {
//		if(Highlands.useOreGens){
//	        for (int var5 = 0; var5 < timesPerChunk; ++var5)
//	        {
//	            int var6 = x + random.nextInt(16);
//	            int var7 = random.nextInt(maxH - minH) + minH;
//	            int var8 = z + random.nextInt(16);
//	            oreGen.generate(world, random, var6, var7, var8);
//	        }
//		}
//	}
//	public void genOreHighlandsNoCheck(World world, Random random, int locX, int locZ, int timesPerChunk, WorldGenerator oreGen, int minH, int maxH)
//    {
//        for (int var5 = 0; var5 < timesPerChunk; ++var5)
//        {
//            int var6 = locX + random.nextInt(16);
//            int var7 = random.nextInt(maxH - minH) + minH;
//            int var8 = locZ + random.nextInt(16);
//            oreGen.generate(world, random, var6, var7, var8);
//        }
//    }
}