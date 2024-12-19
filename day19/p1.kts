// AOC 2024 Day 19 Problem 1

import java.io.File
import java.io.BufferedReader
import java.util.LinkedList

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val width: Int = 71
val height: Int = 71

var input: String? = reader.readLine()
val towels: List<String> = input!!.split(", ")

fun build(towel: String, chain: LinkedList<Int>): Boolean {
    val current: String = chain.joinToString("") { x: Int -> towels[x] }
    if (current == towel) {
        return true
    }
    for ((i, next) in towels.withIndex()) {
        if (current.length + next.length <= towel.length && next == towel.substring(current.length, current.length + next.length)) {
            chain.addLast(i)
            if (build(towel, chain)) {
                return true
            }
            chain.pollLast()
        }
    }
    return false
}

var ans: Int = 0

reader.readLine()
input = reader.readLine()
while (input != null) {
    val chain: LinkedList<Int> = LinkedList()
    if (build(input!!, chain)) {
        ans++
    }
    input = reader.readLine()
}

println(ans)