package controllers

import com.google.inject.Singleton
import models.chords.ChordFinder
import models.notes.NotesDao._
import play.api.mvc.{Action, Controller}

/**
  * Created by michael on 17/12/16.
  */

@Singleton
class HomeController extends Controller {

  def index = Action {
    Ok
  }

  def test = Action {
    val note1 = A3
    val note2 = E4
    val note3 = E4
    val note4 = C5
    println(ChordFinder.findChord(note1, note2, note3, note4))
    val note5 = E3
    val note6 = G4
    val note7 = E4
    println( ChordFinder.findChord(note5, note6, note7))
    println(ChordFinder.findChord(Gs4))
    Ok
  }

}
