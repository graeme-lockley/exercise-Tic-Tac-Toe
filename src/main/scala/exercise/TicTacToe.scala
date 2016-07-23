package exercise

import scala.Option.empty

class TicTacToe {
	private val strikes = Array(
		Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9),
		Array(1, 4, 7), Array(2, 5, 8), Array(3, 6, 9),
		Array(1, 5, 9), Array(3, 5, 7))

	private val board = new Board()

	def markPosition(position: Position.Value, symbol: BoardValue.Value): Unit = board.markPosition(position, symbol)

	def isPositionTaken(position: Position.Value): Boolean = board.isPositionTaken(position)

	def status(): TicTacToeStatus = {
		var result: Option[TicTacToeStatus] = empty

		for (c <- strikes.indices if result.isEmpty) {
			var xCount = 0
			var oCount = 0
			val eachStrike = strikes(c)
			def checkIfXWins(): Unit = {
				xCount += 1
				if (xCount == 3) {
					result = Option(GameWon(BoardValue.X))
				}
			}
			def checkIfOWins(): Unit = {
				oCount += 1
				if (oCount == 3) {
					result = Option(GameWon(BoardValue.O))
				}
			}
			for (b <- eachStrike.indices if result.isEmpty) {
				val strike = Position(eachStrike(b) - 1)
				if (board.get(strike).contains(BoardValue.X)) {
					checkIfXWins()
				} else if (board.get(strike).contains(BoardValue.O)) {
					checkIfOWins()
				}
			}
		}

		result.getOrElse(if (board.isFull) GameTie() else InProgress())
	}

	def printTicTacToeBoard(): Unit = {
		for (a <- Position.values) {
			print(board.get(a).getOrElse(a.id + 1))
			print("     ")
			if ((a.id + 1) % 3 == 0) {
				println()
				println()
			}
		}
	}
}

class TicTacToeStatus

case class GameWon(winner: BoardValue.Value) extends TicTacToeStatus

case class GameTie() extends TicTacToeStatus

case class InProgress() extends TicTacToeStatus

