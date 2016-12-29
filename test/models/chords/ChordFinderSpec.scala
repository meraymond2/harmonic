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

    "return a minor triad" in {
      val notes: Seq[Note] = Seq(A3, C4, E4, A4)
      val chord = waitFor( ChordFinder.findChord(notes:_*) )
      chord must beSome(Chord(A, minorTriad))
    }

    "return a minor triad" in {
      val notes: Seq[Note] = Seq(E2, E3, G3, B3)
      val chord = waitFor( ChordFinder.findChord(notes:_*) )
      chord must beSome(Chord(E, minorTriad))
    }

    "return a minor triad if the fifth is doubled" in {
      val notes: Seq[Note] = Seq(D3, A3, F4, A4)
      val chord = waitFor( ChordFinder.findChord(notes:_*) )
      chord must beSome(Chord(D, minorTriad))
    }

    "return a minor triad if the fifth is missing" in {
      val notes: Seq[Note] = Seq(A3, C4, A4)
      val chord = waitFor( ChordFinder.findChord(notes:_*) )
      chord must beSome(Chord(A, minorTriad))
    }

  }

}
