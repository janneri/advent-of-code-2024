import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day08Test {
    @Test
    fun part1_test() {
        val result = Day08(readInput("Day08_test")).part1()
        assertEquals(14, result)
    }

    @Test
    fun part1_real() {
        val result = Day08(readInput("Day08")).part1()
        assertEquals(249, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day08(readInput("Day08_test")).part2()
        assertEquals(34, result)
    }

    @Test
    fun part2_test2() {
        val result = Day08(readInput("Day08_test2")).part2()
        assertEquals(9, result)
    }

    @Test
    fun part2_real() {
        val result = Day08(readInput("Day08")).part2()
        assertEquals(905, result)
    }
}