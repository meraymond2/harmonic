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

  /***
    * Used for adding and subtracting letters when working out intervals. The difference
    * between A up to E is E.id - A.id = 4, or a fifth, which is fine. But the difference
    * between E up to A is -4. This compensates for wrapping as the letters repeat. So
    * that -4 becomes 3, or a fourth, which is correct. Likewise a third (2) up from G shouldn't
    * be 9 (which doesn't match any letter), but 9 - 7, or 2 (B).
    * Finally: it's recursive, in case it's more than 7 off.
    * @param n Any int, the result of an arithmetic operation involving letters.
    * @return That number +/- 7 until it's between 1 & 7.
    */
  def wrap(n: Int): Int = n match {
    case int if int < 0 => wrap(int + 7)
    case int if int > 7 => wrap(int - 7)
    case int            => int
  }

}
