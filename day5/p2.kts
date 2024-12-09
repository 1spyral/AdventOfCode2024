// AOC 2024 Day 5 Problem 2

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

fun reorder(values: MutableList<Int>): Int {
    loop@while (true) {
        val seen: MutableSet<Int> = mutableSetOf()
        for (i in values) {
            seen.add(i)
            if (rules.contains(i)) {
                for (j in rules[i]!!) {
                    if (seen.contains(j)) {
                        values.remove(i)
                        values.add(0, i)
                        continue@loop
                    }
                }
            }
        }
        return values[values.size / 2]
    }
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
                    ans += reorder(values.toMutableList())
                    input = reader.readLine()
                    continue@check
                }
            }
        }
    }
    input = reader.readLine()
}

print(ans)