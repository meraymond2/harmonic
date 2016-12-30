package controllers

import dao.KeyDb
import models.chords.Chord
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}

import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future

/**
  * Created by michael on 30/12/16.
  */

class KeyController extends Controller {

  def findKeys = Action.async { implicit request =>
    request.body.asJson.fold(Future(BadRequest("Must be JSON.")))(json =>
      (json \ "chord").asOpt[Chord].fold(Future(BadRequest("Improperly formatted JSON.")))(chord =>
        KeyDb.findKeys(chord).map(_.map(_.name)).map(keys => Ok(Json.toJson(keys)))
      )
    )
  }

}
