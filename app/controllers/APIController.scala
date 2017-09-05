package controllers

import dao.KeyDb
import models.chords.{Chord, ChordClasses}
import models.notes.{Note, PitchClasses}
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}
import dao.NoteDb.{A3, A4, C4, E4}
import models.chords.ChordFinder

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

  def findChord = Action { implicit req =>
    req.body.asJson.fold(BadRequest(Json.obj()))(json => {
      println(json.as[Seq[Note]])
      Ok(json)
    })
//    val js = Json.obj(
//      "spn" -> "A3",
//      "absPitch" -> 37
//    )
//    val ok = js.as[Note]
//    val bass = A3
//    val tenor = E4
//    val alto = C4
//    val soprano = A4
//    println(ok)
//    ChordFinder.findChord(bass, tenor, alto, soprano).foreach(_.foreach(println))
  }

}