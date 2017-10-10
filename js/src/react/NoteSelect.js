import React from "react";
import { connect } from "react-redux";

const NoteSelect = ({ noteSpns }) =>
    <div>
        {noteSpns.map(spn => <span>{spn}</span>)}
    </div>
;

const mapStateToProps = ({ noteSpns }) => ({
    noteSpns
});

export default connect(mapStateToProps)(NoteSelect);
