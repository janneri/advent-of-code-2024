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

    private data class Gate(val in1: String, val op: String, val in2: String, val out: String) {
        fun findOutput(wires: Map<String, Int>): Int? = when {
            out in wires -> wires[out]!!
            in1 !in wires || in2 !in wires -> null
            op == "AND" -> wires[in1]!!.and(wires[in2]!!)
            op == "OR" -> wires[in1]!!.or(wires[in2]!!)
            op == "XOR" -> wires[in1]!!.xor(wires[in2]!!)
            else -> throw Error("Invalid op")
        }

        fun getsInputFrom(other: Gate): Boolean = in1 == other.out || in2 == other.out

        fun hasNoXYInputs(): Boolean =
            listOf(in1, in2).none { it.startsWith("x") || it.startsWith("y") }

        fun inputIdsEqual(id: String) =
            (in1 == "x$id" && in2 == "y$id") || (in1 == "y$id" && in2 == "x$id")
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

    private fun findBitStr(char: Char): String = wires.keys
        .filter { it.startsWith(char) }
        .sortedDescending()
        .joinToString(separator = "") { wires[it].toString() }

    fun part1(): Long {
        solveWires()
        return findBitStr('z').toLong(2)
    }

    fun part2(): String {
        val incorrect = (0..44).fold(mutableListOf<String>()) { acc, idx ->
            val id = idx.toString().padStart(2, '0')

            // Find full adder circuit gates
            val xor1 = gates.find { it.inputIdsEqual(id) && it.op == "XOR" }!!
            val xor2 = gates.find { it.out == "z$id" }!!
            val and1 = gates.find { it.inputIdsEqual(id) && it.op == "AND" }!!
            val xor2OrAnd2 = gates.find { it.getsInputFrom(xor1) }
            val or = gates.find { it.getsInputFrom(and1) }

            // Validate gate outputs:
            if (xor2.op != "XOR") acc.add(xor2.out)
            if (or != null && or.op != "OR" && idx > 0) acc.add(and1.out)
            if (xor2OrAnd2 != null && xor2OrAnd2.op !in setOf("XOR", "AND")) acc.add(xor1.out)

            acc
        }

        val misplacedXorOutputs = gates
            .filter { it.op == "XOR" }
            .filter { gate -> gate.hasNoXYInputs() && !gate.out.startsWith("z") }
            .map { it.out }

        incorrect.addAll(misplacedXorOutputs)

        return incorrect.sorted().joinToString(separator = ",")
    }
}
