package exercise

import scala.Option.empty
import scala.collection.mutable

class TicTacToe {
	private val board = new Board()

	def markPosition(position: Position.Value, symbol: BoardValue.Value): Unit = board.markPosition(position, symbol)

	def isPositionTaken(position: Position.Value): Boolean = board.isPositionTaken(position)

	def get(position: Position.Value): Option[BoardValue.Value] = board.get(position)

	def status(): TicTacToeStatus = {
		val strikes = Array(
			Array(1, 2, 3), Array(4, 5, 6), Array(7, 8, 9),
			Array(1, 4, 7), Array(2, 5, 8), Array(3, 6, 9),
			Array(1, 5, 9), Array(3, 5, 7))

		var result: Option[TicTacToeStatus] = empty

		for (c <- strikes.indices if result.isEmpty) {
			val count = mutable.HashMap(BoardValue.X -> 0, BoardValue.O -> 0)
			val eachStrike = strikes(c)
			for (b <- eachStrike.indices if result.isEmpty) {
				val strike = Position(eachStrike(b) - 1)

				def cellMarked(cellValue: BoardValue.Value): Unit = {
					count(cellValue) = count(cellValue) + 1
					if (count(cellValue) == 3) {
						result = Option(GameWon(cellValue))
					}
				}

				board.get(strike).foreach(cellMarked(_))
			}
		}

		result.getOrElse(if (board.isFull) GameTie() else InProgress())
	}
}