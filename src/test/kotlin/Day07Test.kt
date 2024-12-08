import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day07Test {
    @Test
    fun part1_test() {
        val result = Day07(readInput("Day07_test")).part1()
        assertEquals(3749, result)
    }

    @Test
    fun part1_real() {
        val result = Day07(readInput("Day07")).part1()
        assertEquals(303876485655, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day07(readInput("Day07_test")).part2()
        assertEquals(11387, result)
    }

    @Test
    fun part2_real() {
        val result = Day07(readInput("Day07")).part2()
        assertEquals(146111650210682, result)
    }
}