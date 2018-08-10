package rtg.api.util;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * This annotation should be used to indicate that a class is a utility and should be <b>final</b>,
 * with a <b>private no-args constructor</b>, and only contains <b>static</b> members.
 */
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.SOURCE)
public @interface UtilityClass {

}
