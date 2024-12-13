
import util.readInputText
import kotlin.test.Test
import kotlin.test.assertEquals

class Day13Test {
    @Test
    fun part1_test() {
        val result = Day13(readInputText("Day13_test")).part1()
        assertEquals(480, result)

    }

    @Test
    fun part1_real() {
        val result = Day13(readInputText("Day13")).part1()
        assertEquals(29877, result)
    }

    @Test
    fun part2_real() {
        val result = Day13(readInputText("Day13")).part2()
        assertEquals(99423413811305, result)
    }
}