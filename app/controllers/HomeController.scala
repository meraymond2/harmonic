package controllers

import com.google.inject.Singleton
import models.chords.ChordFinder
import models.intervals.{min10th, min6th, unison}
import models.notes._
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
    println(C4.diff(C4))
    println(C4.diff(Db4))
    println(A3.diff(C4))
    println(C4.diff(A3))
    println(A1.diff(A3))
    val note1 = A3
    val note2 = E4
    val note3 = A4
    val note4 = C5
    ChordFinder.findChord(note1, note2, note3, note4)
    Ok
  }

}
