Team-RTG Code style
===
This is the democratically created road to uniformity along all code in all Team-RTG repositories.
Before submitting a Pull Request, we kindly ask you to follow these few simple guidelines, as it will make our job a lot easier.

Braces:
----
*All* braces (yes *all*) go on the next line 
``` java
public static void sendCoffeeToPink(WhichOnesPink pink)
{
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

Other spacing rules are: 
 - required in between parameters `(a, b)`,   
 - required in between opperators and opperants (`a + b`, `a == b`)
 - required in front of parentesis in `if`, `for` and `catch` statements
 - not permitted between variable and `!` or `++`/`--`
``` java
public static void sendCoffeeToPink(WhichOnesPink pink, Coffee coffee)
{
    pink.send(coffee);
    pink.setCoffees(pink.getCoffees() + 1);
    if (!pink.wantsCoffee()) {
      pink.doctor.call();
    }
}
```
