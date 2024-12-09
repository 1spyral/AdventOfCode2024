// AOC 2024 Day 6 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Int = 0

val grid: MutableList<MutableList<Char>> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    grid.add(input!!.toMutableList())
    input = reader.readLine()
}

val width: Int = grid[0].size
val height: Int = grid.size

var x: Int = 0
var y: Int = 0

start@for (i in 0 until width) {
    for (j in 0 until height) {
        if (grid[j][i] == '^') {
            x = i
            y = j
            break@start
        }
    }
}

/*
Directions
0 - ↑
1 - →
2 - ↓
3 - ←
 */
var dir: Int = 0

while (true) {
    if (grid[y][x] != 'v') {
        grid[y][x] = 'v'
        ans++
    }
    val nextX: Int = when (dir) {
        1 -> x + 1
        3 -> x - 1
        else -> x
    }
    val nextY: Int = when (dir) {
        0 -> y - 1
        2 -> y + 1
        else -> y
    }
    if (!(nextX in 0 until width && nextY in 0 until height)) {
        break
    } else if (grid[nextY][nextX] == '#') {
        dir++
        dir %= 4
    } else {
        x = nextX
        y = nextY
    }
}

println(ans)