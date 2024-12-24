// AOC 2024 Day 23 Problem 2

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val ids: MutableMap<String, Long> = mutableMapOf()
val visited: MutableList<Boolean> = mutableListOf()
val connections: MutableList<HashSet<Long>> = mutableListOf()

var count: Long = 0

var input: String? = reader.readLine()
while (input != null) {
    val pair = input!!.split("-")
    val a = pair[0]
    val b = pair[1]

    if (!ids.contains(a)) {
        ids[a] = count
        visited.add(false)
        connections.add(HashSet())
        count++
    }
    if (!ids.contains(b)) {
        ids[b] = count
        visited.add(false)
        connections.add(HashSet())
        count++
    }
    connections[ids[a]!!.toInt()].add(ids[b]!!)
    connections[ids[b]!!.toInt()].add(ids[a]!!)

    input = reader.readLine()
}

val reverse = ids.map { (k , v) -> v to k }.toMap()

var biggest: HashSet<Long> = hashSetOf()

var test: HashSet<Long> = hashSetOf()

fun check(x: Long): Boolean {
    for (i in test) {
        if (!connections[i.toInt()].contains(x)) {
            return false
        }
    }
    return true
}

fun add(x: Long) {
    if (visited[x.toInt()]) {
        return
    }
    test.add(x)
    if (test.size > biggest.size) {
        biggest.clear()
        biggest.addAll(test)
    }
    for (i in connections[x.toInt()]) {
        if (!test.contains(i) && check(i)) {
            add(i)
        }
    }
    test.remove(x)
}

for (i in 0 until count) {
    println(i)
    add(i)
    visited[i.toInt()] = true
}

println(biggest.map { x: Long -> reverse[x] }.sortedBy { x: String? -> x }.joinToString(","))