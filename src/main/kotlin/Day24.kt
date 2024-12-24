import util.parseSections

// See puzzle in https://adventofcode.com/2024/day/24

class Day24(input: String) {
    private val inputSections = parseSections(input)

    private val wires = inputSections[0].map { str ->
        val parts = str.split(": ")
        parts[0] to parts[1].toInt()
    }.toMap().toMutableMap()

    private val gates = inputSections[1].map { str ->
        str.split(" ").let { Gate(it[0], it[1], it[2], it[4]) }
    }
    private
    data class Gate(val in1: String, val op: String, val in2: String, val out: String) {
        fun findOutput(wires: Map<String, Int>): Int? = when {
            out in wires -> wires[out]!!
            in1 !in wires || in2 !in wires -> null
            op == "AND" -> wires[in1]!!.and(wires[in2]!!)
            op == "OR" -> wires[in1]!!.or(wires[in2]!!)
            op == "XOR" -> wires[in1]!!.xor(wires[in2]!!)
            else -> throw Error("Invalid op")
        }
    }

    fun part11(): Long {
        while (gates.any { it.findOutput(wires) != null }) {
            gates.forEach { gate ->
                gate.findOutput(wires)?.let { wires[gate.out] = it }
            }
        }

        val bitString = wires.keys
            .filter { it.startsWith("z") }
            .sortedDescending()
            .joinToString(separator = "") { wires[it].toString() }

        return bitString.toLong(2)
    }

    private fun solveWires() {
        var hasAllValues = false
        while (!hasAllValues) {
            hasAllValues = true
            gates.forEach { gate ->
                val output = gate.findOutput(wires)
                if (output != null) {
                    wires[gate.out] = output
                }
                else {
                    hasAllValues = false
                }
            }
        }
    }

    private fun findBitStr(letter: String): String {
        val keys = wires.keys.filter { it.startsWith(letter) }.sortedDescending()
        val bitStr = keys.fold("") { acc, s ->
            acc + wires[s]!!
        }
        return bitStr
    }

    fun part1(): Long {
        solveWires()
        val bitStr = findBitStr("z")
        return bitStr.toLong(2)
    }

    private fun print() {
        val bitStrX = findBitStr("x")
        val bitStrY = findBitStr("y")
        val bitStrZ = findBitStr("z")
        val sum = bitStrX.toLong(2) + bitStrY.toLong(2)
        println("X: " + bitStrX + " " + bitStrX.toLong(2))
        println("Y: " + bitStrY + " " + bitStrY.toLong(2))
        println("Z: " + bitStrZ + " " + bitStrZ.toLong(2))
        println("=  " + sum.toString(2) + " " + sum)
    }

    fun part2(): String {
        // See the rules of full-adder circuit:
        // https://www.build-electronic-circuits.com/full-adder/
        val BIT_LENGTH = 45

        val incorrect = mutableListOf<String>()
        for (i in 0 until BIT_LENGTH) {
            val id = i.toString().padStart(2, '0')

            val xor1 = gates.find {
                ((it.in1 == "x$id" && it.in2 == "y$id") || (it.in1 == "y$id" && it.in2 == "x$id")) && it.op == "XOR"
            }

            val and1 = gates.find {
                ((it.in1 == "x$id" && it.in2 == "y$id") || (it.in1 == "y$id" && it.in2 == "x$id")) && it.op == "AND"
            }
            val z = gates.find { it.out == "z$id" }

            if (xor1 == null || and1 == null || z == null) continue

            // each z must be connected to an XOR
            if (z.op != "XOR") incorrect.add(z.out)

            // each AND must go to an OR (besides the first case as it starts the carry flag)
            val or = gates.find { it.in1 == and1.out || it.in2 == and1.out }
            if (or != null && or.op != "OR" && i > 0) incorrect.add(and1.out)

            // the first XOR must go to XOR or AND
            val after = gates.find { it.in1 == xor1.out || it.in2 == xor1.out }
            if (after != null && after.op == "OR") incorrect.add(xor1.out)
        }

        // each XOR must be connected to an x, y, or z
        incorrect.addAll(
            gates.filter {
                !it.in1.startsWith("x") && !it.in1.startsWith("y") &&
                        !it.in2.startsWith("x") && !it.in2.startsWith("y") &&
                        !it.out.startsWith("z") && it.op == "XOR"
            }.map { it.out }
        )

        val result = incorrect.sorted().joinToString(separator = ",")
        println(result)
        return result
    }
}
