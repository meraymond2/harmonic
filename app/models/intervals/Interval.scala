package models.intervals

/**
  * Created by michael on 19/12/16.
  */

/***
  * val pitchDiff: The number of half-steps in the interval.
  * val letterDiff: The difference between the letters' indices. Note that this
  * number is one less than the common name. So C-A = 3 - 1 = 2 = a third.
  * val simpleInt: The corresponding simple interval for a compound interval. For
  * example, a minor tenth corresponds to a minor third (when figuring chords).
  *
  * The instances need to be objects so that they can reference themselves.
  * That doesn't work with instances of case classes.
  */

trait Interval {
  val pitchDiff: Int
  val letterDiff: Int
  val simpleInt: Interval
}