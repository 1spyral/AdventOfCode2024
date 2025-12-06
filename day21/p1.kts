// AOC 2024 Day 21 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()
val data: String = reader.readAllAsString()

val lines = data.lines()

var ans = 0

// +---+---+---+
// | 7 | 8 | 9 |
// +---+---+---+
// | 4 | 5 | 6 |
// +---+---+---+
// | 1 | 2 | 3 |
// +---+---+---+
// | 0 | A |
// +---+---+
val numPad = mapOf(
    '7' to Pair(0, 0),
    '8' to Pair(1, 0),
    '9' to Pair(2, 0),
    '4' to Pair(0, 1),
    '5' to Pair(1, 1),
    '6' to Pair(2, 1),
    '1' to Pair(0, 2),
    '2' to Pair(1, 2),
    '3' to Pair(2, 2),
    '0' to Pair(0, 3),
    'A' to Pair(1, 3)
)

//     +---+---+
//     | ^ | A |
// +---+---+---+
// | < | v | > |
// +---+---+---+
val dirPad = mapOf(
    '^' to Pair(1, 0),
    'A' to Pair(2, 0),
    '<' to Pair(0, 1),
    'v' to Pair(1, 1),
    '>' to Pair(2, 1)
)

fun numToDir(code: String): MutableList<String> {
    var out = mutableListOf("")

    var x = 1
    var y = 3

    for (char in code) {
        val next = mutableListOf<String>()

        val (targetX, targetY) = numPad[char]!!

        if (x == targetX && y == targetY) {
            for (seq in out) {
                next.add(seq + "A")
            }
        } else if (y == targetY) {
            val diff = targetX - x
            val dirs = if (diff < 0) "<".repeat(-diff) else ">".repeat(diff)
            for (seq in out) {
                next.add(seq + dirs + "A")
            }
        } else if (x == targetX) {
            val diff = targetY - y
            val dirs = if (diff < 0) "^".repeat(-diff) else "v".repeat(diff)
            for (seq in out) {
                next.add(seq + dirs + "A")
            }
        } else {
            val diffX = targetX - x
            val diffY = targetY - y
            if (y != 3 || targetX != 2) {
                val dirs = (if (diffX < 0) "<".repeat(-diffX) else ">".repeat(diffX)) +
                        if (diffY < 0) "^".repeat(-diffY) else "v".repeat(diffY)
                for (seq in out) {
                    next.add(seq + dirs + "A")
                }
            }
            if (targetY != 3 || x != 2) {
                val dirs = (if (diffY < 0) "^".repeat(-diffY) else "v".repeat(diffY)) +
                        if (diffX < 0) "<".repeat(-diffX) else ">".repeat(diffX)
                for (seq in out) {
                    next.add(seq + dirs + "A")
                }
            }
        }

        x = targetX
        y = targetY
        out = next
    }

    return out
}

fun dirToDir(code: String): MutableList<String> {
    var out = mutableListOf("")

    var x = 2
    var y = 0

    for (char in code) {
        val next = mutableListOf<String>()

        val (targetX, targetY) = dirPad[char]!!

        if (x == targetX && y == targetY) {
            for (seq in out) {
                next.add(seq + "A")
            }
        } else if (y == targetY) {
            val diff = targetX - x
            val dirs = if (diff < 0) "<".repeat(-diff) else ">".repeat(diff)
            for (seq in out) {
                next.add(seq + dirs + "A")
            }
        } else if (x == targetX) {
            val diff = targetY - y
            val dirs = if (diff < 0) "^".repeat(-diff) else "v".repeat(diff)
            for (seq in out) {
                next.add(seq + dirs + "A")
            }
        } else {
            val diffX = targetX - x
            val diffY = targetY - y
            if (y != 0 || targetX != 0) {
                val dirs = (if (diffX < 0) "<".repeat(-diffX) else ">".repeat(diffX)) +
                        if (diffY < 0) "^".repeat(-diffY) else "v".repeat(diffY)
                for (seq in out) {
                    next.add(seq + dirs + "A")
                }
            }
            if (targetY != 0 || x != 0) {
                val dirs = (if (diffY < 0) "^".repeat(-diffY) else "v".repeat(diffY)) +
                        if (diffX < 0) "<".repeat(-diffX) else ">".repeat(diffX)
                for (seq in out) {
                    next.add(seq + dirs + "A")
                }
            }
        }

        x = targetX
        y = targetY
        out = next
    }

    return out
}

fun numValue(code: String): Int {
    var out = 0
    for (char in code) {
        if (char.isDigit()) {
            out *= 10
            out += char.digitToInt()
        }
    }
    return out
}

for (line in lines) {
    // Step 1
    val step1 = numToDir(line)
    // Step 2
    val step2 = step1.flatMap(::dirToDir)
    // Step 3
    val step3 = step2.flatMap(::dirToDir)
    ans += numValue(line) * step3.minOfOrNull { x -> x.length }!!
}

print(ans)
