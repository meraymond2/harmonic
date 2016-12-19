package models

import oldmodels.Interval._
import oldmodels.Interval.findInterval
import oldmodels.Tone._
import org.specs2.mutable.Specification

/**
  * Created by michael on 18/12/16.
  */

class IntervalSpec extends Specification {

  "findInterval" should {

    "return a unison given the same note" in {
      val interval = findInterval(C4, C4)

      interval must_=== Some(unison)
    }

    "return an augmented unison between C4 and C#4" in {
      val interval = findInterval(C4, Cs4)

      interval must_=== Some(augUnison)
    }

    "return an augmented unison between D4 and Db4" in {
      val interval = findInterval(D4, Db4)

      interval must_=== Some(augUnison)
    }

    "return a major third between E and G#" in {
      val interval = findInterval(E4, Gs4)

      interval must_=== Some(majThird)
    }

    "return a minor third between A and C" in {
      val interval = findInterval(A1, C2)

      interval must_=== Some(minThird)
    }

    "return a minor third between A and C" in {
      val interval = findInterval(C2, A1)

      interval must_=== Some(minThird)
    }

    "return a P5 between A4 and E5" in {
      val interval = findInterval(A4, E5)

      interval must_=== Some(perFifth)
    }

    "return a P4 between A4 and E4" in {
      val interval = findInterval(A4, E4)

      interval must_=== Some(perFourth)
    }

    "return an octave between A3 and A4" in {
      val interval = findInterval(A3, A4)

      interval must_=== Some(octave)
    }

  }

}
