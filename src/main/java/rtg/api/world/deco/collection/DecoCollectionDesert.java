package rtg.api.world.deco.collection;

import rtg.api.config.BiomeConfig;
import rtg.api.world.deco.DecoCactus;
import rtg.api.world.deco.DecoDeadBush;
import rtg.api.world.deco.DecoDesertWell;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionDesert extends DecoCollectionBase {

    public DecoCollectionDesert(BiomeConfig config) {

        super(config);

        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.config().MAX_Y.set(80);
        decoDesertWell.config().STRENGTH_FACTOR.set(1f);
        decoDesertWell.config().CHANCE.set(160);
        this.addDeco(decoDesertWell);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.config().MAX_Y.set(90);
        decoCactus.config().STRENGTH_FACTOR.set(3f);
        decoCactus.config().CHANCE.set(2);
        this.addDeco(decoCactus, config.ALLOW_CACTUS.get());

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.config().MAX_Y.set(128);
        decoDeadBush.config().STRENGTH_FACTOR.set(2f);
        decoDeadBush.config().CHANCE.set(3);
        this.addDeco(decoDeadBush);
    }
}
