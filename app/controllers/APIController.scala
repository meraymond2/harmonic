package controllers

import dao.KeyDb
import models.chords.{Chord, ChordClasses, ChordFinder}
import models.notes.{Note, PitchClasses}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.Try

/**
  * Created by michael on 30/12/16.
  */

class APIController extends Controller {

  def findKeys(root: String, chord: String) = Action.async {
    Try(Chord(PitchClasses.withName(root), ChordClasses.withName(chord))).toOption.fold(
      Future(NotFound("That chord wasn't recognised."))
    )(chord =>
      KeyDb.findKeys(chord).map(_.map(_.name)).map(keys => Ok(Json.toJson(keys)))
    )
  }

  def findChord = Action.async { implicit req =>
    req.body.asJson.fold(Future(BadRequest(Json.obj())))(json =>
      json.asOpt[Seq[Note]].fold(Future(BadRequest(Json.obj())))(notes =>
        ChordFinder.findChord(notes:_*).flatMap(_.fold(Future(BadRequest(Json.obj("msg" -> "No Chord"))))(chord => {
          KeyDb.findKeys(chord).map(keys =>
            Ok(Json.obj(
              "chord" -> chord,
              "keys" -> Json.toJson(keys.map(key => key.name))
            ))
          )
        }))
      )
    )
  }

}