package controllers

import com.google.inject.Singleton
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
    Ok
  }

}
