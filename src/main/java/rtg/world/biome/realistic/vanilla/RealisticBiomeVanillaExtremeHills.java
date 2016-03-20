package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaExtremeHills;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBase;
import rtg.world.biome.deco.DecoGrass;
import rtg.world.biome.deco.DecoMushrooms;
import rtg.world.biome.deco.DecoPumpkin;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.helper.DecoHelperRandomSplit;
import rtg.world.gen.feature.WorldGenBlob;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPineEuro;
import rtg.world.gen.feature.tree.WorldGenTreeRTGShrub;
import rtg.world.gen.surface.vanilla.SurfaceVanillaExtremeHills;
import rtg.world.gen.terrain.vanilla.TerrainVanillaExtremeHills;

public class RealisticBiomeVanillaExtremeHills extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.extremeHills.topBlock;
    public static Block fillerBlock = BiomeGenBase.extremeHills.fillerBlock;
    
    public RealisticBiomeVanillaExtremeHills(BiomeConfig config)
    {
    
        super(config, 
            BiomeGenBase.extremeHills,
            BiomeGenBase.river,
            new TerrainVanillaExtremeHills(10f, 120f, 10f, 200f),
            new SurfaceVanillaExtremeHills(config, topBlock, fillerBlock, Blocks.grass, Blocks.dirt, 60f, -0.14f, 14f, 0.25f)
        );
        this.generatesEmeralds = true;
        this.noLakes=true;
    }
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
    
        // boulders
        for (int l = 0; l < 3f * strength; ++l)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            
            if (k1 < 95 && rand.nextInt(16) == 0) {
                (new WorldGenBlob(Blocks.mossy_cobblestone, 0, rand)).generate(world, rand, i1, k1, j1);
            }
        }
        
        // trees
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 6f + 0.8f;
        for (int b1 = 0; b1 < l * 4f * strength; b1++)
        {
            int j6 = chunkX + rand.nextInt(16) + 8;
            int k10 = chunkY + rand.nextInt(16) + 8;
            int z52 = world.getHeightValue(j6, k10);
            
            if (rand.nextInt(24) == 0) {
                WorldGenerator worldgenerator = new WorldGenTreeRTGPineEuro();
                worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                worldgenerator.generate(world, rand, j6, z52, k10);
            }
        }
        
        if (this.config.getPropertyById(BiomeConfigVanillaExtremeHills.decorationLogsId).valueBoolean) {
        
            if (l > 0f && rand.nextInt(6) == 0)
            {
                int x22 = chunkX + rand.nextInt(16) + 8;
                int z22 = chunkY + rand.nextInt(16) + 8;
                int y22 = world.getHeightValue(x22, z22);
                (new WorldGenLog(1, 3 + rand.nextInt(4), false)).generate(world, rand, x22, y22, z22);
            }
        }
        
        for (int b = 0; b < 2f * strength; b++)
        {
            int i1 = chunkX + rand.nextInt(16) + 8;
            int j1 = chunkY + rand.nextInt(16) + 8;
            int k1 = world.getHeightValue(i1, j1);
            if (rand.nextInt(10) == 0)
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(5) + 4, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
            }
            else
            {
                (new WorldGenTreeRTGShrub(rand.nextInt(4) + 1, rand.nextInt(2), rand.nextInt(2))).generate(world, rand, i1, k1, j1);
            }
        }
        
        DecoShrub decoShrub1 = new DecoShrub();
        decoShrub1.maxY = 100;
        decoShrub1.strengthFactor = 2f;
		
        DecoShrub decoShrub2 = new DecoShrub();
        decoShrub2.maxY = 100;
        decoShrub2.strengthFactor = 2f;
        
        DecoHelperRandomSplit decoHelperRandomSplit = new DecoHelperRandomSplit();
        decoHelperRandomSplit.decos = new DecoBase[]{decoShrub1, decoShrub2};
        decoHelperRandomSplit.chances = new int[]{10, 1};
        this.addDeco(decoHelperRandomSplit);
        
        DecoMushrooms decoMushrooms = new DecoMushrooms();
        decoMushrooms.maxY = 90;
        decoMushrooms.randomType = rtg.world.biome.deco.DecoMushrooms.RandomType.X_DIVIDED_BY_STRENGTH;
        decoMushrooms.randomFloat = 3f;
        this.addDeco(decoMushrooms);
        
		DecoPumpkin decoPumpkin = new DecoPumpkin();
		decoPumpkin.maxY = 90;
		decoPumpkin.randomType = rtg.world.biome.deco.DecoPumpkin.RandomType.X_DIVIDED_BY_STRENGTH;
		decoPumpkin.randomFloat = 20f;
        this.addDeco(decoPumpkin);
        
		DecoGrass decoGrass = new DecoGrass();
		decoGrass.maxY = 128;
		decoGrass.strengthFactor = 10f;
        this.addDeco(decoGrass);
    }
}
