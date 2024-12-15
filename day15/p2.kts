// AOC 2024 Day 15 Problem 2

import java.io.File
import java.io.BufferedReader
import kotlin.math.max
import kotlin.math.min

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val grid: MutableList<MutableList<Char>> = mutableListOf()

var input: String? = reader.readLine()
var index: Int = 0
while (input != "") {
    grid.add(mutableListOf())
    for (char in input!!) {
        when (char) {
            '@' -> {
                grid[index].add(char)
                grid[index].add('.')
            }
            'O' -> {
                grid[index].add('[')
                grid[index].add(']')
            }
            else -> {
                grid[index].add(char)
                grid[index].add(char)
            }
        }
    }
    index++
    input = reader.readLine()
}

val height: Int = grid.size
val width: Int = grid[0].size

var x: Int = 0
var y: Int = 0

for (i in 0 until width) {
    for (j in 0 until height) {
        if (grid[j][i] == '@') {
            x = i
            y = j
        }
    }
}

fun swap(x1: Int, y1: Int, x2: Int, y2: Int) {
    val tmp: Char = grid[y1][x1]
    grid[y1][x1] = grid[y2][x2]
    grid[y2][x2] = tmp
}

val nullPair: Pair<Int, Int> = Pair(-1, -1)

fun canMove(x: Int, y: Int, dir: Char, partner: Boolean = false): Boolean {
    val newX: Int = when (dir) {
        '<' -> x - 1
        '>' -> x + 1
        else -> x
    }
    val newY: Int = when (dir) {
        '^' -> y - 1
        'v' -> y + 1
        else -> y
    }
    if ((grid[y][x] == '[' && dir == '>') || (grid[y][x] == ']' && dir == '<')) {
        return canMove(newX, newY, dir)
    }
    var canPartnerMove: Boolean = true
    if (!partner && grid[y][x] == '[' && (dir == '^' || dir == 'v')) {
        canPartnerMove = canMove(x + 1, y, dir, true)
    } else if (!partner && grid[y][x] == ']' && (dir == '^' || dir == 'v')) {
        canPartnerMove = canMove(x - 1, y, dir, true)
    }
    when (grid[newY][newX]) {
        '#' -> {
            return false
        }
        '[', ']' -> {
            return canMove(newX, newY, dir) && canPartnerMove
        }
        '.' -> {
            return canPartnerMove
        }
    }
    return false
}

fun move(x: Int, y: Int, dir: Char, partner: Boolean = false): Pair<Int, Int> {
    val newX: Int = when (dir) {
        '<' -> x - 1
        '>' -> x + 1
        else -> x
    }
    val newY: Int = when (dir) {
        '^' -> y - 1
        'v' -> y + 1
        else -> y
    }
    if (!partner && grid[y][x] == '[' && (dir == '^' || dir == 'v')) {
        move(x + 1, y, dir, true)
    } else if (!partner && grid[y][x] == ']' && (dir == '^' || dir == 'v')) {
        move(x - 1, y, dir, true)
    }
    if (grid[newY][newX] != '.') {
        move(newX, newY, dir)
    }
    swap(x, y, newX, newY)
    return Pair(newX, newY)
}

input = reader.readLine()
while (input != null) {
    input!!.forEach { char: Char ->
        if (canMove(x, y, char)) {
            with (move(x, y, char)) {
                x = first
                y = second
            }
        }
    }
    input = reader.readLine()
}

var ans: Long = 0

for (x in 0 until width) {
    for (y in 0 until height) {
        if (grid[y][x] == '[') {
            ans += 100 * y + x
        }
    }
}

println(ans)