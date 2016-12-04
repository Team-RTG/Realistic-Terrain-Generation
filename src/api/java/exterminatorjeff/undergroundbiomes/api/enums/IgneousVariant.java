package exterminatorjeff.undergroundbiomes.api.enums;

import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.util.IStringSerializable;

/**
 * 
 * @author CurtisA, LouisDB
 *
 */
public enum IgneousVariant implements IStringSerializable, Variant<IgneousVariant> {
	RED_GRANITE(1.7F, 1.42F), BLACK_GRANITE(1.6F, 1.39F), RHYOLITE(1.3F, 1.26F), ANDESITE(1.4F, 1.31F), GABBRO(1.0F, 1.0F), BASALT(1.4F, 1.31F), KOMATIITE(1.5F, 1.35F), DACITE(1.2F, 1.2F);

	public static final IgneousVariant[] IGNEOUS_VARIANTS = values();
	public static final int NB_VARIANTS = values().length;
	public static final PropertyEnum<IgneousVariant> IGNEOUS_VARIANT_PROPERTY = PropertyEnum.create("type", IgneousVariant.class);

	private final float hardness;
	private final float resistance;

	private IgneousVariant(float hardness, float resistance) {
		this.hardness = hardness;
		this.resistance = resistance;
	}

	@Override
	public int getMetadata() {
		return ordinal();
	}

	@Override
	public float getHardness() {
		return hardness;
	}

	@Override
	public float getResistance() {
		return resistance;
	}

	@Override
	public String toString() {
		return name().toLowerCase();
	}

	@Override
	public String getName() {
		return toString();
	}

}