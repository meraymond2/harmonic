package dao

import models.intervals.{HarmonicInterval, MelodicInterval}
import models.notes.Note
import models.notes.PitchLetters.wrap

/**
  * Created by michael on 29/12/16.
  */

object IntervalDb {

  val unison = HarmonicInterval("unison", 0, 0)
  val min2nd = HarmonicInterval("min2nd", 1, 1)
  val maj2nd = HarmonicInterval("maj2nd", 2, 1)
  val aug2nd = HarmonicInterval("aug2nd", 3, 1)
  val min3rd = HarmonicInterval("min3rd", 3, 2)
  val maj3rd = HarmonicInterval("maj3rd", 4, 2)
  val dim4th = HarmonicInterval("dim4th", 4, 3)
  val per4th = HarmonicInterval("per4th", 5, 3)
  val aug4th = HarmonicInterval("aug4th", 6, 3)
  val dim5th = HarmonicInterval("dim5th", 6, 4)
  val per5th = HarmonicInterval("per5th", 7, 4)
  val aug5th = HarmonicInterval("aug5th", 8, 4)
  val min6th = HarmonicInterval("min6th", 8, 5)
  val maj6th = HarmonicInterval("maj6th", 9, 5)
  val dim7th = HarmonicInterval("dim7th", 9, 6)
  val min7th = HarmonicInterval("min7th", 10, 6)
  val maj7th = HarmonicInterval("maj7th", 11, 6)
  val octave = HarmonicInterval("octave", 12, 0)
  val unknownInterval = HarmonicInterval("Unknown Interval", 99, 99)

  val intervals: Set[HarmonicInterval] = Set(
    unison, min2nd, maj2nd, aug2nd, min3rd, maj3rd, dim4th, per4th, aug4th,
    dim5th, per5th, aug5th, min6th, maj6th, dim7th, min7th, maj7th, octave,
    unknownInterval
  )

  private def simpleInt(absPitchDiff: Int): Int = absPitchDiff match {
    case n if n > 12 => simpleInt(n - 12)
    case n           => n
  }

  /***
    * Returns the harmonic interval between two notes. It also reduces the interval down to a simple
    * interval, ignoring compound intervals. I'm not sure when I would need to consider compound
    * intervals.
    * @param note1: The first note to compare.
    * @param note2 The second note to compare; it doesn't matter if it's higher or lower.
    * @return A static interval. It's a bit strange to return a placeholder unknown interval instead of an
    *         option, but it works for the chord-matching, and if it needs to be returned to a user, it's
    *         as good to display an unknown interval as it is to display no interval.
    */
  def harmonicInterval(note1: Note, note2: Note): HarmonicInterval = {
    val Seq(lower, upper) = Seq(note1, note2).sortBy(_.absPitch)
    intervals.find( interval =>
      (interval.letterDiff == wrap(upper.pitchLetter.id - lower.pitchLetter.id))
        &&
      (interval.pitchDiff == simpleInt(upper.absPitch - lower.absPitch))
    ).getOrElse(unknownInterval)
  }

  def melodicInterval(note1: Note, note2: Note): MelodicInterval = {
    val direction = if (note2.absPitch > note1.absPitch) "asc" else "desc"
    val interval = harmonicInterval(note1, note2)
    MelodicInterval(interval, direction)
  }

}
