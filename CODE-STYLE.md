Team-RTG Code style
===
This is the democratically created road to uniformity along all code in all Team-RTG repositories.
Before submitting a Pull Request, we kindly ask you to follow these few simple guidelines, as it will make our job a lot easier.

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
