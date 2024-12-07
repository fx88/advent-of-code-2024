package day_03

import readInput

val mulPattern = "(mul\\([0-9]{1,3},[0-9]{1,3}\\))"
val doPattern = "(do\\(\\))"
val dontPattern = "(don\\'t\\(\\))"

fun main() {

  val input = readInput("day_03/input").joinToString()

  val sumPart1 = part1(input)
  val sumPart2 = part2(input)

  println("Sum of Part1: $sumPart1")
  println("Sum of Part2: $sumPart2")
}

fun part1(input: String): Int {

  val pattern = mulPattern.toRegex()
  val results = pattern.findAll(input).toList().map { it.value }

  return results.sumOf {
    mul(it)
  }
}

fun part2(input: String): Int {
  val pattern = "$doPattern|$dontPattern|$mulPattern".toRegex()

  val results = pattern.findAll(input).toList().map { it.value }
  var sum = true

  val bar = results
    .mapNotNull {
      if (it == "do()") {
        sum = true
        return@mapNotNull null
      }
      if (it == "don't()") {
        sum = false
        return@mapNotNull null
      }
      if (sum) it else null
    }

  return bar.sumOf { mul(it) }
}

private fun mul(it: String) =
  it.substringAfter("(")
    .substringBefore(")")
    .split(",")
    .map { it.toInt() }
    .let { it[0] * it[1] }
