package rtg.world.biome.realistic.vanilla;

import java.util.Random;

import net.minecraft.block.BlockSand;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import rtg.api.biome.BiomeConfig;
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

    private static final Biome biome = Biome.mesa;
    private static final Biome river = Biome.river;

    public RealisticBiomeVanillaMesa(BiomeConfig config) {

        super(config, biome, river,
            new TerrainVanillaMesa(),
            new SurfaceVanillaMesa(
                config,
                Blocks.sand.getStateFromMeta(BlockSand.EnumType.RED_SAND.getMetadata()),
                Blocks.stained_hardened_clay.getStateFromMeta(1),
                0
            )
        );

        this.waterSurfaceLakeChance = 20;

        this.addDecoCollection(new DecoCollectionDesertRiver());

        DecoBoulder decoBoulder = new DecoBoulder();
        decoBoulder.boulderBlock = Blocks.cobblestone.getDefaultState();
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
        decoCactus.soilBlock = Blocks.sand;
        decoCactus.soilMeta = (byte) 1;
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
}
