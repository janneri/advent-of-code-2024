import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day04Test {
    @Test
    fun part1_test() {
        val result = Day04(readInput("Day04_test")).part1()
        assertEquals(18, result)
    }

    @Test
    fun part1_real() {
        val result = Day04(readInput("Day04")).part1()
        assertEquals(2536, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day04(readInput("Day04_test")).part2()
        assertEquals(9, result)
    }

    @Test
    fun part2_real() {
        val result = Day04(readInput("Day04")).part2()
        assertEquals(1875, result)
    }
}