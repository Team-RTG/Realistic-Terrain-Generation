package rtg.world.biome.realistic.vanilla;

import static net.minecraftforge.event.terraingen.DecorateBiomeEvent.Decorate.EventType.TREE;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenMegaJungle;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.TerrainGen;
import rtg.api.biome.BiomeConfig;
import rtg.api.biome.vanilla.config.BiomeConfigVanillaJungle;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.util.RandomUtil;
import rtg.world.gen.feature.WorldGenLog;
import rtg.world.gen.feature.tree.WorldGenTreeRTGMangrove;
import rtg.world.gen.feature.tree.WorldGenTreeRTGPalmCustom;
import rtg.world.gen.surface.vanilla.SurfaceVanillaJungle;
import rtg.world.gen.terrain.vanilla.TerrainVanillaJungle;

public class RealisticBiomeVanillaJungle extends RealisticBiomeVanillaBase
{	
	public static Block topBlock = BiomeGenBase.jungle.topBlock;
	public static Block fillerBlock = BiomeGenBase.jungle.fillerBlock;
	
	public RealisticBiomeVanillaJungle(BiomeConfig config)
	{
		super(config, 
			BiomeGenBase.jungle,
			BiomeGenBase.river,
			new TerrainVanillaJungle(),
			new SurfaceVanillaJungle(config, Blocks.grass, Blocks.dirt, false, null, 0f, 1.5f, 60f, 65f, 1.5f, Blocks.dirt, (byte)2, 0.09f)
		);
		
		this.waterSurfaceLakeChance = 3;
        
		this.decoBaseBiomeDecorations.allowed = true;
		this.decoBaseBiomeDecorations.notEqualsZeroChance = 6;
		this.decoBaseBiomeDecorations.loops = 1;
		
		this.decoJungleLilypadVines.allowed = true;
		
		this.decoJungleGrassVines.allowed = true;
		
        this.decoFlowersRTG.allowed = true;
        this.decoFlowersRTG.flowers = new int[]{5};
        this.decoFlowersRTG.chance = 4;
        this.decoFlowersRTG.maxY = 120;
        this.decoFlowersRTG.strengthFactor = 2f;
        
		this.decoJungleCacti.allowed = false;
		this.decoJungleCacti.strengthFactor = 8f;
		this.decoJungleCacti.maxY = 120;
		this.decoJungleCacti.sandOnly = false;
		this.decoJungleCacti.extraHeight = 7;
		this.decoJungleCacti.sandMeta = (byte)1;
		
		this.decoBoulder.allowed = true;
		this.decoBoulder.boulder = Blocks.mossy_cobblestone;
		this.decoBoulder.chance = 16;
		this.decoBoulder.maxY = 95;
		this.decoBoulder.strengthFactor = 2f;

		this.decos.add(this.decoBaseBiomeDecorations);
		this.decos.add(this.decoJungleLilypadVines);
		this.decos.add(this.decoJungleGrassVines);
		this.decos.add(this.decoFlowersRTG);
		
		if (this.config.getPropertyById(BiomeConfigVanillaJungle.decorationCactusId).valueBoolean) {
			this.decos.add(this.decoJungleCacti);
		}
		
		this.decos.add(this.decoBoulder);
	}
	
    
    @Override
    public void rDecorate(World world, Random rand, int chunkX, int chunkY, OpenSimplexNoise simplex, CellNoise cell, float strength, float river)
    {
        
        /**
         * Using rDecorateSeedBiome() to partially decorate the biome? If so, then comment out this method.
         */
        //rOreGenSeedBiome(world, rand, chunkX, chunkY, simplex, cell, strength, river, baseBiome);
        
        float l = simplex.noise2(chunkX / 100f, chunkY / 100f) * 5f + 0.8f;
        
        if (TerrainGen.decorate(world, rand, chunkX, chunkY, TREE)) {
        
            for (int b33 = 0; b33 < 5; b33++)
            {
                int j6 = chunkX + rand.nextInt(16) + 8;
                int k10 = chunkY + rand.nextInt(16) + 8;
                int z52 = world.getHeightValue(j6, k10);
                
                if (z52 < 100 && rand.nextBoolean())
                {
                    WorldGenerator worldgenerator =
                        rand.nextInt(3) != 0
                        ? new WorldGenMegaJungle(false, 10 + rand.nextInt(18), 20, 3, 3)
                        : new WorldGenTreeRTGMangrove(Blocks.log, 3, Blocks.leaves, 3, 10 + rand.nextInt(18), 3 + rand.nextInt(2), 13f, RandomUtil.getRandomInt(rand, 4, 5),
                        0.32f,
                        0.2f);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j6, z52, k10);
                }
                
                if (rand.nextInt(3) == 0) {
                    
                    int j61 = chunkX + rand.nextInt(16) + 8;
                    int k101 = chunkY + rand.nextInt(16) + 8;
                    int z521 = world.getHeightValue(j61, k101);

                    WorldGenerator worldgenerator = new WorldGenTreeRTGPalmCustom((float)(10 + rand.nextInt(11)));
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j61, z521, k101);
                }
                
                if (rand.nextInt(3) == 0) {
                    
                    int j61 = chunkX + rand.nextInt(16) + 8;
                    int k101 = chunkY + rand.nextInt(16) + 8;
                    int z521 = world.getHeightValue(j61, k101);

                    int megaHeight = (rand.nextInt(40) == 0) ? (40 + rand.nextInt(20)) : 20 + rand.nextInt(20);
                    
                    WorldGenerator worldgenerator = new WorldGenMegaJungle(false, megaHeight, 0, 3, 3);
                    worldgenerator.setScale(1.0D, 1.0D, 1.0D);
                    worldgenerator.generate(world, rand, j61, z521, k101);
                }
                
                
            }
    
            if (this.config.getPropertyById(BiomeConfigVanillaJungle.decorationLogsId).valueBoolean) {
            
                if (l > 0f && rand.nextInt(3) == 0)
                {
                    int x22 = chunkX + rand.nextInt(16) + 8;
                    int z22 = chunkY + rand.nextInt(16) + 8;
                    int y22 = world.getHeightValue(x22, z22);
                    (new WorldGenLog(Blocks.log, 3, Blocks.leaves, -1, 4 + rand.nextInt(5))).generate(world, rand, x22, y22, z22);
                }
            }
        }
    }
}
