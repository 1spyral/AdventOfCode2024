// AOC 2024 Day 12 Problem 2

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
val section: MutableList<MutableList<Int>> = mutableListOf()

for (y in 0 until height) {
    visited.add(mutableListOf())
    section.add(mutableListOf())
    for (x in 0 until width) {
        visited[y].add(false)
        section[y].add(-1)
    }
}

// Return area
fun visit(x: Int, y: Int, sectionNumber: Int): Int {
    var area: Int = 1

    visited[y][x] = true
    section[y][x] = sectionNumber

    if (y - 1 in 0 until height && grid[y - 1][x] == grid[y][x] && !visited[y - 1][x]) {
        area += visit(x, y - 1, sectionNumber)
    }
    if (y + 1 in 0 until height && grid[y + 1][x] == grid[y][x] && !visited[y + 1][x]) {
        area += visit(x, y + 1, sectionNumber)
    }
    if (x - 1 in 0 until width && grid[y][x - 1] == grid[y][x] && !visited[y][x - 1]) {
        area += visit(x - 1, y, sectionNumber)
    }
    if (x + 1 in 0 until width && grid[y][x + 1] == grid[y][x] && !visited[y][x + 1]) {
        area += visit(x + 1, y, sectionNumber)
    }
    return area
}

fun sides(sectionNumber: Int): Int {
    var ans: Int = 0
    var prev: Boolean = false
    // Check outsides
    // Top
    for (x in 0 until width) {
        if (section[0][x] == sectionNumber && !prev) {
            ans++
            prev = true
        } else if (section[0][x] != sectionNumber) {
            prev = false
        }
    }
    // Bottom
    prev = false
    for (x in 0 until width) {
        if (section[height - 1][x] == sectionNumber && !prev) {
            ans++
            prev = true
        } else if (section[height - 1][x] != sectionNumber) {
            prev = false
        }
    }
    // Left
    prev = false
    for (y in 0 until height) {
        if (section[y][0] == sectionNumber && !prev) {
            ans++
            prev = true
        } else if (section[y][0] != sectionNumber) {
            prev = false
        }
    }
    // Right
    prev = false
    for (y in 0 until height) {
        if (section[y][width - 1] == sectionNumber && !prev) {
            ans++
            prev = true
        } else if (section[y][width - 1] != sectionNumber) {
            prev = false
        }
    }
    // Vertical Sides
    // Left Vertical
    for (x in 0 until width - 1) {
        prev = false
        for (y in 0 until height) {
            if (section[y][x] == sectionNumber && section[y][x] != section[y][x + 1] && !prev) {
                ans++
                prev = true
            } else if (section[y][x] != sectionNumber || section[y][x] == section[y][x + 1]) {
                prev = false
            }
        }
    }
    // Right Vertical
    for (x in 1 until width) {
        prev = false
        for (y in 0 until height) {
            if (section[y][x] == sectionNumber && section[y][x] != section[y][x - 1] && !prev) {
                ans++
                prev = true
            } else if (section[y][x] != sectionNumber || section[y][x] == section[y][x - 1]) {
                prev = false
            }
        }
    }
    // Horizontal Sides
    // Top Horizontal
    for (y in 1 until height) {
        prev = false
        for (x in 0 until width) {
            if (section[y][x] == sectionNumber && section[y][x] != section[y - 1][x] && !prev) {
                ans++
                prev = true
            } else if (section[y][x] != sectionNumber || section[y][x] == section[y - 1][x]) {
                prev = false
            }
        }
    }
    // Bottom Horizontal
    for (y in 0 until height - 1) {
        prev = false
        for (x in 0 until width) {
            if (section[y][x] == sectionNumber && section[y][x] != section[y + 1][x] && !prev) {
                ans++
                prev = true
            } else if (section[y][x] != sectionNumber || section[y][x] == section[y + 1][x]) {
                prev = false
            }
        }
    }
    return ans
}

var ans: Int = 0
var sectionNumber: Int = 0

for (x in 0 until width) {
    for (y in 0 until height) {
        if (!visited[y][x]) {
            sectionNumber++
            ans += visit(x, y, sectionNumber) * sides(sectionNumber)
        }
    }
}

println(ans)
