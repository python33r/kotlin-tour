# Python equivalent to stats.kts

import sys
import statistics

if len(sys.argv) != 2:
    sys.exit("Usage: python stats.py <data-file>")

with open(sys.argv[1], "rt") as infile:
    data = [ float(line) for line in infile ]

print(f"Minimum = {min(data)}")
print(f"Maximum = {max(data)}")
print(f"Mean = {statistics.mean(data)}")
