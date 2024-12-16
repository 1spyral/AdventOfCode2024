// AOC 2024 Day 16 Problem 1

import java.io.File
import java.io.BufferedReader
import java.util.PriorityQueue
import kotlin.math.min

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

val visited: MutableList<MutableList<MutableList<Boolean>>> = mutableListOf()
val dist: MutableList<MutableList<MutableList<Int>>> = mutableListOf()

for (y in 0 until height) {
    visited.add(mutableListOf())
    dist.add(mutableListOf())
    for (x in 0 until width) {
        visited[y].add(mutableListOf(false, false, false, false))
        dist[y].add(mutableListOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE))
    }
}

val pq: PriorityQueue<Triple<Int, Int, Int>> = PriorityQueue<Triple<Int, Int, Int>>(compareBy { x: Triple<Int, Int, Int> -> dist[x.second][x.first][x.third] })

// x y dir
/* dir:
0 ↑ North
1 → East
2 ↓ South
3 ← West
 */
pq.add(Triple(1, height - 2, 1))
dist[height - 2][1][1] = 0

while (pq.isNotEmpty()) {
    var x: Int = 0
    var y: Int = 0
    var dir: Int = 0
    with (pq.poll()) {
        x = first
        y = second
        dir = third
    }
    if (visited[y][x][dir]) {
        continue
    }
    visited[y][x][dir] = true
    // Move forward
    val newX: Int = when (dir) {
        1 -> x + 1
        3 -> x - 1
        else -> x
    }
    val newY: Int = when (dir) {
        0 -> y - 1
        2 -> y + 1
        else -> y
    }
    if (grid[newY][newX] != '#') {
        dist[newY][newX][dir] = min(dist[newY][newX][dir], dist[y][x][dir] + 1)
        pq.add(Triple(newX, newY, dir))
    }
    // Turn counter-clockwise
    var newDir: Int = (dir + 3) % 4
    dist[y][x][newDir] = min(dist[y][x][newDir], dist[y][x][dir] + 1000)
    pq.add(Triple(x, y, newDir))
    // Turn clockwise
    newDir = (dir + 1) % 4
    dist[y][x][newDir] = min(dist[y][x][newDir], dist[y][x][dir] + 1000)
    pq.add(Triple(x, y, newDir))
}

println(dist[1][width - 2].min())