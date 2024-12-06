fun main() {
  val input = readInput("day_02/input")

  val reports = input.map { it.split(" ").map { it.trim().toInt() } }

  val checkedReportsPart1 = getCheckedReportsPart1(reports)

  val checkedReportsPart2 = part2(reports)

  println("Part1: " + checkedReportsPart1.count { it })
  println("Part2: " + checkedReportsPart2.count { it })
}

private fun getCheckedReportsPart1(reports: List<List<Int>>) = reports.map {
  checkReport(it)
}

private fun part2(reports: List<List<Int>>): List<Boolean> {
  val checkedReportsPart2 = reports.map { report ->

    if (checkReport(report).not()) {

      List(report.size) { index ->
        val subReport = report.toMutableList()
        subReport.removeAt(index)
        subReport
      }.any { subReport ->
        checkReport(subReport)
      }
      
    } else true

  }
  return checkedReportsPart2
}

fun checkReport(report: List<Int>): Boolean {
  val differences = List(report.size) { index ->
    if (index != 0) {
      report[index] - report[index - 1]
    } else null
  }.filterNotNull()

  return checkDifferences(differences)
}

fun checkDifferences(differences: List<Int>): Boolean {
  if (differences.all { it in -3..-1 }) return true
  if (differences.all { it in 1..3 }) return true

  return false
}