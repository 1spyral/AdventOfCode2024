// AOC 2024 Day 13 Problem 2

import java.io.File
import java.io.BufferedReader
import kotlin.math.ceil

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val re: Regex = Regex("[^0-9]")

var ans: Long = 0

var input: String? = ""
while (input != null) {
    var aX: Long = 0
    var aY: Long = 0
    var bX: Long = 0
    var bY: Long = 0
    var targetX: Long = 10000000000000
    var targetY: Long = 10000000000000

    input = reader.readLine()
    with (input!!.split(" ")) {
        aX = get(2).replace(re, "").toLong()
        aY = get(3).replace(re, "").toLong()
    }
    input = reader.readLine()
    with (input!!.split(" ")) {
        bX = get(2).replace(re, "").toLong()
        bY = get(3).replace(re, "").toLong()
    }
    input = reader.readLine()
    with (input!!.split(" ")) {
        targetX += get(1).replace(re, "").toLong()
        targetY += get(2).replace(re, "").toLong()
    }
    val a: Double = (bY * targetX - bX * targetY).toDouble() / (aX * bY - bX * aY).toDouble()
    val b: Double = (targetY - aY * a) / bY.toDouble()

    if (a == ceil(a) && b == ceil(b)) {
        ans += 3 * a.toLong() + b.toLong()
    }

    input = reader.readLine()
}

println(ans)