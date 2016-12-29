So far I have:
-a list of possible notes
-a list of possible intervals
-the ability to find a note from a note + an interval
-the ability to find an interval between two notes
-the ability to recognise a min/maj triad

**To Do:**
Chords:
-able to recognise six chords (I can't remember if there are any formal requirements beyond that the root. I think any additional thirds or fifths would be optional.)
-able to recognise six-four chords (not a huge priority)
-able to recognise diminished triads
-able to recognise augmented triads
-able to recognise various seventh chords (dominant, major-minor, minor, major, diminished)

Keys:
-for a given chord, identify which keys in which it is diatonic
    -for this, is it enough to simply check the notes and make sure they're in that key?
    -ABC are all in A-minor, but it's certainly not a chord.
    -there's also the issue of chords that don't just have diatonic notes, what about the Neapolitan Sixth?
    -I think there's a decent way to do this. Chords don't need to be singletons
-for a given key, identify which position the chord occupies (i, V, etc.)

Code:
-is it good or bad to have so many singletons? The intervals have to be objects, due to the way they reference
themselves. Do notes? At the moment they are because of the way I figure out intervals. Given an F and an augmented
second, I need to be able to tell that that's a G#. I can't think of a better way of determining that programmatically.
-I'd like to be able to generate chords programatically, that will help with keys. I'd like to say that the minor key
of a given PitchClass with include a set of chords, built on the pitch class, without having to hardcode them all.