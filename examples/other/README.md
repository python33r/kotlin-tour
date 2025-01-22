# other

Miscellaneous examples of other aspects of Kotlin.

`extensions.kts` shows how existing types can be extended with new functions
and properties, without the need for subclassing.

`letalso.kts` demonstrates the `let` and `also` scope functions, showing how
the can be used in conjunction with the safe call operator.

`also2.kts` is another example of using the `also` scope function - in this
case, as a way of adding validity checking code onto the initialization of
a property. This provides an alternative to using an `init` block.

`with.kts` shows how the `with` scope function can be used.

`typecast.kt` demonstrates type checking and smart casting, via a class
with an overridden `equals()` method.

`observed.kt` shows how Kotlin's `observable` delegate can be used to track
changes to the value of a variable.

`limited.kts` shows how a variable's range of values can be limited by using
the `vetoable` delegate to veto changes that might take it out of range.

`mapped.kts` demonstrates delegation of property storage to a map. This
makes the class behave a little like Python, which uses dicts in a similar
way.
