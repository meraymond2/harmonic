package controllers

import dao.KeyDb
import models.chords.{Chord, ChordClasses}
import models.notes.PitchClasses
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

/**
  * Created by michael on 30/12/16.
  */

class KeyController extends Controller {

  def findKeys(root: String, chord: String) = Action.async {
    Try(Chord(PitchClasses.withName(root), ChordClasses.withName(chord))).toOption.fold(
      Future(NotFound("That chord wasn't recognised."))
    )(chord =>
      KeyDb.findKeys(chord).map(_.map(_.name)).map(keys => Ok(Json.toJson(keys)))
    )
  }

}