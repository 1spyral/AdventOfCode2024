// AOC 2024 Day 14 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.max

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val re: Regex = Regex("[^0-9-]")

// 1 2
// 3 4
var quad1: Long = 0
var quad2: Long = 0
var quad3: Long = 0
var quad4: Long = 0

val width: Long = 101
val height: Long = 103

var input: String? = reader.readLine()
while (input != null) {
    with (input!!.split(" ", ",").map { x: String -> x.replace(re, "").toLong() }) {
        val x: Long = (((get(0) + 100 * get(2)) % width) + width) % width
        val y: Long = (((get(1) + 100 * get(3)) % height) + height) % height
        when {
            x < width / 2 && y < height / 2 -> quad1++
            x < width / 2 && y > height / 2 -> quad3++
            x > width / 2 && y < height / 2 -> quad2++
            x > width / 2 && y > height / 2 -> quad4++
            else -> {}
        }

    }
    input = reader.readLine()
}

println(quad1 * quad2 * quad3 * quad4)