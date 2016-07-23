package exercise

object App {
	def main(args: Array[String]): Unit = {
		val ticTacToe = new TicTacToe()

		ticTacToe.printTicTacToeBoard()
		println("This is just a positive scenario")
		val turns = BoardValue.values.toList
		for (validEntryCount <- 0 until 9) {
			val turn = turns(validEntryCount % 2)
			val entry = captureEntry(ticTacToe, turn)
			ticTacToe.markPosition(entry, turn)
			ticTacToe.printTicTacToeBoard()
			ticTacToe.status()
		}
		println("Match draw")
	}

	private def captureEntry(ticTacToe: TicTacToe, symbol: BoardValue.Value): Position.Value = {
		def capturePosition(): Position.Value = {
			val entry: String = scala.io.StdIn.readLine()
			Position.fromString(entry) match {
				case Left(_) =>
					println("Please re-enter a digit")
					capturePosition()
				case Right(position) =>
					if (ticTacToe.isPositionTaken(position)) {
						println("Please re-enter a digit. This is already present")
						capturePosition()
					} else {
						position
					}
			}
		}

		println(s"This is $symbol's turn. Please enter the position-digit")
		capturePosition()
	}
}
