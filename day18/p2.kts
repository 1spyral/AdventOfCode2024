// AOC 2024 Day 18 Problem 2

import java.io.File
import java.io.BufferedReader
import java.util.LinkedList

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val width: Int = 71
val height: Int = 71

var reachable: MutableList<MutableList<Boolean>> = mutableListOf()

for (y in 0 until height) {
    reachable.add(mutableListOf())
    for (x in 0 until width) {
        reachable[y].add(true)
    }
}

fun add(x: Int, y: Int, vis: MutableList<MutableList<Boolean>>, q: LinkedList<Pair<Int, Int>>) {
    if (x in 0 until width && y in 0 until height && reachable[y][x] && !vis[y][x]) {
        q.addLast(Pair(x, y))
    }
}

var input: String? = reader.readLine()
while (input != null) {
    var byteX: Int
    var byteY: Int
    with (input!!.split(",").map { x: String -> x.toInt() }) {
        byteX = get(0)
        byteY = get(1)
    }
    if (!reachable[byteY][byteX]) {
        input = reader.readLine()
        continue
    }
    reachable[byteY][byteX] = false
    val reached: MutableList<MutableList<Boolean>> = mutableListOf()
    for (j in 0 until height) {
        reached.add(mutableListOf())
        for (i in 0 until width) {
            reached[j].add(false)
        }
    }
    val q: LinkedList<Pair<Int, Int>> = LinkedList()
    q.add(Pair(0, 0))
    while (q.isNotEmpty()) {
        var x: Int
        var y: Int
        with (q.pollFirst()) {
            x = first
            y = second
        }
        if (reached[y][x]) {
            continue
        }
        reached[y][x] = true
        add(x - 1, y, reached, q)
        add(x + 1, y, reached, q)
        add(x, y - 1, reached, q)
        add(x, y + 1, reached, q)
    }

    reachable = reached
    if (!reachable[height - 1][width - 1]) {
        print("$byteX,$byteY")
        break
    }
    input = reader.readLine()
}
