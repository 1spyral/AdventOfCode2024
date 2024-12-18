// AOC 2024 Day 8 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Int = 0

val grid: MutableList<CharArray> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    grid.add(input!!.toCharArray())
    input = reader.readLine()
}

val width: Int = grid[0].size
val height: Int = grid.size

val visited: MutableList<MutableList<Boolean>> = mutableListOf()
for (i in 0 until height) {
    visited.add(mutableListOf())
    for (j in 0 until width) {
        visited[i].add(false)
    }
}

val locations: MutableMap<Char, MutableList<Pair<Int, Int>>> = mutableMapOf()

for (i in 0 until height) {
    for (j in 0 until width) {
        if (grid[i][j] != '.') {
            if (locations.contains(grid[i][j])) {
                locations[grid[i][j]]?.add(Pair(j, i))
            } else {
                locations[grid[i][j]] = mutableListOf(Pair(j, i))
            }
        }
    }
}

for (frequency in locations.values) {
    for (i in 0 until frequency.size - 1) {
        for (j in i + 1 until frequency.size) {
            val x1: Int = frequency[i].first
            val x2: Int = frequency[j].first
            val y1: Int = frequency[i].second
            val y2: Int = frequency[j].second

            val xDiff: Int = x2 - x1
            val yDiff: Int = y2 - y1

            val newX1: Int = x1 - xDiff
            val newY1: Int = y1 - yDiff

            if (newX1 in 0 until width && newY1 in 0 until height && !visited[newY1][newX1]) {
                visited[newY1][newX1] = true
                ans++
            }

            val newX2: Int = x2 + xDiff
            val newY2: Int = y2 + yDiff

            if (newX2 in 0 until width && newY2 in 0 until height && !visited[newY2][newX2]) {
                visited[newY2][newX2] = true
                ans++
            }
        }
    }
}
println(ans)