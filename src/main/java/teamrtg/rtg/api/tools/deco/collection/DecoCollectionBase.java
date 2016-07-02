package teamrtg.rtg.api.tools.deco.collection;

import java.util.ArrayList;

import teamrtg.rtg.api.tools.feature.tree.rtg.TreeRTG;
import teamrtg.rtg.api.world.biome.deco.DecoBase;

/**
 * 
 * @author WhichOnesPink
 *
 */
public class DecoCollectionBase
{
	
	public ArrayList<DecoBase> decos;
	public ArrayList<TreeRTG> rtgTrees;
	
	public DecoCollectionBase()
	{
		this.decos = new ArrayList<DecoBase>();
		this.rtgTrees = new ArrayList<TreeRTG>();
	}
	
	public void addDeco(DecoBase deco)
	{
		this.decos.add(deco);
	}
	
	public void addDeco(DecoBase deco, boolean allowed)
	{
		if (allowed) {
			this.decos.add(deco);
		}
	}
	
    /**
     * Adds a tree to the list of RTG trees associated with this collection.
     * The 'allowed' parameter allows us to pass biome config booleans dynamically when configuring the trees in the collection.
     * 
     * @param tree
     * @param allowed
     */
    public void addTree(TreeRTG tree, boolean allowed)
    {
    	if (allowed) {

	    	this.rtgTrees.add(tree);
    	}
    }
    
    /**
     * Convenience method for addTree() where 'allowed' is assumed to be true.
     * 
     * @param tree
     */
    public void addTree(TreeRTG tree)
    {
    	this.addTree(tree, true);
    }
}
