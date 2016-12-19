package models.notes

/**
  * Created by michael on 19/12/16.
  */

object PitchLetters extends Enumeration {
  type PitchLetter = PitchLetters.Value

  val A = Value(1, "A")
  val B = Value(2, "B")
  val C = Value(3, "C")
  val D = Value(4, "D")
  val E = Value(5, "E")
  val F = Value(6, "F")
  val G = Value(7, "G")

}
