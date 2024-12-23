// AOC 2024 Day 23 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.max
import kotlin.math.min

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val ids: MutableMap<String, Long> = mutableMapOf()
val t: MutableList<Boolean> = mutableListOf()
val connections: MutableList<MutableList<Long>> = mutableListOf()

var count: Long = 0

var input: String? = reader.readLine()
while (input != null) {
    val pair = input!!.split("-")
    val a = pair[0]
    val b = pair[1]

    if (!ids.contains(a)) {
        ids[a] = count
        t.add(a.startsWith("t"))
        connections.add(mutableListOf())
        count++
    }
    if (!ids.contains(b)) {
        ids[b] = count
        t.add(b.startsWith("t"))
        connections.add(mutableListOf())
        count++
    }
    connections[ids[a]!!.toInt()].add(ids[b]!!)
    connections[ids[b]!!.toInt()].add(ids[a]!!)

    input = reader.readLine()
}

val reverse = ids.map { (k , v) -> v to k }.toMap()

// biggest * count * count + middle * count + smallest
val seen: HashSet<Long> = HashSet()

for (i in 0 until count) {
    if (t[i.toInt()]) {
        for (j in connections[i.toInt()]) {
            for (k in connections[j.toInt()]) {
                if (i in connections[k.toInt()]) {
                    val biggest = max(max(i, j), k)
                    val smallest = min(min(i, j), k)
                    val middle = if (i != biggest && i != smallest) {
                        i
                    } else if (j != biggest && j != smallest) {
                        j
                    } else {
                        k
                    }
                    seen.add(biggest * count * count + middle * count + smallest)
                }
            }
        }
    }
}

println(seen.size)
