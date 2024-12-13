// Lists subdirectories of a specified directory (or current directory)

import java.io.File

val dir = if (args.size > 0) args[0] else "."

File(dir).listFiles { it.isDirectory() }
         .forEach { println(it) }
