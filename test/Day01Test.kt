import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day01Test {
    @Test
    fun part1_test() {
        val result = Day01(readInput("Day01_test")).part1()
        assertEquals(142, result)
    }

    @Test
    fun part1_real() {
        val result = Day01(readInput("Day01")).part1()
        assertEquals(54630, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day01(readInput("Day01_test2")).part2()
        assertEquals(281, result)
    }

    @Test
    fun part2_real() {
        val result = Day01(readInput("Day01")).part2()
        assertEquals(54770, result)
    }
}