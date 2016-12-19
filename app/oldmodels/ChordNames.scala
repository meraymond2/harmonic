package oldmodels

/**
  * Created by michael on 18/12/16.
  */

object ChordNames extends Enumeration {
  type ChordName = ChordNames.Value

  val majorTriad = Value("Major Triad")
  val minorTriad = Value("Minor Triad")
}