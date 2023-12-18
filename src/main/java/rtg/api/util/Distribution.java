package rtg.api.util;

import net.minecraft.util.math.BlockPos;
import rtg.api.util.noise.SimplexNoise;

/**
 * Parameter object for noise calculations.
 * <p>
 * simplex.noise2(chunkX / noiseDivisor, chunkZ / noiseDivisor) * noiseFactor + noiseAddend;
 *
 * @author WhichOnesPink
 * @author Zeno410
 */
// TODO: [1.12] Due to the lack of variance in usage, the use of this class can be extracted to the few places it
//              gets used, if that usage is even kept. (Usage of noise decoration should be removed.)
// Zeno: That's just nuts. Code copying should always be minimized, or maintenance is a nightmare.
//       I HAD TO ALTER 200 PHOTOCOPIES OF THE SURFACING CODE!!!!! No code copying! Just NO!
public class Distribution {

    protected float noiseDivisor;
    protected float noiseFactor;
    protected float noiseAddend;
    

    public Distribution(float noiseDivisor, float noiseFactor, float noiseAddend) {

        this.noiseDivisor = noiseDivisor;
        this.noiseFactor = noiseFactor;
        this.noiseAddend = noiseAddend;
    }

    public float getNoiseDivisor() {

        return noiseDivisor;
    }

    public Distribution setNoiseDivisor(float noiseDivisor) {

        this.noiseDivisor = noiseDivisor;
        return this;
    }

    public float getNoiseFactor() {

        return noiseFactor;
    }

    public Distribution setNoiseFactor(float noiseFactor) {

        this.noiseFactor = noiseFactor;
        return this;
    }

    public float getNoiseAddend() {

        return noiseAddend;
    }

    public Distribution setNoiseAddend(float noiseAddend) {

        this.noiseAddend = noiseAddend;
        return this;
    }
    
    public float getValue(BlockPos offsetPos, SimplexNoise noise) {
    	return noise
                .noise2f(offsetPos.getX() / getNoiseDivisor(), offsetPos.getZ() / getNoiseDivisor())
                * getNoiseFactor() + getNoiseAddend();
    }
}
