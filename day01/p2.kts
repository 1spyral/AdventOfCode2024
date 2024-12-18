// AOC 2024 Day 1 Part 2

import java.io.File
import java.io.BufferedReader

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

var sum: Int = 0

for (i in list1) {
    sum += i * list2.count { x: Int -> x == i }
}

println(sum)