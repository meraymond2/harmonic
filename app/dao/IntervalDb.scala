package dao

import models.intervals.{Interval, MelodicInterval}
import models.notes.Note
import models.notes.PitchLetters.wrap

/**
  * Created by michael on 29/12/16.
  *
  * The instances need to be objects so that they can reference themselves, which
  * doesn't work with instances of case classes.
  *
  */

object IntervalDb {

  object unison extends Interval {
    override val pitchDiff: Int = 0
    override val letterDiff: Int = 0
    override val simpleInt: Interval = unison
  }

  object min2nd extends Interval {
    override val pitchDiff: Int = 1
    override val letterDiff: Int = 1
    override val simpleInt: Interval = min2nd
  }

  object maj2nd extends Interval {
    override val pitchDiff: Int = 2
    override val letterDiff: Int = 1
    override val simpleInt: Interval = maj2nd
  }

  object aug2nd extends Interval {
    override val pitchDiff: Int = 3
    override val letterDiff: Int = 1
    override val simpleInt: Interval = aug2nd
  }

  object min3rd extends Interval {
    override val pitchDiff: Int = 3
    override val letterDiff: Int = 2
    override val simpleInt: Interval = min3rd
  }

  object maj3rd extends Interval {
    override val pitchDiff: Int = 4
    override val letterDiff: Int = 2
    override val simpleInt: Interval = maj3rd
  }

  object dim4th extends Interval {
    override val pitchDiff: Int = 4
    override val letterDiff: Int = 3
    override val simpleInt: Interval = dim4th
  }

  object per4th extends Interval {
    override val pitchDiff: Int = 5
    override val letterDiff: Int = 3
    override val simpleInt: Interval = per4th
  }

  object aug4th extends Interval {
    override val pitchDiff: Int = 6
    override val letterDiff: Int = 3
    override val simpleInt: Interval = aug4th
  }

  object dim5th extends Interval {
    override val pitchDiff: Int = 6
    override val letterDiff: Int = 4
    override val simpleInt: Interval = dim5th
  }

  object per5th extends Interval {
    override val pitchDiff: Int = 7
    override val letterDiff: Int = 4
    override val simpleInt: Interval = per5th
  }

  object aug5th extends Interval {
    override val pitchDiff: Int = 8
    override val letterDiff: Int = 4
    override val simpleInt: Interval = aug5th
  }

  object min6th extends Interval {
    override val pitchDiff: Int = 8
    override val letterDiff: Int = 5
    override val simpleInt: Interval = min6th
  }

  object maj6th extends Interval {
    override val pitchDiff: Int = 9
    override val letterDiff: Int = 5
    override val simpleInt: Interval = maj6th
  }

  object dim7th extends Interval {
    override val pitchDiff: Int = 9
    override val letterDiff: Int = 6
    override val simpleInt: Interval = dim7th
  }

  object min7th extends Interval {
    override val pitchDiff: Int = 10
    override val letterDiff: Int = 6
    override val simpleInt: Interval = min7th
  }

  object maj7th extends Interval {
    override val pitchDiff: Int = 11
    override val letterDiff: Int = 6
    override val simpleInt: Interval = maj7th
  }

  object octave extends Interval {
    override val pitchDiff: Int = 12
    override val letterDiff: Int = 0
    override val simpleInt: Interval = octave
  }

  object min9th extends Interval {
    override val pitchDiff: Int = 13
    override val letterDiff: Int = 1
    override val simpleInt: Interval = min2nd
  }

  object maj9th extends Interval {
    override val pitchDiff: Int = 14
    override val letterDiff: Int = 1
    override val simpleInt: Interval = maj2nd
  }

  object aug9th extends Interval {
    override val pitchDiff: Int = 15
    override val letterDiff: Int = 1
    override val simpleInt: Interval = aug2nd
  }

  object min10th extends Interval {
    override val pitchDiff: Int = 15
    override val letterDiff: Int = 2
    override val simpleInt: Interval = min3rd
  }

  object maj10th extends Interval {
    override val pitchDiff: Int = 16
    override val letterDiff: Int = 2
    override val simpleInt: Interval = maj3rd
  }

  object dim11th extends Interval {
    override val pitchDiff: Int = 16
    override val letterDiff: Int = 3
    override val simpleInt: Interval = dim4th
  }

  object per11th extends Interval {
    override val pitchDiff: Int = 17
    override val letterDiff: Int = 3
    override val simpleInt: Interval = per4th
  }

  object aug11th extends Interval {
    override val pitchDiff: Int = 18
    override val letterDiff: Int = 3
    override val simpleInt: Interval = aug4th
  }

  object dim12th extends Interval {
    override val pitchDiff: Int = 18
    override val letterDiff: Int = 4
    override val simpleInt: Interval = dim5th
  }

  object per12th extends Interval {
    override val pitchDiff: Int = 19
    override val letterDiff: Int = 4
    override val simpleInt: Interval = per5th
  }

  object aug12th extends Interval {
    override val pitchDiff: Int = 20
    override val letterDiff: Int = 4
    override val simpleInt: Interval = aug5th
  }

  object min13th extends Interval {
    override val pitchDiff: Int = 20
    override val letterDiff: Int = 5
    override val simpleInt: Interval = min6th
  }

  object maj13th extends Interval {
    override val pitchDiff: Int = 21
    override val letterDiff: Int = 5
    override val simpleInt: Interval = maj6th
  }

