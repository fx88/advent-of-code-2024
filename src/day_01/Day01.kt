import kotlin.math.absoluteValue

fun main() {

  val input = readInput("day_01/input")

  val leftList = mutableListOf<Int>()
  val rightList = mutableListOf<Int>()

  input.forEach {
    it.substringBefore(" ").trim().let { leftList.add(it.toInt()) }
    it.substringAfter(" ").trim().let { rightList.add(it.toInt()) }
  }

  leftList.sort()
  rightList.sort()

  val totalDistance = leftList.mapIndexed { index, leftValue -> (rightList[index] - leftValue).absoluteValue }.sum()

  println("totalDistance: $totalDistance")

  val similarityScore = leftList.sumOf { leftValue -> rightList.count { it == leftValue } * leftValue }

  println("similarityScore: $similarityScore")
}
