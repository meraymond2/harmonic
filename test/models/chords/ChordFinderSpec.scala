package models.chords

import dao.NoteDb._
import models.chords.ChordClasses._
import models.notes.Note
import models.notes.PitchClasses._
import play.api.test.Helpers._
import org.specs2.mutable.Specification

import scala.concurrent.{Await, Future}

/**
  * Created by michael on 28/12/16.
  */

class ChordFinderSpec extends Specification {

  private def waitFor[T](future: Future[T]): T = Await.result(future, defaultAwaitTimeout.duration)

  "findChord" should {

    "find minor triads" in {
      val chord = waitFor(ChordFinder.findChord(A3, C4, E4, A4))
      chord must beSome( Chord(A, minorTriad) )

      val chord2 = waitFor(ChordFinder.findChord(D3, F3, A3, D4))
      chord2 must beSome( Chord(D, minorTriad))
    }

    "find major triads" in {
      val chord1 = waitFor(ChordFinder.findChord(E3, Gs4, E4, B4))
      chord1 must beSome (Chord(E, majorTriad))

      val chord2 = waitFor(ChordFinder.findChord(F3, A4, C5, A5))
      chord2 must beSome (Chord(F, majorTriad))
    }

  }

}
