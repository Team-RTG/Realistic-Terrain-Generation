package exterminatorjeff.undergroundbiomes.api.enums;

/**
 * 
 * @author LouisDB
 *
 */
public interface Variant<T> extends Comparable<T> {

	int getMetadata();

	float getHardness();

	float getResistance();

}
