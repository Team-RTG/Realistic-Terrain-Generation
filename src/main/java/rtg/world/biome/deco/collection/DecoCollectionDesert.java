package rtg.world.biome.deco.collection;

import rtg.world.biome.deco.DecoCactus;
import rtg.world.biome.deco.DecoDeadBush;
import rtg.world.biome.deco.DecoDesertWell;


/**
 * @author WhichOnesPink
 */
public class DecoCollectionDesert extends DecoCollectionBase {

    public DecoCollectionDesert() {

        super();

        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.setMaxY(80);
        decoDesertWell.setStrengthFactor(1f);
        decoDesertWell.setChance(160);
        this.addDeco(decoDesertWell);

        DecoCactus decoCactus = new DecoCactus();
        decoCactus.setMaxY(90);
        decoCactus.setStrengthFactor(3f);
        decoCactus.setChance(2);
        this.addDeco(decoCactus);

        DecoDeadBush decoDeadBush = new DecoDeadBush();
        decoDeadBush.setMaxY(128);
        decoDeadBush.setStrengthFactor(2f);
        decoDeadBush.setChance(3);
        this.addDeco(decoDeadBush);
    }
}
