package rtg.api.world;

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

/**
 * @author topisani
 */
public class RTGWorld {

    public final World world;
    public final OpenSimplexNoise simplex;
    public final CellNoise cell;
    public final CellNoise voronoi;
    public final Random rand;
    public final SimplexOctave.Disk surfaceJitter = new SimplexOctave.Disk();
    public final TimedHashSet<ChunkPos> decoratedChunks = new TimedHashSet(5000);
    public final BiomeMesa mesaBiome;

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
    }
}
