// AOC 2024 Day 2 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Int = 0

var input: String? = reader.readLine()
while (input != null) {
    val values: List<Int> = input!!.split(" ").map { x: String -> x.toInt() }
    val inc: Int = values[1] - values[0]

    ans++
    for (i in 1 until values.size) {
        val difference: Int = values[i] - values[i - 1]
        if (!(abs(difference) in 1..3 && difference * inc > 0)) {
            ans--
            break
        }
    }
    input = reader.readLine()
}

println(ans)