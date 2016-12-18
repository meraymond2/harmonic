package controllers

import com.google.inject.Singleton
import models.{Chord, ChordNames, PitchClasses}
import models.Tone._
import models.Interval._
import play.api.mvc.{Action, Controller}

/**
  * Created by michael on 17/12/16.
  */

@Singleton
class HomeController extends Controller {

  def index = Action {
    val c = Chord(A0, C1, E1, A1)
    val blah = c.classify
    println(blah)
    Ok
  }

}
