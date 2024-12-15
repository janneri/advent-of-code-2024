
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day14Test {
    @Test
    fun part1_test() {
        val result = Day14(readInput("Day14_test")).part1(11, 7)
        assertEquals(12, result)
    }

    @Test
    fun part1_real() {
        val result = Day14(readInput("Day14")).part1(101, 103)
        assertEquals(223020000, result)
    }
    
    @Test
    fun part2_real() {
        val result = Day14(readInput("Day14")).part2(101, 103)
        assertEquals(7338, result)
    }
}