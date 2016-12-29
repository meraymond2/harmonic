package models.chords

import models.chords.ChordClasses._
import models.notes.Note
import models.notes.NotesDao._
import models.notes.PitchClasses._
import org.specs2.mutable.Specification

/**
  * Created by michael on 28/12/16.
  */

class ChordFinderSpec extends Specification {

  "findChord" should {

    "return a minor triad" in {
      val notes: Seq[Note] = Seq(A3, C4, E4, A4)
      val chord = ChordFinder.findChord(notes:_*)
      chord must beSome(Chord(A, minorTriad))
    }

    "return a minor triad" in {
      val notes: Seq[Note] = Seq(E2, E3, G3, B3)
      val chord = ChordFinder.findChord(notes:_*)
      chord must beSome(Chord(E, minorTriad))
    }

    "return a minor triad if the fifth is doubled" in {
      val notes: Seq[Note] = Seq(D3, A3, F4, A4)
      val chord = ChordFinder.findChord(notes:_*)
      chord must beSome(Chord(D, minorTriad))
    }

    "return a minor triad if the fifth is missing" in {
      val notes: Seq[Note] = Seq(A3, C4, A4)
      val chord = ChordFinder.findChord(notes:_*)
      chord must beSome(Chord(A, minorTriad))
    }

  }

}
