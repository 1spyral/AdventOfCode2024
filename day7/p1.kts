// AOC 2024 Day 7 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

var ans: Long = 0

var input: String? = reader.readLine()
while (input != null) {
    val split: List<String> = input!!.split(":")
    val req: Long = split[0].toLong()
    val nums: List<Long> = split[1].trim().split(" ").map { x: String -> x.toLong() }
    var answers: MutableList<Long> = mutableListOf(nums[0])
    for (i in 1 until nums.size) {
        val newAnswers: MutableList<Long> = mutableListOf()
        for (answer in answers) {
            newAnswers.add(answer + nums[i])
            newAnswers.add(answer * nums[i])
        }
        answers = newAnswers
    }
    if (req in answers) {
        ans += req
    }
    input = reader.readLine()
}

println(ans)