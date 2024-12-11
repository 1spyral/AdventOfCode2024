// AOC 2024 Day 11 Problem 2

import java.io.File
import java.io.BufferedReader
import kotlin.math.log10
import kotlin.math.pow

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var stones: MutableMap<Long, Long> = mutableMapOf()

val input: String = reader.readLine()

input.split(" ").map { x: String -> x.toLong() }.map { x: Long ->
    stones[x] = stones[x]?.plus(1) ?: 1
}

val digits: (Long) -> Long = { x: Long -> log10(x.toDouble()).toLong() + 1 }

for (i in 0 until 75) {
    val newStones: MutableMap<Long, Long> = mutableMapOf()

    for ((stone, count) in stones) {
        if (stone == 0L) {
            newStones[1] = newStones[1]?.plus(count) ?: count
        } else if (digits(stone) % 2L == 0L) {
            val left = stone / 10.0.pow(digits(stone).toDouble() / 2).toLong()
            val right = stone % 10.0.pow(digits(stone).toDouble() / 2).toLong()
            newStones[left] = newStones[left]?.plus(count) ?: count
            newStones[right] = newStones[right]?.plus(count) ?: count
        } else {
            newStones[stone * 2024] = newStones[stone * 2024]?.plus(count) ?: count
        }
    }
    stones = newStones
}
println(stones.values.sum())