// AOC 2024 Day 18 Problem 1

import java.io.File
import java.io.BufferedReader
import java.util.LinkedList

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val width: Int = 71
val height: Int = 71

val grid: MutableList<MutableList<Boolean>> = mutableListOf()

for (y in 0 until height) {
    grid.add(mutableListOf())
    for (x in 0 until width) {
        grid[y].add(true)
    }
}

var input: String? = reader.readLine()
for (i in 0 until 1024) {
    with (input!!.split(",").map { x: String -> x.toInt() }) {
        grid[get(1)][get(0)] = false
    }
    input = reader.readLine()
}

val visited: MutableList<MutableList<Boolean>> = mutableListOf()
val dist: MutableList<MutableList<Int>>  = mutableListOf()

for (y in 0 until height) {
    visited.add(mutableListOf())
    dist.add(mutableListOf())
    for (x in 0 until width) {
        visited[y].add(false)
        dist[y].add(Int.MAX_VALUE)
    }
}

val q: LinkedList<Pair<Int, Int>> = LinkedList()
q.add(Pair(0, 0))
dist[0][0] = 0

fun add(x: Int, y: Int, distance: Int) {
    if (x in 0 until width && y in 0 until height && grid[y][x] && !visited[y][x]) {
        q.addLast(Pair(x, y))
        dist[y][x] = distance + 1
    }
}

while (q.isNotEmpty()) {
    var x: Int
    var y: Int
    with (q.pollFirst()) {
        x = first
        y = second
    }
    if (visited[y][x]) {
        continue
    }
    visited[y][x] = true
    add(x - 1, y, dist[y][x])
    add(x + 1, y, dist[y][x])
    add(x, y - 1, dist[y][x])
    add(x, y + 1, dist[y][x])
}

println(dist[height - 1][width - 1])