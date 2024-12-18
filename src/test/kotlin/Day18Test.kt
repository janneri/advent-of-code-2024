
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day18Test {
    @Test
    fun part1_test() {
        val result = Day18(readInput("Day18_test")).part1(7, 12)
        assertEquals(22, result)
    }

    @Test
    fun part1_real() {
        val result = Day18(readInput("Day18")).part1(71, 1024)
        assertEquals(370, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day18(readInput("Day18_test")).part2(7)
        assertEquals("(6, 1)", result)
    }

    @Test
    fun part2_real() {
        val result = Day18(readInput("Day18")).part2(71)
        assertEquals("(65, 6)", result)
    }
}