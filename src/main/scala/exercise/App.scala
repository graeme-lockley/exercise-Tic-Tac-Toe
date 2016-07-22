package exercise

import scala.collection.mutable

object App {
	def main(args: Array[String]): Unit = {
		val positionsList = List("1", "2", "3", "4", "5", "6", "7", "8", "9")
		val ticTacToe = new TicTacToe
		ticTacToe.printTic()
		println("This is just a positive scenario")
		var set = mutable.Set[String]()
		for (validEntryCount <- 0 until 9) {
			if (validEntryCount % 2 == 0) {
				println("This is X's turn. Please enter the position-digit")
				var entry: String = scala.io.StdIn.readLine()
				def validateEntryForX(): Unit = {
					var rightEntry = true
					while (rightEntry) {
						if (positionsList.contains(entry)) {
							rightEntry = false
							while (set.contains(entry)) {
								println("Please re-enter a digit.This is already present")
								entry = scala.io.StdIn.readLine()
								while (entry == null || entry.isEmpty) {
									println("Please do not hit enter without pressing any key")
									entry = scala.io.StdIn.readLine()
								}
							}
							while (!positionsList.contains(entry)) {
								println("Please re-enter a digit")
								entry = scala.io.StdIn.readLine()
							}
							set += entry
						} else {
							println("Please re-enter a digit")
							entry = scala.io.StdIn.readLine()
						}
					}
				}
				validateEntryForX()
				ticTacToe.markPosition(entry.toInt, "X")
			} else {
				println("This is O's turn. Please enter the position")
				var entry: String = scala.io.StdIn.readLine()
				def validateEntryForO(): Unit = {
					var rightEntry = true
					while (rightEntry) {
						if (positionsList.contains(entry)) {
							rightEntry = false
							while (set.contains(entry)) {
								println("Please re-enter a digit.This is already present")
								entry = scala.io.StdIn.readLine()
								while (entry == null || entry.isEmpty) {
									println("Please do not hit enter without pressing any key")
									entry = scala.io.StdIn.readLine()
								}
							}
							while (!positionsList.contains(entry)) {
								println("Please re-enter a digit")
								entry = scala.io.StdIn.readLine()
							}
							set += entry
						} else {
							println("Please re-enter a digit")
							entry = scala.io.StdIn.readLine()
						}
					}
				}
				validateEntryForO()
				ticTacToe.markPosition(entry.toInt, "O")
			}
			ticTacToe.printTic()
		}
		println("Match draw")
	}
}
