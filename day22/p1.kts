// AOC 2024 Day 22 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Long = 0

val prune: Long = 16777216 - 1

fun next(x: Long): Long {
    var num: Long = x
    num = (num xor (num shl 6)) and prune
    num = (num xor (num shr 5)) and prune
    num = (num xor (num shl 11)) and prune
    return num
}

var input: String? = reader.readLine()
while (input != null) {
    var x: Long = input!!.toLong()
    for (i in 0 until 2000) {
        x = next(x)
    }
    ans += x
    input = reader.readLine()
}

println(ans)