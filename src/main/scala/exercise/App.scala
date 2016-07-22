package exercise

object App {
	val positionsList = List("1", "2", "3", "4", "5", "6", "7", "8", "9")

	def main(args: Array[String]): Unit = {
		val ticTacToe = new TicTacToe
		ticTacToe.printTic()
		println("This is just a positive scenario")
		for (validEntryCount <- 0 until 9) {
			val turn = List("X", "O")(validEntryCount % 2)
			val entry = captureEntry(ticTacToe, turn)
			ticTacToe.markPosition(entry.toInt, turn)
			ticTacToe.printTic()
		}
		println("Match draw")
	}

	private def captureEntry(ticTacToe: TicTacToe, symbol: String): String = {
		println(s"This is $symbol's turn. Please enter the position-digit")
		var entry: String = scala.io.StdIn.readLine()
		var rightEntry = true
		while (rightEntry) {
			if (positionsList.contains(entry)) {
				rightEntry = false
				while (ticTacToe.isPositionTaken(entry)) {
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
			} else {
				println("Please re-enter a digit")
				entry = scala.io.StdIn.readLine()
			}
		}
		entry
	}
}
