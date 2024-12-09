// AOC 2024 Day 8 Problem 2

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

            var newX: Int = x1
            var newY: Int = y1

            while (newX in 0 until width && newY in 0 until height) {
                if (!visited[newY][newX]) {
                    visited[newY][newX] = true
                    ans++
                }
                newX -= xDiff
                newY -= yDiff
            }

            newX = x2
            newY = y2

            while (newX in 0 until width && newY in 0 until height) {
                if (!visited[newY][newX]) {
                    visited[newY][newX] = true
                    ans++
                }
                newX += xDiff
                newY += yDiff
            }
        }
    }
}
println(ans)