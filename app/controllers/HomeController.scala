package controllers

import com.google.inject.Singleton
import models.notes.{A0, Ab1, Cs7}
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
    println(Ab1.toString)
    println(Ab1.absPitch)
    println(Ab1.pitchClass)
    println(Ab1.pitchLetter)
    println(Cs7.toString)
    Ok
  }

}
