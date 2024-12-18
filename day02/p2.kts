// AOC 2024 Day 2 Problem 2

import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

fun check(values: List<Int>, remove: Int = -1): Int {
    val newValues: MutableList<Int> = values.toMutableList()
    if (remove > -1) {
        newValues.removeAt(remove)
    }
    val inc: Int = newValues[1] - newValues[0]
    for (i in 1 until newValues.size) {
        val difference: Int = newValues[i] - newValues[i - 1]
        if (!(abs(difference) in 1..3 && difference * inc > 0)) {
            if (remove == -1) {
                for (j in 0 until newValues.size) {
                    if (check(newValues, j) == 1) {
                        return 1
                    }
                }
            }
            return 0
        }
    }
    return 1
}

var ans: Int = 0

var input: String? = reader.readLine()
while (input != null) {
    val values: List<Int> = input!!.split(" ").map { x: String -> x.toInt() }
    ans += check(values)

    input = reader.readLine()
}

println(ans)