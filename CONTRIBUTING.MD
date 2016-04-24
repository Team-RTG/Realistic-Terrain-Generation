Contributing
===
So you wanna contribute to this Team RTG mod? Great, you are very welcome to do so! Just a few things before you do: We would really apreciate it if you conformed to our coding standards, as it would really easen our jobs.  
If you have any questions, you can hop on over to our [discord server] (https://discord.gg/0wIG7mz3g6RSfyq5).  
Happy coding!

Braces:
----
No newlines in front of opening braces
``` java
public static void sendCoffeeToPink(WhichOnesPink pink) {
    pink.send(new Coffee());
}
```

Spaces:
----
Just to be clear. This is java, not php. Please dont put spaces after, before or on top of any kinds of brackets.
``` java
pink.give(new Coffee(coffees[]));
```
That is the *only* correct way to do that line!

Also, indents are 4 spaces, not tabs! (throwinyavotes!)

Other spacing rules are: 
 - required in between parameters `(a, b)`,   
 - required in between opperators and opperants (`a + b`, `a == b`)
 - required in front of parentesis in `if`, `for` and `catch` statements
 - required in front of opening braces for statements and methods
 - not permitted between variable and `!` or `++`/`--`
``` java
public static void sendCoffeeToPink(WhichOnesPink pink, Coffee coffee) {
    pink.send(coffee);
    pink.setCoffees(pink.getCoffees() + 1);
    if (!pink.wantsCoffee()) {
      pink.doctor.call();
    }
}
```

Switch statements
----
 - `case` statements are indented and on new lines
 - `case` statements always end with `break;`
 - always ends with `default;`, even if unused
``` java
switch (drink) {
    case COFFEE:
        drink.giveTo(pink);
        break;
    case TEA:
        system.crash("Are you trying to poison me?");
        break;
    default:
        break;
}
```

Other Characters
----
 - Strings are with double quotes (`"`), unless `'` is needed for escaping reasons.

Naming
----
 - If a similar class or function to what you are adding already exists, name it with the same template.
 - If you are overriding a method all the parameters should have the same names as the overridden methods.  

### Coordinates ###
Minecraft has 3 types of coordinates, that we need to use in diffferent cases. Block coordinates, chunk coordinates,
and coordinates to a block relative to the chunk. For example, the block at block coordinates `x = 0, y = 64, z = 20` is in the chunk at `x = 0, z = 1`, and it has the chunk-local coordinate `x = 0, y = 64, z = 4`. To make matters worse, the chunk coordinates are sometimes refered to as `x` and `y` instead of `x` and `z`. So we have a few rules about coordinate naming. These are some of the rules we consider most important, since they can really create confusion:
 - Block coordinates should be referenced as `bx, by, bz` or simply `x, y, z`.
 - Chunk coordinates should be referenced as `cx, cz`, _never_ `x, z`, `x, y` or even `cx, cy`. 
 - Chunk-local block coordinates should be referenced as `lx, ly, lz`, never anything else.
 - Avoid using other names for any kind of coordinates, like `i, j, k` even in loops. If its a coordinate, give it the correct `x`, `y` or `z` and optionally some describing prefex.
