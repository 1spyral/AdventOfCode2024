// AOC 2024 Day 20 Problem 2

import java.io.File
import java.io.BufferedReader
import kotlin.math.abs

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val grid: MutableList<CharArray> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    grid.add(input!!.toCharArray())

    input = reader.readLine()
}

val visited: MutableList<MutableList<Boolean>> = mutableListOf()
val dist: MutableList<MutableList<Int>> = mutableListOf()

val width: Int = grid[0].size
val height: Int = grid.size

for (y in 0 until height) {
    visited.add(mutableListOf())
    dist.add(mutableListOf())
    for (x in 0 until width) {
        visited[y].add(false)
        dist[y].add(0)
    }
}

var startX: Int = 0
var startY: Int = 0
var endX: Int = 0
var endY: Int = 0

for (x in 0 until width) {
    for (y in 0 until height) {
        if (grid[y][x] == 'E') {
            endX = x
            endY = y
        } else if (grid[y][x] == 'S') {
            startX = x
            startY = y
        }
    }
}

dist[startY][startX] = 0

var x: Int = startX
var y: Int = startY

fun visit(nextX: Int, nextY: Int): Boolean {
    if (nextX in 0 until width && nextY in 0 until height && grid[nextY][nextX] != '#' && !visited[nextY][nextX]) {
        dist[nextY][nextX] = dist[y][x] + 1
        x = nextX
        y = nextY
        return true
    }
    return false
}

while (!visited[endY][endX]) {
    visited[y][x] = true
    if (visit(x - 1, y)) {
        continue
    }
    if (visit(x + 1, y)) {
        continue
    }
    if (visit(x, y + 1)) {
        continue
    }
    if (visit(x, y - 1)) {
        continue
    }
}

var ans: Int = 0

for (y in 0 until height) {
    for (x in 0 until width) {
        if (!visited[y][x]) {
            continue
        }
        for (diffX in -20..20) {
            for (diffY in (abs(diffX) - 20)..(20 - abs(diffX))) {
                val endX: Int = x + diffX
                val endY: Int = y + diffY
                if (endX in 0 until width && endY in 0 until height && visited[endY][endX]) {
                    if (dist[endY][endX] - dist[y][x] - abs(diffX) - abs(diffY) >= 100) {
                        ans++
                    }
                }
            }
        }
    }
}

println(ans)