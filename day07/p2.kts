// AOC 2024 Day 7 Problem 2

import java.io.File
import java.io.BufferedReader
import kotlin.math.floor
import kotlin.math.log
import kotlin.math.pow

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
            val digits: Double = floor(log(nums[i].toDouble(), 10.0)) + 1
            newAnswers.add(answer * 10.0.pow(digits).toLong() + nums[i])
        }
        answers = newAnswers
    }
    if (req in answers) {
        ans += req
    }
    input = reader.readLine()
}

println(ans)