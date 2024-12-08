// AOC 2024 Day 1 Part 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val list1: MutableList<Int> = mutableListOf()
val list2: MutableList<Int> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    val values: List<String> = input!!.split(Regex("\\s+"))
    list1.add(values[0].toInt())
    list2.add(values[1].toInt())
    input = reader.readLine()
}

list1.sort()
list2.sort()

var sum: Int = 0

for (i in 0 until list1.size) {
    sum += abs(list1[i] - list2[i])
}

println(sum)