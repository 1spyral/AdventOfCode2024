// AOC 2024 Day 16 Problem 2

import java.io.File
import java.io.BufferedReader
import java.util.PriorityQueue

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
val prev: MutableList<MutableList<MutableList<MutableList<Triple<Int, Int, Int>>>>> = mutableListOf()

for (y in 0 until height) {
    visited.add(mutableListOf())
    dist.add(mutableListOf())
    prev.add(mutableListOf())
    for (x in 0 until width) {
        visited[y].add(mutableListOf(false, false, false, false))
        dist[y].add(mutableListOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE))
        prev[y].add(mutableListOf(mutableListOf(), mutableListOf(), mutableListOf(), mutableListOf()))
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
    var x: Int
    var y: Int
    var dir: Int
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
        if (dist[y][x][dir] + 1 <= dist[newY][newX][dir]) {
            if (dist[y][x][dir] + 1 < dist[newY][newX][dir]) {
                prev[newY][newX][dir].clear()
            }
            prev[newY][newX][dir].add(Triple(x, y, dir))
            dist[newY][newX][dir] = dist[y][x][dir] + 1
            pq.add(Triple(newX, newY, dir))
        }
    }
    // Turn counter-clockwise
    var newDir: Int = (dir + 3) % 4
    if (dist[y][x][dir] + 1000 <= dist[y][x][newDir]) {
        if (dist[y][x][dir] + 1000 < dist[y][x][newDir]) {
            prev[y][x][newDir].clear()
        }
        prev[y][x][newDir].add(Triple(x, y, dir))
        dist[y][x][newDir] = dist[y][x][dir] + 1000
        pq.add(Triple(x, y, newDir))
    }
    // Turn clockwise
    newDir = (dir + 1) % 4
    if (dist[y][x][dir] + 1000 <= dist[y][x][newDir]) {
        if (dist[y][x][dir] + 1000 < dist[y][x][newDir]) {
            prev[y][x][newDir].clear()
        }
        prev[y][x][newDir].add(Triple(x, y, dir))
        dist[y][x][newDir] = dist[y][x][dir] + 1000
        pq.add(Triple(x, y, newDir))
    }
}

val best: MutableList<MutableList<Boolean>> = mutableListOf()
for (y in 0 until height) {
    best.add(mutableListOf())
    for (x in 0 until width) {
        best[y].add(false)
    }
}

var ans: Int = 0

fun visit(x: Int, y: Int, dir: Int) {
    if (x == -1) {
        return
    }
    if (!best[y][x]) {
        best[y][x] = true
        ans++
    }
    for ((newX, newY, newDir) in prev[y][x][dir]) {
        visit(newX, newY, newDir)
    }
}

visit(width - 2, 1, dist[1][width - 2].indexOf(dist[1][width - 2].min()))

println(ans)