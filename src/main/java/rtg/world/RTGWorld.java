package rtg.world;

import java.util.Random;

import net.minecraft.init.Biomes;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeMesa;

import rtg.api.util.TimedHashSet;
import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexOctave;
import rtg.api.util.noise.SpacedCellNoise;
import rtg.api.world.IRTGWorld;
import rtg.api.world.biome.IBiomeProviderRTG;
import rtg.api.world.biome.OrganicBiomeGenerator;
import rtg.world.gen.LandscapeGenerator;

/**
 * @author topisani
 */
public class RTGWorld implements IRTGWorld {

    public final World world;
    public final OpenSimplexNoise simplex;
    public final CellNoise cell;
    public final CellNoise voronoi;
    public final Random rand;
    public final SimplexOctave.Disk surfaceJitter = new SimplexOctave.Disk();
    public final TimedHashSet<ChunkPos> decoratedChunks = new TimedHashSet(5000);
    public final BiomeMesa mesaBiome;
    public final OrganicBiomeGenerator organicBiomeGenerator;
    public final LandscapeGenerator landscapeGenerator;

    public RTGWorld(World world) {
        this.world = world;
        this.simplex = new OpenSimplexNoise(world.getSeed());
        this.voronoi = new SpacedCellNoise(world.getSeed());
        // Simplex Cell noise deleted because point assignments are defecting producing serious artifacts
        // I can't figure out how to fix it. - Zeno
        this.cell = new SpacedCellNoise(world.getSeed());
        this.rand = world.rand;
        mesaBiome = (BiomeMesa)Biomes.MESA;
        mesaBiome.generateBands(world.getSeed());
        this.organicBiomeGenerator = new OrganicBiomeGenerator(this);
        this.landscapeGenerator = new LandscapeGenerator(this);
    }

    @Override
    public World world() {
        return this.world;
    }

    @Override
    public OpenSimplexNoise simplex() {
        return this.simplex;
    }

    @Override
    public CellNoise cell() {
        return this.cell;
    }

    @Override
    public CellNoise voronoi() {
        return this.voronoi;
    }

    @Override
    public Random rand() {
        return this.rand;
    }

    @Override
    public SimplexOctave.Disk surfaceJitter() {
        return this.surfaceJitter;
    }

    @Override
    public TimedHashSet<ChunkPos> decoratedChunks() {
        return this.decoratedChunks;
    }

    @Override
    public BiomeMesa mesaBiome() {
        return this.mesaBiome;
    }

    @Override
    public OrganicBiomeGenerator organicBiomeGenerator() {
        return this.organicBiomeGenerator;
    }

    @Override
    public int getRepairedBiomeAt(IBiomeProviderRTG cmr, int cx, int cz) {
        return this.landscapeGenerator.getBiomeDataAt(cmr, cx, cz);
    }
}
