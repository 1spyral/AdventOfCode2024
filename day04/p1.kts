// AOC 2024 Day 4 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Int = 0

val matrix: MutableList<CharArray> = mutableListOf()

var input: String? = reader.readLine()
while (input != null) {
    matrix.add(input!!.toCharArray())
    input = reader.readLine()
}

val width = matrix[0].size
val height = matrix.size

/*
Directions:
1 : ↑
2 : ↑→
3 : →
4 : ↓→
5 : ↓
6: ↓←
7: ←
8: ←↑
 */
fun search(x: Int, y: Int, dir: Int, find: Char = 'X'): Boolean {
    if (matrix[x][y] == find) {
        if (find == 'S') {
            return true
        }
        val nextX: Int = when (dir) {
            2, 3, 4 -> x + 1
            6, 7, 8 -> x - 1
            else -> x
        }
        val nextY: Int = when (dir) {
            4, 5, 6 -> y + 1
            1, 2, 8 -> y - 1
            else -> y
        }
        if (nextX in 0 until width && nextY in 0 until height) {
            val nextFind = when (find) {
                'X' -> 'M'
                'M' -> 'A'
                else -> 'S'
            }
            return search(nextX, nextY, dir, nextFind)
        }
    }
    return false
}

for (i in 0 until width) {
    for (j in 0 until height) {
        for (k in 1..8) {
            if (search(i, j, k)) {
                ans++
            }
        }
    }
}

print(ans)