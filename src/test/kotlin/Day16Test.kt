
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day16Test {
    @Test
    fun part1_test() {
        val result = Day16(readInput("Day16_test")).part1()
        assertEquals(7036, result)
    }

    @Test
    fun part1_test2() {
        val result = Day16(readInput("Day16_test2")).part1()
        assertEquals(11048, result)
    }

    @Test
    fun part1_real() {
        val result = Day16(readInput("Day16")).part1()
        assertEquals(95476, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day16(readInput("Day16_test")).part2()
        assertEquals(45, result)
    }

    @Test
    fun part2_test2() {
        val result = Day16(readInput("Day16_test2")).part2()
        assertEquals(64, result)
    }

    @Test
    fun part2_real() {
        val result = Day16(readInput("Day16")).part2()
        assertEquals(511, result)
    }
}