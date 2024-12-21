
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day21Test {
    @Test
    fun part1_test() {
        val result = Day21(readInput("Day21_test")).part1()
        assertEquals(126384, result)
    }

    @Test
    fun part1_real() {
        val result = Day21(readInput("Day21")).part1()
        assertEquals(152942, result)
    }
    
    @Test
    fun part2_real() {
        val result = Day21(readInput("Day21")).part2()
        assertEquals(189235298434780L, result)
    }
}