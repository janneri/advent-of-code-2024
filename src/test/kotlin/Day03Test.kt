import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day03Test {
    @Test
    fun part1_test() {
        val result = Day03(readInput("Day03_test")).part1()
        assertEquals(161, result)
    }

    @Test
    fun part1_real() {
        val result = Day03(readInput("Day03")).part1()
        assertEquals(169021493, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day03(readInput("Day03_test2")).part2()
        assertEquals(48, result)
    }

    @Test
    fun part2_real() {
        val result = Day03(readInput("Day03")).part2()
        assertEquals(111762583, result)
    }
}