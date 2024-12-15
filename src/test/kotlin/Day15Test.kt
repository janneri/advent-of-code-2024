
import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day15Test {
    @Test
    fun part1_test_small() {
        val result = Day15(readInputText("Day15_test_small")).part1()
        assertEquals(2028, result)
    }

    @Test
    fun part1_test() {
        val result = Day15(readInputText("Day15_test")).part1()
        assertEquals(10092, result)
    }

    @Test
    fun part1_real() {
        val result = Day15(readInputText("Day15")).part1()
        assertEquals(1475249, result)
    }
    
    @Test
    fun part2_test_small() {
        val result = Day15(readInputText("Day15_test_part2")).part2()
        assertEquals(618, result)
    }

    @Test
    fun part2_test() {
        val result = Day15(readInputText("Day15_test")).part2()
        assertEquals(9021, result)
    }

    @Test
    fun part2_real() {
        val result = Day15(readInputText("Day15")).part2()
        assertEquals(1509724, result)
    }
}