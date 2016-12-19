package models.intervals

/**
  * Created by michael on 19/12/16.
  */

/***
  * pitchDiff: The number of half-steps in the interval.
  * letterDiff: The difference between the letters' indices. Note that this
  * number is one less than the common name. So C-A = 3 - 1 = 2 = a third.
  * simpleInt: The corresponding simple interval for a compound interval. For
  * example, a minor tenth corresponds to a minor third (when figuring chords).
  *
  * The instances are not an exhaustive list, for now they're just the common ones.
  */

trait Interval {
  val pitchDiff: Int
  val letterDiff: Int
  val simpleInt: Interval
}

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
  override val letterDiff: Int = 7
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
  override val letterDiff: Int = 7
  override val simpleInt: Interval = octave
}