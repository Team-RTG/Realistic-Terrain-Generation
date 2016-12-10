package exterminatorjeff.undergroundbiomes.api.common;

/**
 * Common interface for UB blocks and items that have variants.
 * 
 * @author LouisDB
 *
 */
public interface Variable {

	int getNbVariants();

	String getVariantName(int meta);

}
