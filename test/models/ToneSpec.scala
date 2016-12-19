package models

import oldmodels.Interval._
import oldmodels.Tone._
import org.specs2.mutable.Specification

/**
  * Created by michael on 18/12/16.
  */

class ToneSpec extends Specification {

  "up" should {
    "return the tone the specified interval above" in {
      A3.up(octave) must_=== Some(intervals.A4)
      A3.up(unison) must_=== Some(A3)
      A3.up(majSecond) must_=== Some(B3)
      A3.up(minThird) must_=== Some(C4)
      A3.up(perFourth) must_=== Some(D4)
      A3.up(perFifth) must_=== Some(E4)
      A3.up(minSixth) must_=== Some(F4)
      A3.up(majSeventh) must_=== Some(Gs4)
    }
  }

}
