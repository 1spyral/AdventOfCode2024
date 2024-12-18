// AOC 2024 Day 9 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val input: String? = reader.readLine()

val chars: CharArray = input!!.toCharArray()
val blocks: MutableList<Long> = mutableListOf()
for (i in chars.indices) {
    for (j in 0 until chars[i].digitToInt()) {
        if (i % 2 == 0) {
            blocks.add(i / 2L)
        } else {
            blocks.add(-1)
        }
    }
}

var left: Int = 0
var right: Int = blocks.size - 1

var ans: Long = 0

while (left < right) {
    if (blocks[left] == -1L) {
        while (blocks[right] == -1L) {
            right--
        }
        if (right <= left) {
            break
        }
        ans += blocks[right] * left
        /* Uncomment to rearrange the blocks
        blocks[left] = blocks[right]
         */
        blocks[right] = -1L
    } else {
        ans += blocks[left] * left
    }
    left++
}
println(ans)

/* Uncomment to calculate checksum based on rearranged blocks
var ans2: Long = 0
for (i in 0 until blocks.size) {
    if (blocks[i] != -1L) {
        ans2 += blocks[i] * i
    }
}
println(ans2)
 */