package rtg.api.world;

import java.util.Random;

import net.minecraft.world.World;

import rtg.api.util.noise.CellNoise;
import rtg.api.util.noise.OpenSimplexNoise;
import rtg.api.util.noise.SimplexCellularNoise;
import rtg.api.util.noise.SimplexOctave;

/**
 * @author topisani
 */
public class RTGWorld {

    public final World world;
    public final OpenSimplexNoise simplex;
    public final CellNoise cell;
    public final Random rand;
    public final SimplexOctave.Disk surfaceJitter = new SimplexOctave.Disk();

    public RTGWorld(World world) {
        this.world = world;
        this.simplex = new OpenSimplexNoise(world.getSeed());
        this.cell = new SimplexCellularNoise(world.getSeed());
        this.rand = world.rand;
    }
}
