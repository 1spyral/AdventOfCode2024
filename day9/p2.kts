// AOC 2024 Day 9 Problem 2

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val input: String? = reader.readLine()

val chars: CharArray = input!!.toCharArray()
val files: MutableList<Pair<Int, Int>> = mutableListOf()
for (i in chars.indices) {
    if (i % 2 == 0) {
        files.add(Pair(i / 2, chars[i].digitToInt()))
    } else {
        files.add(Pair(-1, chars[i].digitToInt()))
    }
}

var index: Int = files.size - 1

fun merger() {
    for (i in files.size - 2 downTo 0) {
        if (files[i].first == files[i + 1].first) {
            files[i] = Pair(files[i].first, files[i].second + files[i + 1].second)
            files.removeAt(i + 1)
            if (i <= index) {
                index--
            }
        }
    }
}

move@while (index > 0) {
    merger()
    if (files[index].first != -1) {
        for (i in 0 until index) {
            if (files[i].first == -1 && files[i].second >= files[index].second) {
                files[index - 1] = Pair(-1, files[index - 1].second + files[index].second)
                files.add(i, files.removeAt(index))
                files[i + 1] = Pair(-1, files[i + 1].second - files[i].second)
                continue@move
            }
        }
    }
    index--
}

var current: Int = 0
var ans: Long = 0

for (i in files) {
    for (j in 0 until i.second) {
        if (i.first != -1) {
            ans += i.first * current
        }
        current++
    }
}

println(ans)