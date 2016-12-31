package models.intervals

/**
  * Created by michael on 19/12/16.
  */

/***
  * val pitchDiff: The number of half-steps in the interval.
  * val letterDiff: The difference between the letters' indices. Note that this
  * number is one less than the common name. So C-A = 3 - 1 = 2 = a third.
  *
  */

case class HarmonicInterval(name: String, pitchDiff: Int, letterDiff: Int)