// AOC 2024 Day 12 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val grid: MutableList<CharArray> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    grid.add(input!!.toCharArray())
    input = reader.readLine()
}

val width: Int = grid[0].size
val height: Int = grid.size

val visited: MutableList<MutableList<Boolean>> = mutableListOf()

for (y in 0 until height) {
    visited.add(mutableListOf())
    for (x in 0 until width) {
        visited[y].add(false)
    }
}

// Return area and perimeter
fun visit(x: Int, y: Int): Pair<Int, Int> {
    var area: Int = 1
    var peri: Int = 4

    visited[y][x] = true

    if (y - 1 in 0 until height && grid[y - 1][x] == grid[y][x]) {
        peri--
        if (!visited[y - 1][x]) {
            with (visit(x, y - 1)) {
                area += first
                peri += second
            }
        }
    }
    if (y + 1 in 0 until height && grid[y + 1][x] == grid[y][x]) {
        peri--
        if (!visited[y + 1][x]) {
            with (visit(x, y + 1)) {
                area += first
                peri += second
            }
        }
    }
    if (x - 1 in 0 until width && grid[y][x - 1] == grid[y][x]) {
        peri--
        if (!visited[y][x - 1]) {
            with (visit(x - 1, y)) {
                area += first
                peri += second
            }
        }
    }
    if (x + 1 in 0 until width && grid[y][x + 1] == grid[y][x]) {
        peri--
        if (!visited[y][x + 1]) {
            with (visit(x + 1, y)) {
                area += first
                peri += second
            }
        }
    }
    return Pair(area, peri)
}

var ans: Int = 0

for (x in 0 until width) {
    for (y in 0 until height) {
        if (!visited[y][x]) {
            with (visit(x, y)) {
                ans += first * second
            }
        }
    }
}

println(ans)
