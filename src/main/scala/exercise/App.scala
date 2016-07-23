package exercise

object App {
	def main(args: Array[String]): Unit = {
		val ticTacToe = new TicTacToe()

		ticTacToe.printTic()
		println("This is just a positive scenario")
		for (validEntryCount <- 0 until 9) {
			val turn = BoardValue.values.toList(validEntryCount % 2)
			val entry = captureEntry(ticTacToe, turn)
			ticTacToe.markPosition(entry, turn)
			ticTacToe.printTic()
		}
		println("Match draw")
	}

	private def captureEntry(ticTacToe: TicTacToe, symbol: BoardValue.Value): Position.Value = {
		def capturePosition(): Position.Value = {
			val entry: String = scala.io.StdIn.readLine()
			Position.fromString(entry) match {
				case Left(_) => println("Please re-enter a digit")
					capturePosition()
				case Right(v) =>
					if (ticTacToe.isPositionTaken(v)) {
						println("Please re-enter a digit.This is already present")
						capturePosition()
					} else {
						v
					}
			}
		}

		println(s"This is $symbol's turn. Please enter the position-digit")
		capturePosition()
	}
}
