// AOC 2024 Day 13 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.max

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val re: Regex = Regex("[^0-9]")

var ans: Int = 0

var input: String? = ""
while (input != null) {
    var aX: Int = 0
    var aY: Int = 0
    var bX: Int = 0
    var bY: Int = 0
    var targetX: Int = 0
    var targetY: Int = 0

    input = reader.readLine()
    with (input!!.split(" ")) {
        aX = get(2).replace(re, "").toInt()
        aY = get(3).replace(re, "").toInt()
    }
    input = reader.readLine()
    with (input!!.split(" ")) {
        bX = get(2).replace(re, "").toInt()
        bY = get(3).replace(re, "").toInt()
    }
    input = reader.readLine()
    with (input!!.split(" ")) {
        targetX = get(1).replace(re, "").toInt()
        targetY = get(2).replace(re, "").toInt()
    }
    var maxToken: Int = Int.MIN_VALUE
    for (a in 1..100) {
        if (a * aX > targetX || a * aY > targetY) {
            break
        }
        for (b in 1..100) {
            val x: Int = a * aX + b * bX
            val y: Int = a * aY + b * bY
            if (x > targetX || y > targetY) {
                break
            }
            if (x == targetX && y == targetY) {
                maxToken = max(maxToken, 3 * a + b)
            }
        }
    }
    if (maxToken != Int.MIN_VALUE) {
        ans += maxToken
    }
    input = reader.readLine()
}

println(ans)