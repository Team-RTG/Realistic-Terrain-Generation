package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenForest;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaForest;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoFallenTree.LogCondition;
import rtg.world.biome.deco.DecoFallenTree5050;
import rtg.world.biome.deco.DecoFlowersRTG;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineBig;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineSmall;
import rtg.world.gen.feature.tree.WorldGenTreeRTGTrees;
import rtg.world.gen.surface.vanilla.SurfaceVanillaForest;
import rtg.world.gen.terrain.vanilla.TerrainVanillaForest;

public class RealisticBiomeVanillaForest extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.forest.topBlock;
    public static Block fillerBlock = BiomeGenBase.forest.fillerBlock;
    
    public RealisticBiomeVanillaForest(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.forest,
            BiomeGenBase.river,
            new TerrainVanillaForest(),
            new SurfaceVanillaForest(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.10f)
        );

		/**
		 * ##################################################
		 * # DECORATIONS (ORDER MATTERS)
		 * ##################################################
		 */
        
        if (this.config.getPropertyById(BiomeConfigVanillaForest.decorationLogsId).valueBoolean) {
            
            DecoFallenTree5050 decoFallenTree5050 = new DecoFallenTree5050();
			decoFallenTree5050.logCondition = LogCondition.RANDOM_CHANCE;
			decoFallenTree5050.logConditionChance = 8; 
			decoFallenTree5050.maxY = 100;
			decoFallenTree5050.logBlock = Blocks.log;
			decoFallenTree5050.logMeta = (byte)0;
			decoFallenTree5050.leavesBlock = Blocks.leaves;
			decoFallenTree5050.leavesMeta = (byte)-1;
			decoFallenTree5050.minSize = 3;
			decoFallenTree5050.maxSize = 6;
			decoFallenTree5050.logBlock2 = Blocks.log;
			decoFallenTree5050.logMeta2 = (byte)1;
			decoFallenTree5050.leavesBlock2 = Blocks.leaves;
			decoFallenTree5050.leavesMeta2 = (byte)-1;
			decoFallenTree5050.minSize2 = 3;
			decoFallenTree5050.maxSize2 = 6;
			this.decos.add(decoFallenTree5050);
        }
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.maxY = 110;
        decoShrub.strengthFactor = 3f;
		this.decos.add(decoShrub);
        
		DecoFlowersRTG decoFlowersRTG = new DecoFlowersRTG();
		decoFlowersRTG.flowers = new int[] {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11};
		decoFlowersRTG.maxY = 128;
		decoFlowersRTG.strengthFactor = 8f;
        this.decos.add(decoFlowersRTG);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 12f;
        this.decos.add(decoGrass);
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        float l = simplex.noise2(chunkX / 80f, chunkY / 80f) * 60f - 15f;
        
        for (int b1 = 0; b1 < l * strength; b1++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            if (rand.nextBoolean()) {
                WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 1, 1);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
            else {
                WorldGenerator worldgenerator = new WorldGenTreeRTGPineBig(11 + rand.nextInt(11), 15 + rand.nextInt(15), 0, 0);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        for (int b2 = 0; b2 < 3f * strength; b2++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            if (z52 < 120)
            {
                WorldGenerator worldgenerator =
                    rand.nextInt(4) != 0 ? new WorldGenTreeRTGPineSmall(4 + rand.nextInt(7), 6 + rand.nextInt(9), 0)
                        : rand.nextInt(10) != 0 ? new WorldGenTreeRTGTrees(false) : new WorldGenForest(false, false);
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        

    }
}
