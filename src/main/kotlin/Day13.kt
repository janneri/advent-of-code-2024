import util.parseSections

// See puzzle in https://adventofcode.com/2024/day/13

class Day13(input: String) {
    private val equationRegex = """Button .: X\+(\d+), Y\+(\d+)""".toRegex()
    private val priceRegex = """Prize: X=(\d+), Y=(\d+)""".toRegex()
    private val inputSections = parseSections(input)
    private val machines = inputSections.map { parseMachine(it) }

    // Represents a linear equation X*a + Y*b = c
    data class Equation(val a: Long, val b: Long, val c: Long) {
        fun check(A: Long, B: Long, adjustedC: Long) = (A * a + B * b) == adjustedC
    }

    data class Machine(val eq1: Equation, val eq2: Equation)

    private fun parseMachine(lines: List<String>): Machine {
        val (a1, a2) = equationRegex.find(lines[0])!!.destructured
        val (b1, b2) = equationRegex.find(lines[1])!!.destructured
        val (c1, c2) = priceRegex.find(lines[2])!!.destructured

        return Machine(
            Equation(a1.toLong(), b1.toLong(), c1.toLong()),
            Equation(a2.toLong(), b2.toLong(), c2.toLong())
        )
    }

    private fun solveByElimination(e1: Equation,
                                   e2: Equation,
                                   limit: Int?
    ): Pair<Long, Long>? {
        val determinant = e1.a * e2.b - e2.a * e1.b

        if (determinant == 0L) {
            throw Error("The system has infinitely many solutions or no solution.")
        }

        val adjustedC1 = if (limit == null) e1.c + 10_000_000_000_000L else e1.c
        val adjustedC2 = if (limit == null) e2.c + 10_000_000_000_000L else e2.c

        val A = (adjustedC1 * e2.b - adjustedC2 * e1.b) / determinant
        val B = (e1.a * adjustedC2 - e2.a * adjustedC1) / determinant

        val isInBounds = limit == null || (A <= limit && B <= limit)

        return if (e1.check(A, B, adjustedC1) && e2.check(A, B, adjustedC2) && isInBounds) {
            A to B
        } else {
            null
        }
    }

    private fun solve(limit: Int?): Long = machines
        .mapNotNull { solveByElimination(it.eq1, it.eq2, limit) }
        .sumOf { (a, b) -> a * 3 + b }

    fun part1(): Long = solve(limit = 100)

    fun part2(): Long = solve(limit = null)

}