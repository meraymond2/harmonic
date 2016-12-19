package models

import oldmodels.Letter._
import org.specs2.mutable.Specification

/**
  * Created by michael on 18/12/16.
  */

class LetterSpec extends Specification {

  "above" should {

    "calculate the gap between a letter and one below it" in {
      a.above(e) must_=== 3
      e.above(a) must_=== 4

      a.above(g) must_=== 1
      g.above(a) must_=== 6

      c.above(c) must_=== 0
      d.above(d) must_=== 0
    }

  }

}
