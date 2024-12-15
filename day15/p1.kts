// AOC 2024 Day 14 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.max

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val grid: MutableList<MutableList<Char>> = mutableListOf()

var input: String? = reader.readLine()
while (input != "") {
    grid.add(input!!.toMutableList())
    input = reader.readLine()
}

val height: Int = grid.size
val width: Int = grid[0].size

var x: Int = 0
var y: Int = 0

for (i in 0 until width) {
    for (j in 0 until height) {
        if (grid[i][j] == '@') {
            x = i
            y = j
        }
    }
}

fun swap(x1: Int, y1: Int, x2: Int, y2: Int) {
    val tmp: Char = grid[y1][x1]
    grid[y1][x1] = grid[y2][x2]
    grid[y2][x2] = tmp
}

val nullPair: Pair<Int, Int> = Pair(-1, -1)

fun move(x: Int, y: Int, dir: Char): Pair<Int, Int> {
    val newX: Int = when (dir) {
        '<' -> x - 1
        '>' -> x + 1
        else -> x
    }
    val newY: Int = when (dir) {
        '^' -> y - 1
        'v' -> y + 1
        else -> y
    }
    when (grid[newY][newX]) {
        '#' -> {
            return nullPair
        }
        'O' -> {
            if (move(newX, newY, dir) != nullPair) {
                swap(x, y, newX, newY)
                return Pair(newX, newY)
            }
            return nullPair
        }
        '.' -> {
            swap(x, y, newX, newY)
            return Pair(newX, newY)
        }
    }
    return nullPair
}

input = reader.readLine()
while (input != null) {
    input!!.forEach { char: Char ->
        val moved: Pair<Int, Int> = move(x, y, char)
        if (moved != nullPair) {
            x = moved.first
            y = moved.second
        }
    }
    input = reader.readLine()
}

var ans: Long = 0

for (x in 0 until width) {
    for (y in 0 until height) {
        if (grid[y][x] == 'O') {
            ans += 100 * y + x
        }
    }
}

println(ans)