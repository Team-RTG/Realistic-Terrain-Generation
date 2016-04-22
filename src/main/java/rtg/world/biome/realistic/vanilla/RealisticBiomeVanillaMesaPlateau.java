package rtg.world.biome.realistic.vanilla;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import rtg.api.biome.BiomeConfig;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.*;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesaPlateau;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesaPlateau;

import java.util.Random;

import static rtg.world.biome.deco.DecoTree.TreeCondition.NOISE_GREATER_AND_RANDOM_CHANCE;
import static rtg.world.biome.deco.DecoTree.TreeType.VANILLA_OAK;

public class RealisticBiomeVanillaMesaPlateau extends RealisticBiomeVanillaBase
{
    
    public static Block topBlock = BiomeGenBase.mesaPlateau.topBlock;
    public static Block fillerBlock = BiomeGenBase.mesaPlateau.fillerBlock;
    
    public RealisticBiomeVanillaMesaPlateau(BiomeConfig config)
    {

        super(config,
                BiomeGenBase.mesaPlateau,
                BiomeGenBase.river,
                new TerrainVanillaMesaPlateau(true, 35f, 160f, 60f, 40f, 69f),
                new SurfaceVanillaMesaPlateau(config, Blocks.sand, (byte)1, Blocks.sand, (byte)1, 0)
        );
        this.noLakes = true;
        
        DecoShrub decoShrub = new DecoShrub();
        decoShrub.chance = 10;
        addDeco(decoShrub);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.strengthFactor = 25f;
        decoCactus.soilBlock = Blocks.sand;
        decoCactus.soilMeta = (byte)1;
        decoCactus.sandOnly = false;
        decoCactus.maxRiver = 0.8f;
        addDeco(decoCactus);

        DecoReed decoReed = new DecoReed();
        decoReed.loops = 5;
        decoReed.maxRiver = 0.8f;
        addDeco(decoReed);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.strengthFactor = 5f;
        addDeco(decoDeadBush);

        DecoTree decoTree = new DecoTree();
        decoTree.loops = 20;
        decoTree.treeType = VANILLA_OAK;
        decoTree.treeCondition = NOISE_GREATER_AND_RANDOM_CHANCE;
        decoTree.distribution = new DecoTree.Distribution(24f, 1f, 0f);
        decoTree.treeConditionChance = 0;
        decoTree.treeConditionNoise = 0f;
        decoTree.minY = 74;
        addDeco(decoTree);
    }

    @Override
    public void rReplace(Block[] blocks, byte[] metadata, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, BiomeGenBase[] base)
    {

        this.getSurface().paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(blocks, metadata, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }
}
