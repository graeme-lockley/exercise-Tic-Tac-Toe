package exercise

object Position extends Enumeration {
	val One, Two, Three, Four, Five, Six, Seven, Eight, Nine = Value

	def fromString(value: String): Either[String, Value] = {
		try {
			val index = value.toInt - 1

			if (index < 0) {
				Left("Underflow")
			} else if (index >= maxId) {
				Left("Overflow")
			} else {
				Right(this (value.toInt - 1))
			}
		} catch {
			case _: NumberFormatException => Left("Not a number")
		}
	}
}
