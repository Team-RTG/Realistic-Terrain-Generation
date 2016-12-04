package exterminatorjeff.undergroundbiomes.api.enums;

/**
 * 
 * @author LouisDB
 *
 */
public enum UBStoneType {
	IGNEOUS, METAMORPHIC, SEDIMENTARY;

	@Override
	public String toString() {
		return name().toLowerCase();
	}

}