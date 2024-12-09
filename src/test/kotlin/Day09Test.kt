import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day09Test {
    @Test
    fun part1_test() {
        val result = Day09(readInputText("Day09_test")).part1()
        assertEquals(1928, result)
    }

    @Test
    fun part1_real() {
        val result = Day09(readInputText("Day09")).part1()
        assertEquals(6307275788409, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day09(readInputText("Day09_test")).part2()
        assertEquals(2858, result)
    }

    @Test
    fun part2_real() {
        val result = Day09(readInputText("Day09")).part2()
        assertEquals(6327174563252, result)
    }
}