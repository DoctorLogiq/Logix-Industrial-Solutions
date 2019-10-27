package com.mods.logix.API.security;

import java.lang.annotation.*;

/**
 * If this annotation is present with a function, it means that the function can only be called
 * from the classes specified in the annotation's {@link #whitelist()} value.
 * <br/><br/>
 * Attempts to call this function from other places will result in exceptions being thrown around,
 * and you do not want that!
 * <br/><br/>
 * This is intended to protect parts of the mod which must be public (because Java sucks and doesn't have friend
 * classes), but are not intended to be used by other modders who are extending any of the Logix mods.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface WhitelistedAccess
{
    Class[] whitelist();
}