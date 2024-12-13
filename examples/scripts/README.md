# scripts

Examples of KotlinScript.

KotlinScript operates almost exactly like Kotlin, except there is no
top-level `main` function, and no explicit compile step. This brings Kotlin
closer to the feel of Python.

`hello.kts` shows that 'Hello World' is a one-liner in KotlinScript, just
as it is in Python.

`grades.kts` is a more complex script that demonstrates the use of multiple
Kotlin features (functions, if & when expressions, for loops, maps and
string interpolation).

`subdirs.kts` is a practical example of interacting with the filesystem.
It is written in a semi-functional style, demonstrating chained method calls
and the use of lambda expressions to pass code into those methods.

`stats.kts` computes some basic statistics for data in a named file.
Compare this with the the equivalent Python program in `stats.py`.  The
Python code is only about 25% shorter.
