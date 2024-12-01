import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day01Test {
    @Test
    fun part1_test() {
        val result = Day01(readInput("Day01_test")).part1()
        assertEquals(11, result)
    }

    @Test
    fun part1_real() {
        val result = Day01(readInput("Day01")).part1()
        assertEquals(2031679, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day01(readInput("Day01_test")).part2()
        assertEquals(31, result)
    }

    @Test
    fun part2_real() {
        val result = Day01(readInput("Day01")).part2()
        assertEquals(19678534, result)
    }
}