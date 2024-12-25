// AOC 2024 Day 25 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

class Lock(val x1: Int, val x2: Int, val x3: Int, val x4: Int, val x5: Int)
class Key(val x1: Int, val x2: Int, val x3: Int, val x4: Int, val x5: Int)

val locks: MutableList<Lock> = mutableListOf()
val keys: MutableList<Key> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    var x1: Int = 0
    var x2: Int = 0
    var x3: Int = 0
    var x4: Int = 0
    var x5: Int = 0
    val key: Boolean = input!![0] == '.'
    while (input != "" && input != null) {
        if (input!![0] == '#') {
            x1++
        }
        if (input!![1] == '#') {
            x2++
        }
        if (input!![2] == '#') {
            x3++
        }
        if (input!![3] == '#') {
            x4++
        }
        if (input!![4] == '#') {
            x5++
        }
        input = reader.readLine()
    }
    if (key) {
        keys.add(Key(x1, x2, x3, x4, x5))
    } else {
        locks.add(Lock(x1, x2, x3, x4, x5))
    }
    input = reader.readLine()
}

var ans: Int = 0

for (lock in locks) {
    for (key in keys) {
        if (key.x1 + lock.x1 <= 7 && key.x2 + lock.x2 <= 7 && key.x3 + lock.x3 <= 7 && key.x4 + lock.x4 <= 7 && key.x5 + lock.x5 <= 7) {
            ans++
        }
    }
}

println(ans)
