package highlands.api;

import java.util.List;

import net.minecraft.entity.EnumCreatureType;
public interface IHighlandsBiome {
	public void setSpawnLists(List monster, List creature, List waterCreature);
	public List getSpawnableList(EnumCreatureType par1EnumCreatureType);
}
