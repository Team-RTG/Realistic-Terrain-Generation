package sgcraft.api;

import gcewing.sg.FeatureUnderDesertPyramid;

import java.util.LinkedList;

import net.minecraft.world.gen.structure.ComponentScatteredFeaturePieces;


public class SGCraftAPI
{
	public void addStargateToDesertTempleComponents(ComponentScatteredFeaturePieces.DesertPyramid desertpyramid, LinkedList desertTempleComponents)
	{
    	FeatureUnderDesertPyramid stargate = new FeatureUnderDesertPyramid(desertpyramid);
    	desertTempleComponents.add(stargate);
	}
}
