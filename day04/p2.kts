// AOC 2024 Day 4 Problem 2

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

val width: Int = matrix[0].size
val height: Int = matrix.size

fun isSOrM(x: Int, y: Int): Boolean {
    return matrix[x][y] == 'S' || matrix[x][y] == 'M'
}

fun check(x: Int, y: Int): Boolean {
    val middle: Boolean = matrix[x][y] == 'A'
    val side1: Boolean = isSOrM(x - 1, y - 1) && isSOrM(x + 1, y + 1) && matrix[x - 1][y - 1] != matrix[x + 1][y + 1]
    val side2: Boolean = isSOrM(x - 1, y + 1) && isSOrM(x + 1, y - 1) && matrix[x - 1][y + 1] != matrix[x + 1][y - 1]
    return middle && side1 && side2
}

for (i in 1 until width - 1) {
    for (j in 1 until height - 1) {
        if (check(i, j)) {
            ans++
        }
    }
}

print(ans)