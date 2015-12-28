
package Zeno410Utils;

/**
 *
 * @author Zeno410
 */
@SuppressWarnings("hiding")
public abstract class Function<Source,Product> {
    public abstract Product result(Source source);

    public KeyedRegistry<Source,Product> registry(){
        return new KeyedRegistry<Source,Product>(this);
    }
}
