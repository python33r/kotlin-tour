# nullable

Examples of working with nullable types in Kotlin.

`nullable.kts` is a simple comparison of the behaviours of a nullable and
non-nullable type.

`nullcheck.kts` is an example of performing explicit null checks when working
with a nullable type. This code is more verbose than it needs to be!

`safecall.kts` is a better version of `nullcheck.kts`, in which the safe
call operator is used to avoid the need for explicit null checks.

`elvis.kts` demonstrates how the elvis operator simplifies code where we
need to generate a default of some kind when the result of computation
is null.
