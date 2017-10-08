package controllers

/**
  * Created by michael on 08/10/17.
  */

import dao.NoteDb
import play.api.libs.json.Json
import play.api.mvc.{Action, Controller}


/**
  * Created by michael on 30/12/16.
  */

class NoteController extends Controller {

  def notes = Action {
    Ok(Json.toJson(NoteDb.notes))
  }

}