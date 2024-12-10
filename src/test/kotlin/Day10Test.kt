import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day10Test {
    @Test
    fun part1_test() {
        val result = Day10(readInput("Day10_test")).part1()
        assertEquals(36, result)
    }

    @Test
    fun part1_real() {
        val result = Day10(readInput("Day10")).part1()
        assertEquals(459, result)
    }

    @Test
    fun part2_test2() {
        val result = Day10(readInput("Day10_test2")).part2()
        assertEquals(3, result)
    }

    @Test
    fun part2_test() {
        val result = Day10(readInput("Day10_test")).part2()
        assertEquals(81, result)
    }

    @Test
    fun part2_real() {
        val result = Day10(readInput("Day10")).part2()
        assertEquals(1034, result)
    }
}