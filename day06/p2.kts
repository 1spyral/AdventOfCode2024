// AOC 2024 Day 6 Problem 2

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

var startX: Int = 0
var startY: Int = 0

start@for (i in 0 until width) {
    for (j in 0 until height) {
        if (grid[j][i] == '^') {
            startX = i
            startY = j
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
var x: Int = startX
var y: Int = startY

while (true) {
    if (grid[y][x] == '.') {
        grid[y][x] = 'v'
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

fun check(obstacleX: Int, obstacleY: Int): Int {
    var dir: Int = 0
    var x: Int = startX
    var y: Int = startY

    val visited: MutableList<MutableList<Int>> = mutableListOf()

    for (i in 0 until height) {
        visited.add(mutableListOf())
        for (j in 0 until width) {
            visited[i].add(0)
        }
    }

    while (true) {
        visited[y][x]++
        if (visited[y][x] == 5) {
            return 1
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
            return 0
        } else if (grid[nextY][nextX] == '#' || nextX == obstacleX && nextY == obstacleY) {
            dir++
            dir %= 4
        } else {
            x = nextX
            y = nextY
        }
    }
}

for (i in 0 until width) {
    for (j in 0 until height) {
        if (grid[j][i] == 'v') {
            ans += check(i, j)
        }
    }
}

println(ans)