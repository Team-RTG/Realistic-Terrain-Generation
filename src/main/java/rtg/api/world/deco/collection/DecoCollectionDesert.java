package rtg.api.world.deco.collection;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.DecoCactus;
import rtg.api.world.deco.DecoDeadBush;


/**
 * @author WhichOnesPink
 */
// TODO: [1.12] To be removed.
public class DecoCollectionDesert extends DecoCollectionBase {

    public DecoCollectionDesert(BiomeConfig config) {

        super(config);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setMaxY(90);
        decoCactus.setStrengthFactor(3f);
        decoCactus.setChance(2);
        this.addDeco(decoCactus, config.ALLOW_CACTUS.get());

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.setMaxY(128);
        decoDeadBush.setStrengthFactor(2f);
        decoDeadBush.setChance(3);
        this.addDeco(decoDeadBush);
    }
}
