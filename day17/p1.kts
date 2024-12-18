// AOC 2024 Day 17 Problem 1

import java.io.File
import java.io.BufferedReader
import kotlin.math.pow

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var input: String = reader.readLine()
var a: Int = input.split(" ")[2].toInt()

input = reader.readLine()
var b: Int = input.split(" ")[2].toInt()

input = reader.readLine()
var c: Int = input.split(" ")[2].toInt()

reader.readLine()
input = reader.readLine()
val program: List<Int> = input.split(" ")[1].split(",").map { x: String -> x.toInt() }

var instruction: Int = 0
val output: MutableList<Int> = mutableListOf()

fun combo(op: Int): Int {
    return when (op) {
        0, 1, 2, 3 -> op
        4 -> a
        5 -> b
        6 -> c
        else -> -1
    }
}

val adv = { op: Int ->
    a = (a / 2.0.pow(combo(op))).toInt()
}
val bxl = { op: Int ->
    b = b xor op
}
val bst = { op: Int ->
    b = combo(op) % 8
}
val jnz = { op: Int -> // Jump operator!
    if (a != 0) {
        instruction = op
    }
}
val bxc = { op: Int ->
    b = b xor c
}
val out = { op: Int ->
    output.add(combo(op) % 8)
}
val bdv = { op: Int ->
    b = (a / 2.0.pow(combo(op))).toInt()
}
val cdv = { op: Int ->
    c = (a / 2.0.pow(combo(op))).toInt()
}

while (instruction < program.size) {
    val prevInstruction: Int = instruction
    when (program[instruction]) {
        0 -> adv
        1 -> bxl
        2 -> bst
        3 -> jnz
        4 -> bxc
        5 -> out
        6 -> bdv
        else -> cdv // 7
    }(program[instruction + 1])

    if (program[prevInstruction] != 3 || a == 0) {
        instruction += 2
    }
}

println(output.joinToString(","))