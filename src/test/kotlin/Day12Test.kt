import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertTrue

class Day12Test {
    @Test
    fun part1_test() {
        val result = Day12(readInput("Day12_test")).part1()
        assertEquals(1930, result)
    }

    @Test
    fun part1_real() {
        val result = Day12(readInput("Day12")).part1()
        assertEquals(1446042, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day12(readInput("Day12_test")).part2()
        assertEquals(1206, result)
    }

    @Test
    fun part2_test2() {
        val result = Day12(readInput("Day12_test2")).part2()
        assertEquals(80, result)
    }

    @Test
    fun part2_test4() {
        val result = Day12(readInput("Day12_test4")).part2()
        assertEquals(236, result)
    }

    @Test
    fun part2_test5() {
        val result = Day12(readInput("Day12_test5")).part2()
        assertEquals(368, result)
    }

    @Test
    fun part2_real() {
        val result = Day12(readInput("Day12")).part2()
        assertEquals(902742, result)
    }
}