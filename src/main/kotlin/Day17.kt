
import util.parseInts
import util.parseSections

// See puzzle in https://adventofcode.com/2024/day/17

class Day17(input: String) {
    private val inputSections = parseSections(input)
    private val program = parseInts(inputSections[1][0])
    private val computer = parseComputer(inputSections[0])

    private fun parseComputer(registerLines: List<String>) = registerLines
        .map { it.substringAfter(": ").toLong() }
        .let { Computer(it[0], it[1], it[2]) }

    data class Computer(var registerA: Long,
                        var registerB: Long,
                        var registerC: Long,
                        var instructionPointer: Int = 0
    ) {
        fun execute(program: List<Int>): String = buildString {
            while (instructionPointer < program.size) {
                val opcode = program[instructionPointer]

                when (opcode) {
                    0 -> registerA /= (1L shl getOperand(program).toInt())
                    1 -> registerB = registerB xor program[instructionPointer + 1].toLong()
                    2 -> registerB = getOperand(program) % 8
                    3 -> if (registerA != 0L) instructionPointer = program[instructionPointer + 1]
                    else instructionPointer += 2
                    4 -> registerB = registerB xor registerC
                    5 -> append(getOperand(program) % 8)
                    6 -> registerB = registerA / (1L shl getOperand(program).toInt())
                    7 -> registerC = registerA / (1L shl getOperand(program).toInt())
                }
                if (opcode != 3) instructionPointer += 2
            }
        }

        private fun getOperand(program: List<Int>) =
            when (val operand = program[instructionPointer + 1]) {
                in 0..3 -> operand.toLong()
                4 -> registerA
                5 -> registerB
                6 -> registerC
                else -> throw IllegalArgumentException("Invalid operand: $operand")
            }
    }

    fun part1(): String = computer.execute(program)

    fun part2(): Long {
        val target = program.joinToString("")
        var current = (1L..8L).toSet()

        while (true) {
            val next = mutableSetOf<Long>()

            for (candidateA in current) {
                val output = computer.copy(registerA = candidateA).execute(program)

                if (output == target) return candidateA // Found solution

                // Focus only on values that produce valid suffixes
                // to avoid unnecessary computation for invalid states
                if (target.endsWith(output)) {
                    // The program shifts A by three with each run
                    // A = 25 (11001) -> A % 8 = 3 (11), removing 001
                    // A = 31 (11111) -> A % 8 = 3 (11), removing 111
                    // That's why each A has 8 possible predecessors
                    val shifted = candidateA * 8
                    next.addAll((0..7).map { shifted + it })
                }
            }

            current = next // Continue with the expanded set
        }
    }
}