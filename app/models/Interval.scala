package models

/**
  * Created by michael on 17/12/16.
  */

case class Interval private (fullName: String, shortName: String, halfSteps: Int, letterGap: Int)

object Interval {

  def findInterval(first: Tone, second: Tone): Option[Interval] = {
    val Seq(lower, upper) = Seq(first, second).sortBy(_.pitch)
    intervals.find( interval =>
      (interval.letterGap == upper.letter.above(lower.letter))
        &&
      (interval.halfSteps == upper.pitch - lower.pitch)
    )
  }

  // Need to be careful with the unusual ones, augmented seven above A0 is a G##, which I don't have in the list.

  val unison:     Interval = Interval("Perfect Unison",     "P1", 0,  0)
  val augUnison:  Interval = Interval("Augmented Unison",   "A1", 1,  0)
  val dimSecond:  Interval = Interval("Diminished Second",  "d2", 0,  1)
  val minSecond:  Interval = Interval("Minor Second",       "m2", 1,  1)
  val majSecond:  Interval = Interval("Major Second",       "M2", 2,  1)
  val augSecond:  Interval = Interval("Augmented Second",   "A2", 2,  1)
  val dimThird:   Interval = Interval("Diminished Third",   "d3", 2,  2)
  val minThird:   Interval = Interval("Minor Third",        "m3", 3,  2)
  val majThird:   Interval = Interval("Major Third",        "M3", 4,  2)
  val augThird:   Interval = Interval("Augmented Third",    "A3", 5,  2)
  val dimFourth:  Interval = Interval("Diminished Fourth",  "d4", 4,  3)
  val perFourth:  Interval = Interval("Perfect Fourth",     "P4", 5,  3)
  val augFourth:  Interval = Interval("Augmented Fourth",   "A4", 6,  3)
  val dimFifth:   Interval = Interval("Diminished Fifth",   "d5", 6,  4)
  val perFifth:   Interval = Interval("Perfect Fifth",      "P5", 7,  4)
  val augFifth:   Interval = Interval("Augmented Fifth",    "P5", 8,  4)
  val dimSixth:   Interval = Interval("Diminished Sixth",   "d6", 7,  5)
  val minSixth:   Interval = Interval("Minor Sixth",        "m6", 8,  5)
  val majSixth:   Interval = Interval("Major Sixth",        "M6", 9,  5)
  val augSixth:   Interval = Interval("Augmented Sixth",    "A6", 10, 5)
  val dimSeventh: Interval = Interval("Diminished Seventh", "d7", 9,  6)
  val minSeventh: Interval = Interval("Minor Seventh",      "m7", 10, 6)
  val majSeventh: Interval = Interval("Major Seventh",      "M7", 11, 6)
  val augSeventh: Interval = Interval("Augmented Seventh",  "A7", 12, 6)
  val dimOctave:  Interval = Interval("Diminished Octave",  "d8", 11, 0)
  val octave:     Interval = Interval("Perfect Octave",     "P8", 12, 0)

  val intervals: Seq[Interval] = Seq[Interval](unison, augUnison, dimSecond, minSecond, majSecond, augSecond, dimThird, minThird, majThird, augThird, dimFourth, perFourth, augFourth, dimFifth, perFifth, augFifth, dimSixth, minSixth, majSixth, augSixth, dimSeventh, minSeventh, majSeventh, augSeventh, dimOctave, octave)

}
