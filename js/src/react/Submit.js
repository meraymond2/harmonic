import React from "react"
import { connect } from "react-redux";

import { notesSubmit } from "../redux/actions";

const Submit = ({ disabled, submit, error, result }) =>
    <div>
        <button onClick={submit} disabled={disabled}>Submit</button>
        {error ? <p style={{ color: "red" }}>{error}</p> : null}
        <p>{result.chord.root} - {result.chord.chordClass}</p>
        <ul>
            {result.keys.map(key => <li key={key}>{key}</li>)}
        </ul>
    </div>
;

const mapStateToProps = ({ selectedNotes, error, result }) => ({
    disabled: !Boolean(selectedNotes.length),
    error,
    result
});

const mapDispatchToProps = (dispatch) => ({
    submit: () => dispatch(notesSubmit())
});

export default connect(mapStateToProps, mapDispatchToProps)(Submit);
