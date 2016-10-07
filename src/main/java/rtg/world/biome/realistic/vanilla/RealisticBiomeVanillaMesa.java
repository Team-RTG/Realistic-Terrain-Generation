package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockStainedGlass;
import net.minecraft.init.Biomes;
import net.minecraft.init.Blocks;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
import rtg.util.BlockUtil;
import rtg.util.CellNoise;
import rtg.util.OpenSimplexNoise;
import rtg.world.biome.deco.DecoBoulder;
import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoShrub;
import rtg.world.biome.deco.collection.DecoCollectionDesertRiver;
import rtg.world.gen.surface.SurfaceBase;
import rtg.world.gen.surface.SurfaceRiverOasis;
import rtg.world.gen.surface.vanilla.SurfaceVanillaMesa;
import rtg.world.gen.terrain.vanilla.TerrainVanillaMesa;

public class RealisticBiomeVanillaMesa extends RealisticBiomeVanillaBase {

    public static Biome biome = Biomes.MESA;
    public static Biome river = Biomes.RIVER;

    public RealisticBiomeVanillaMesa(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaMesa(),
            new SurfaceVanillaMesa(
                config,
                BlockUtil.getStateSand(1),
                Blocks.STAINED_HARDENED_CLAY.getDefaultState().withProperty(BlockStainedGlass.COLOR, EnumDyeColor.ORANGE),
                0
            )
        );

        this.waterSurfaceLakeChance = 20;

        this.addDecoCollection(new DecoCollectionDesertRiver());

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.COBBLESTONE.getDefaultState();
        decoBoulder.maxY = 83;
        this.addDeco(decoBoulder);

        DecoShrub decoShrub = new DecoShrub();
        decoShrub.loops = 3;
        decoShrub.maxY = 90;
        addDeco(decoShrub);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.maxY = 100;
        decoDeadBush.loops = 3;
        this.addDeco(decoDeadBush);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.soilBlock = Blocks.SAND.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND);
        decoCactus.loops = 18;
        decoCactus.maxY = 100;
        this.addDeco(decoCactus);
    }

    @Override
    public void rReplace(ChunkPrimer primer, int i, int j, int x, int y, int depth, World world, Random rand,
                         OpenSimplexNoise simplex, CellNoise cell, float[] noise, float river, Biome[] base) {

        this.getSurface().paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);

        SurfaceBase riverSurface = new SurfaceRiverOasis(this.config);
        riverSurface.paintTerrain(primer, i, j, x, y, depth, world, rand, simplex, cell, noise, river, base);
    }

    @Override
    public int getExtraGoldGenCount() {
        return 20;
    }

    @Override
    public Biome beachBiome() {
        return this.beachBiome(Biomes.BEACH);
    }
}
