// AOC 2024 Day 3 Problem 2

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

/*
Stages:
1: m
2: u
3: l
4: (
5: num1 ,
6: num2 )
7: DONE
 */

var ans: Int = 0

var stage: Int = 1
var num1: Int = 0
var num2: Int = 0

var enabled: Boolean = true

var input: Int = reader.read()
while (input != -1) {
    val char: Char = input.toChar()

    if (stage == 1 && char == 'd') {
        stage = 11
        input = reader.read()
        continue
    } else if (stage > 10) {
        if (stage == 11 && char == 'o') {
            stage = 12
        } else if (stage == 12 && char == '(') {
            stage = 21
        } else if (stage == 21 && char == ')') {
            enabled = true
        } else if (stage == 12 && char == 'n') {
            stage = 31
        } else if (stage == 31 && char == '\'') {
            stage = 32
        } else if (stage == 32 && char == 't') {
            stage = 33
        } else if (stage == 33 && char == '(') {
            stage = 34
        } else if (stage == 34 && char == ')') {
            enabled = false
        } else {
            stage = 1
        }
    }

    if (stage == 1 && char == 'm' ||
        stage == 2 && char == 'u' ||
        stage == 3 && char == 'l' ||
        stage == 4 && char == '(' ||
        stage == 5 && char == ',' ||
        stage == 6 && char == ')') {
        stage++
    } else if (stage == 5 && char.isDigit()) {
        num1 *= 10
        num1 += char.digitToInt()
    } else if (stage == 6 && char.isDigit()) {
        num2 *= 10
        num2 += char.digitToInt()
    } else if (stage < 10) {
        stage = 1
        num1 = 0
        num2 = 0
    }
    if (stage == 7) {
        if (enabled && num1 < 1000 && num2 < 1000) {
            ans += num1 * num2
        }
        stage = 1
        num1 = 0
        num2 = 0
    }
    input = reader.read()
}

println(ans)