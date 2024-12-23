
import util.readInput
import kotlin.test.Test
import kotlin.test.assertEquals

class Day23Test {
    @Test
    fun part1_test() {
        val result = Day23(readInput("Day23_test")).part1()
        assertEquals(7, result)
    }

    @Test
    fun part1_real() {
        val result = Day23(readInput("Day23")).part1()
        assertEquals(1467, result)
    }
    
    @Test
    fun part2_test() {
        val result = Day23(readInput("Day23_test")).part2()
        assertEquals("co,de,ka,ta", result)
    }

    @Test
    fun part2_real() {
        val result = Day23(readInput("Day23")).part2()
        assertEquals("di,gs,jw,kz,md,nc,qp,rp,sa,ss,uk,xk,yn", result)
    }
}