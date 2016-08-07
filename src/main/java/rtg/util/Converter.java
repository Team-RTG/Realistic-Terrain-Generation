package rtg.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

/**
 * A class for converting one type into another
 *
 * @author Zeno410
 */
public abstract class Converter<FromType, ToType> {

    /**
     * Creates a new instance of Converter
     */
    public Converter() {

    }

    abstract public ToType result(FromType original);

    public ToType of(FromType original) {

        return result(original);
    }


    public Collection<ToType> listResult(Collection<FromType> source) {

        Collection<ToType> result = new ArrayList<ToType>(source.size());
        Iterator<? extends FromType> sourceList = source.iterator();
        while (sourceList.hasNext()) {
            result.add(result(sourceList.next()));
        }
        return result;
    }

    public Acceptor<FromType> acceptor(final Acceptor<ToType> target) {

        return new Acceptor<FromType>() {

            public void accept(FromType accepted) {

                target.accept(result(accepted));
            }
        };
    }

    public <FinalType> Converter<FromType, FinalType> composedWith(Converter<ToType, FinalType> following) {

        return new ChainTo<FinalType>(following);
    }

    public Converter<Named<FromType>, Named<ToType>> namer() {

        return new NamedConverter<FromType, ToType>(this);
    }

    @SuppressWarnings("hiding")
    public <FromType, Type2, Type3> Iterator<Type3> forEach(
        Converter<FromType, Converter<Type2, Type3>> converterizer,
        Iterator<Type2> data) {

        return null;
    }

    public static class Constant<CFromType, CToType> extends Converter<CFromType, CToType> {

        private final CToType value;

        public Constant(CToType theValue) {

            value = theValue;
        }

        public CToType result(CFromType original) {

            return value;
        }
    }

    class IteratorConverter implements Iterator<ToType> {

        Iterator<? extends FromType> source;

        IteratorConverter(Iterator<? extends FromType> theSource) {

            source = theSource;
        }

        public boolean hasNext() {

            return source.hasNext();
        }

        public ToType next() {

            return result(source.next());
        }

        public void remove() {

            source.remove();
        }
    }

    public class ChainTo<FinalType> extends Converter<FromType, FinalType> {

        Converter<ToType, FinalType> finalizer;

        public ChainTo(Converter<ToType, FinalType> theFinalizer) {

            finalizer = theFinalizer;
        }

        public FinalType result(FromType original) {

            return finalizer.result(Converter.this.result(original));
        }
    }
}

class NamedConverter<FromType, ToType>
    extends Converter<Named<FromType>, Named<ToType>> {

    Converter<FromType, ToType> handler;

    public NamedConverter(Converter<FromType, ToType> theHandler) {

        handler = theHandler;
    }

    public Named<ToType> result(Named<FromType> original) {

        return new Named<ToType>(original.name, handler.result(original.object));
    }

}
