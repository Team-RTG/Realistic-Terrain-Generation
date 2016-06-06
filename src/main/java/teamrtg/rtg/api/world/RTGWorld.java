package teamrtg.rtg.api.world;

import net.minecraft.world.World;
import teamrtg.rtg.api.util.noise.CellNoise;
import teamrtg.rtg.api.util.noise.OpenSimplexNoise;
import teamrtg.rtg.api.util.noise.SimplexCellularNoise;
import teamrtg.rtg.api.util.noise.SimplexOctave;

import java.util.Random;

/**
 * @author topisani
 */
public class RTGWorld {

    public final World world;
    public final OpenSimplexNoise simplex;
    public final CellNoise cell;
    public final Random rand;
    public final SimplexOctave.Disk surfaceJitter = new SimplexOctave.Disk();
    public final RealisticBiomeFaker biomeFaker;

    public RTGWorld(World world) {
        this.world = world;
        this.simplex = new OpenSimplexNoise(world.getSeed());
        this.cell = new SimplexCellularNoise(world.getSeed());
        this.rand = world.rand;
        this.biomeFaker = new RealisticBiomeFaker(this);
    }
}
