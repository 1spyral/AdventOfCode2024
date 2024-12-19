// AOC 2024 Day 19 Problem 2

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var input: String? = reader.readLine()
val towels: Set<String> = input!!.split(", ").toSet()

val memo: MutableMap<String, Long> = mutableMapOf()

fun build(towel: String, start: Int = 0): Long {
    if (start == towel.length) {
        return 1
    }
    val substring = towel.substring(start, towel.length)
    if (memo.contains(substring)) {
        return memo[substring]!!
    }
    var ans: Long = 0
    for (i in start + 1..towel.length) {
        if (towels.contains(towel.substring(start, i))) {
            ans += build(towel, i)
        }
    }
    memo[towel.substring(start, towel.length)] = ans
    return ans
}

var ans: Long = 0

reader.readLine()
input = reader.readLine()
while (input != null) {
    ans += build(input!!)
    input = reader.readLine()
}

println(ans)