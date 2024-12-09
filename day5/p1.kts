// AOC 2024 Day 5 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Int = 0

val rules: MutableMap<Int, MutableList<Int>> = mutableMapOf()

var input: String? = reader.readLine()
while (input != "") {
    val values: List<Int> = input!!.split("|").map { x: String -> x.toInt() }
    if (rules.contains(values[0])) {
        rules[values[0]]?.add(values[1])
    } else {
        rules[values[0]] = mutableListOf(values[1])
    }

    input = reader.readLine()
}

input = reader.readLine()
check@while (input != null) {
    val values: List<Int> = input!!.split(",").map { x: String -> x.toInt() }
    val seen: MutableSet<Int> = mutableSetOf()

    for (i in values) {
        seen.add(i)
        if (rules.contains(i)) {
            for (j in rules[i]!!) {
                if (seen.contains(j)) {
                    input = reader.readLine()
                    continue@check
                }
            }
        }
    }
    ans += values[values.size / 2]
    input = reader.readLine()
}

print(ans)