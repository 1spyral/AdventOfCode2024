// AOC 2024 Day 10 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val grid: MutableList<List<Int>> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    grid.add(input!!.toCharArray().map { x: Char -> x.digitToInt() })

    input = reader.readLine()
}

val width: Int = grid[0].size
val height: Int = grid.size

fun traverse(x: Int, y: Int, visited: MutableSet<Pair<Int, Int>>) {
    if (grid[y][x] == 9) {
        visited.add(Pair(x, y))
    }
    // ↑
    if (y - 1 in 0 until height && grid[y - 1][x] == grid[y][x] + 1) {
        traverse(x, y - 1, visited)
    }
    // ↓
    if (y + 1 in 0 until height && grid[y + 1][x] == grid[y][x] + 1) {
        traverse(x, y + 1, visited)
    }
    // →
    if (x + 1 in 0 until width && grid[y][x + 1] == grid[y][x] + 1) {
        traverse(x + 1, y, visited)
    }
    // ←
    if (x - 1 in 0 until width && grid[y][x - 1] == grid[y][x] + 1) {
        traverse(x - 1, y, visited)
    }
}

var ans: Int = 0

for (x in 0 until width) {
    for (y in 0 until height) {
        if (grid[y][x] == 0) {
            val visited: MutableSet<Pair<Int, Int>> = mutableSetOf()
            traverse(x, y, visited)
            ans += visited.size
        }
    }
}

println(ans)
