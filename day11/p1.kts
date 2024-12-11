// AOC 2024 Day 11 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.log10
import kotlin.math.pow

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var stones: MutableList<Long> = mutableListOf()

val input: String = reader.readLine()

stones.addAll(input.split(" ").map {x: String -> x.toLong()})

val digits: (Long) -> Long = { x: Long -> log10(x.toDouble()).toLong() + 1 }

for (i in 0 until 25) {
    val newStones: MutableList<Long> = mutableListOf()
    for (stone in stones) {
        if (stone == 0L) {
            newStones.add(1L)
        } else if (digits(stone) % 2L == 0L) {
            newStones.add(stone / 10.0.pow(digits(stone).toDouble() / 2).toLong())
            newStones.add(stone % 10.0.pow(digits(stone).toDouble() / 2).toLong())
        } else {
            newStones.add(stone * 2024)
        }
    }
    stones = newStones
}

println(stones.size)