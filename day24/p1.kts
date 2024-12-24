// AOC 2024 Day 24 Problem 1

import java.io.File
import java.io.BufferedReader

val file: File = File("input.txt")
val reader: BufferedReader = file.bufferedReader()

val gates: MutableMap<String, Boolean> = mutableMapOf()

var input: String? = reader.readLine()
while (input != "") {
    with (input!!.split(": ")) {
        gates[get(0)] = get(1) == "1"
    }

    input = reader.readLine()
}

// 0 - AND, 1 - OR, 2 - XOR
class Operation(val x: String, val y: String, val z: String, val operation: Int)

val ids: HashSet<Int> = HashSet()
val operations: MutableList<Operation> = mutableListOf()

var count: Int = 0


input = reader.readLine()
while (input != null) {
    with (input!!.split(" ")) {
        operations.add(Operation(get(0), get(2), get(4), when (get(1)) {
            "AND" -> 0
            "OR" -> 1
            else -> 2
        }))
        ids.add(count)
        count++
    }
    input = reader.readLine()
}

while (ids.isNotEmpty()) {
    val remove: HashSet<Int> = HashSet()
    for (i in ids) {
        with (operations[i]) {
            if (gates.contains(x) && gates.contains(y)) {
                gates[z] = when (operation) {
                    0 -> gates[x]!! && gates[y]!!
                    1 -> gates[x]!! || gates[y]!!
                    else -> gates[x]!!.xor(gates[y]!!)
                }
                remove.add(i)
            }
        }
    }
    ids.removeAll(remove)
}

var ans: Long = 0

for (i in gates.filterKeys { x: String -> x.startsWith("z") }.keys.sortedDescending()) {
    ans = ans shl 1
    ans = ans or when (gates[i]) {
        true -> 1
        else -> 0
    }
}

println(ans)