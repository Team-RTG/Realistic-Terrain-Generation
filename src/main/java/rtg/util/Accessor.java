package rtg.util;


import java.lang.reflect.Field;
import java.util.Arrays;

/**
 * @author Zeno410
 *         Modified by topisani to allow for multiple possible field names.
 *         This is useful for catching both the obfuscated and the deobfuscated field.
 *         Updated and ported to 1.7.10 by srs_bsns
 */
public class Accessor<ObjectType, FieldType> {
    private final String[] fieldNames;
    private Field field;

    public Accessor(String... _fieldName) {
        fieldNames = _fieldName;
    }

    @SuppressWarnings("unchecked")
    public FieldType get(ObjectType object) {
        try {
            return (FieldType) (field(object).get(object));
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }

    private Field field(ObjectType example) {
        Class classObject = example.getClass();
        if (field == null) {
            try {
                setField(classObject);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return field;
    }

    private void setField(Class classObject) throws IllegalAccessException {
        // hunts through the class object and all superclasses looking for the field name
        Field[] fields;
        do {
            fields = classObject.getDeclaredFields();
            for (Field field1 : fields) {
                for (String fieldName : fieldNames) {
                    if (field1.getName().contains(fieldName)) {
                        field = field1;
                        field.setAccessible(true);
                        return;
                    }
                }
            }
            classObject = classObject.getSuperclass();
        } while (classObject != Object.class);
        throw new RuntimeException("None of " + Arrays.toString(fieldNames) + " found in class " + classObject.getName());
    }

    public void setField(ObjectType object, FieldType fieldValue) {
        try {
            field(object).set(object, fieldValue);
        } catch (IllegalArgumentException | IllegalAccessException ex) {
            throw new RuntimeException(ex);
        }
    }
}