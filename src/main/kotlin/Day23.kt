// See puzzle in https://adventofcode.com/2024/day/23

class Day23(inputLines: List<String>) {
    private val graph: Map<String, Set<String>> = inputLines
        .map { it.split("-").let { (a, b) -> a to b } }
        .fold(mutableMapOf<String, MutableSet<String>>()) { acc, (a, b) ->
            acc.getOrPut(a) { mutableSetOf() }.add(b)
            acc.getOrPut(b) { mutableSetOf() }.add(a)
            acc
        }

    fun part1(): Int {
        val triplets = mutableSetOf<List<String>>()

        for ((k, neighbors) in graph) {
            for (neighbor in neighbors) {
                val common = graph[k]!!.intersect(graph[neighbor]!!)
                common.forEach { triplets.add(listOf(k, neighbor, it).sorted()) }
            }
        }

        return triplets.count { triplet -> triplet.any { it.startsWith('t') } }

    }

    private fun findMaxClique(graph: Map<String, Set<String>>): Set<String> {
        var maxClique = setOf<String>()

        fun bronKerbosch(curr: Set<String>, candidates: Set<String>, seen: Set<String>) {
            if (candidates.isEmpty() && seen.isEmpty()) {
                if (curr.size > maxClique.size) maxClique = curr
                return
            }

            val pivot = (candidates + seen).first()

            val nonNeighbors = candidates - pivot.let { graph[it]!! }.toSet()
            for (v in nonNeighbors) {
                bronKerbosch(
                    curr + v,
                    candidates.intersect(graph[v]!!),
                    seen.intersect(graph[v]!!)
                )
            }
        }

        bronKerbosch(emptySet(), graph.keys, emptySet())
        return maxClique
    }


    fun part2(): String = findMaxClique(graph).sorted().joinToString(",")
}