  object dim14th extends Interval {
    override val pitchDiff: Int = 21
    override val letterDiff: Int = 6
    override val simpleInt: Interval = dim7th
  }

  object min14th extends Interval {
    override val pitchDiff: Int = 22
    override val letterDiff: Int = 6
    override val simpleInt: Interval = min7th
  }

  object maj14th extends Interval {
    override val pitchDiff: Int = 23
    override val letterDiff: Int = 6
    override val simpleInt: Interval = maj7th
  }

  object dblOctave extends Interval {
    override val pitchDiff: Int = 24
    override val letterDiff: Int = 0
    override val simpleInt: Interval = octave
  }

  object min16th extends Interval {
    override val pitchDiff: Int = 25
    override val letterDiff: Int = 1
    override val simpleInt: Interval = min2nd
  }

  object maj16th extends Interval {
    override val pitchDiff: Int = 26
    override val letterDiff: Int = 1
    override val simpleInt: Interval = maj2nd
  }

  object aug16th extends Interval {
    override val pitchDiff: Int = 27
    override val letterDiff: Int = 1
    override val simpleInt: Interval = aug2nd
  }

  object min17th extends Interval {
    override val pitchDiff: Int = 27
    override val letterDiff: Int = 2
    override val simpleInt: Interval = min3rd
  }

  object maj17th extends Interval {
    override val pitchDiff: Int = 28
    override val letterDiff: Int = 2
    override val simpleInt: Interval = maj3rd
  }

  object dim18th extends Interval {
    override val pitchDiff: Int = 28
    override val letterDiff: Int = 3
    override val simpleInt: Interval = dim4th
  }

  object per18th extends Interval {
    override val pitchDiff: Int = 29
    override val letterDiff: Int = 3
    override val simpleInt: Interval = per4th
  }

  object aug18th extends Interval {
    override val pitchDiff: Int = 30
    override val letterDiff: Int = 3
    override val simpleInt: Interval = aug4th
  }

  object dim19th extends Interval {
    override val pitchDiff: Int = 30
    override val letterDiff: Int = 4
    override val simpleInt: Interval = dim5th
  }

  object per19th extends Interval {
    override val pitchDiff: Int = 31
    override val letterDiff: Int = 4
    override val simpleInt: Interval = per5th
  }

  object aug19th extends Interval {
    override val pitchDiff: Int = 32
    override val letterDiff: Int = 4
    override val simpleInt: Interval = aug5th
  }

  object min20th extends Interval {
    override val pitchDiff: Int = 32
    override val letterDiff: Int = 5
    override val simpleInt: Interval = min6th
  }

  object maj20th extends Interval {
    override val pitchDiff: Int = 33
    override val letterDiff: Int = 5
    override val simpleInt: Interval = maj6th
  }

  object dim21th extends Interval {
    override val pitchDiff: Int = 33
    override val letterDiff: Int = 6
    override val simpleInt: Interval = dim7th
  }

  object min21th extends Interval {
    override val pitchDiff: Int = 34
    override val letterDiff: Int = 6
    override val simpleInt: Interval = min7th
  }

  object maj21th extends Interval {
    override val pitchDiff: Int = 35
    override val letterDiff: Int = 6
    override val simpleInt: Interval = maj7th
  }

  object trplOctave extends Interval {
    override val pitchDiff: Int = 36
    override val letterDiff: Int = 0
    override val simpleInt: Interval = octave
  }

  object unknownInterval extends Interval {
    override val pitchDiff: Int = 999
    override val letterDiff: Int = 999
    override val simpleInt: Interval = unknownInterval
  }

  val intervals: Set[Interval] = Set(
    unison, min2nd, maj2nd, aug2nd, min3rd, maj3rd, dim4th, per4th, aug4th,
    dim5th, per5th, aug5th, min6th, maj6th, dim7th, min7th, maj7th, octave,
    min9th, maj9th, aug9th, min10th, maj10th, dim11th, per11th, aug11th,
    dim12th, per12th, aug12th, min13th, maj13th, dim14th, min14th, maj14th, dblOctave,
    min16th, maj16th, aug16th, min17th, maj17th, dim18th, per18th, aug18th,
    dim19th, per19th, aug19th, min20th, maj20th, dim21th, min21th, maj21th, trplOctave
  )


  /***
    * Returns the harmonic interval between two notes (doesn't consider direction).
    * @param note1: The first note to compare.
    * @param note2 The second note to compare; it doesn't matter if it's higher or lower.
    * @return A static interval. It's a bit strange to return a placeholder unknown interval instead of an
    *         option, but it works for the chord-matching, and if it needs to be returned to a user, it's
    *         as good to display an unknown interval as it is to display no interval.
    */
  def harmonicInterval(note1: Note, note2: Note): Interval = {
    val Seq(lower, upper) = Seq(note1, note2).sortBy(_.absPitch)
    intervals.find( interval =>
      (interval.letterDiff == wrap(upper.pitchLetter.id - lower.pitchLetter.id))
        &&
      (interval.pitchDiff == upper.absPitch - lower.absPitch)
    ).getOrElse(unknownInterval)
  }

  def melodicInterval(note1: Note, note2: Note): MelodicInterval = {
    val direction = if (note2.absPitch > note1.absPitch) "asc" else "desc"
    val interval = harmonicInterval(note1, note2)
    MelodicInterval(interval, direction)
  }

}
