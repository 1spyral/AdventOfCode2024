// AOC 2024 Day 22 Problem 2

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val prune: Long = 16777216 - 1

fun next(x: Long): Long {
    var num: Long = x
    num = (num xor (num shl 6)) and prune
    num = (num xor (num shr 5)) and prune
    num = (num xor (num shl 11)) and prune
    return num
}

val arr: MutableList<MutableList<Long>> = mutableListOf()
val diff: MutableList<MutableList<Long>> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    val ls: MutableList<Long> = mutableListOf()
    val diffLs: MutableList<Long> = mutableListOf()
    var x: Long = input!!.toLong()
    ls.add(x % 10)
    for (i in 1 .. 2000) {
        x = next(x)
        ls.add(x % 10)
        diffLs.add(ls[i] - ls[i - 1] + 9)
    }
    arr.add(ls)
    diff.add(diffLs)
    input = reader.readLine()
}

val sums: MutableList<Long> = mutableListOf()

for (i in 0 until 19 * 19 * 19 * 19) {
    sums.add(0)
}

for (i in diff.indices) {
    val visited: HashSet<Int> = hashSetOf()
    for (j in 3 until 2000) {
        val a: Int = diff[i][j - 3].toInt()
        val b: Int = diff[i][j - 2].toInt()
        val c: Int = diff[i][j - 1].toInt()
        val d: Int = diff[i][j].toInt()
        val index: Int = d + 19 * c + 19 * 19 * b + 19 * 19 * 19 * a
        if (!visited.contains(index)) {
            visited.add(index)
            sums[index] += arr[i][j + 1]
        }
    }
}

println(sums.max())