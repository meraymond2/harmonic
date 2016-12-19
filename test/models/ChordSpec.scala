package models

import oldmodels.ChordNames._
import oldmodels.PitchClasses.A
import oldmodels.Tone._
import org.specs2.mutable.Specification

/**
  * Created by michael on 18/12/16.
  */
class ChordSpec extends Specification {

  "classify" should {

    "return a chord type if known for a given combination of notes" in {
      Chord(A0, C1, E1, A1).classify must_=== Some((minorTriad, Some(A)))
    }

  }

}
