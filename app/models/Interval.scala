package models

/**
  * Created by michael on 17/12/16.
  */

case class Interval private (fullName: String, shortName: String, size: Int)

object Interval {

  val unison: Interval = Interval("Perfect Unison", "P1", 0)
  val minSecond: Interval = Interval("Minor Second", "m2", 1)
  val majSecond: Interval = Interval("Major Second", "M2", 2)
  val minThird: Interval = Interval("Minor Third", "m3", 3)
  val majThird: Interval = Interval("Major Third", "M3", 4)
  val perFourth: Interval = Interval("Perfect Fourth", "P4", 5)
  val augFourth: Interval = Interval("Augmented Fourth", "A4", 6)
  val dimFifth: Interval = Interval("Diminished Fifth", "d5", 6)
  val perFifth: Interval = Interval("Perfect Fifth", "P5", 7)
  val minSixth: Interval = Interval("Minor Sixth", "m6", 8)
  val majSixth: Interval = Interval("Major Sixth", "M6", 9)
  val minSeventh: Interval = Interval("Minor Seventh", "m7", 10)
  val majSeventh: Interval = Interval("Major Seventh", "M7", 11)
  val octave: Interval = Interval("Perfect Octave", "P8", 12)

}
