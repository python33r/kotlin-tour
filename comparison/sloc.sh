#!/bin/bash
# Estimates Source Lines Of Code (SLOC) for the two examples

echo "SLOC (app only)"
echo
echo "  Java:" `cat java-gps/src/main/java/*.java | grep -c -v '^$'`
echo "Kotlin:" `cat kotlin-gps/src/main/kotlin/*.kt | grep -c -v '^$'`
echo
echo "SLOC (app + tests)"
echo
echo "  Java:" `cat java-gps/src/{main,test}/java/*.java | grep -c -v '^$'`
echo "Kotlin:" `cat kotlin-gps/src/{main,test}/kotlin/*.kt | grep -c -v '^$'`
echo
