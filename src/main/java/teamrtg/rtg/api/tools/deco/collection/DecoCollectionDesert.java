package teamrtg.rtg.api.tools.deco.collection;

import teamrtg.rtg.api.tools.deco.DecoCactus;
import teamrtg.rtg.api.tools.deco.DecoDeadBush;
import teamrtg.rtg.api.tools.deco.DecoDesertWell;


/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionDesert extends DecoCollectionBase
{

	public DecoCollectionDesert()
	{
		super();
		
        DecoDesertWell decoDesertWell = new DecoDesertWell();
        decoDesertWell.maxY = 80;
        decoDesertWell.strengthFactor = 1f;
        decoDesertWell.chance = 160;
        this.addDeco(decoDesertWell);

		DecoCactus decoCactus = new DecoCactus();
		decoCactus.maxY = 90;
		decoCactus.strengthFactor = 3f;
		decoCactus.chance = 2;
        this.addDeco(decoCactus);
        
        DecoDeadBush decoDeadBush = new DecoDeadBush();
		decoDeadBush.maxY = 128;
		decoDeadBush.strengthFactor = 2f;
		decoDeadBush.chance = 3;
        this.addDeco(decoDeadBush);
	}
}
