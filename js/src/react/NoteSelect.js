import React from "react";
import { connect } from "react-redux";
import Select from 'react-select';
import 'react-select/dist/react-select.css';

import { selectedNotesUpdate } from "../redux/actions";

const NoteSelect = ({ noteSpns, selected, handleSelect }) =>
    <div>
        <Select
            onChange={handleSelect}
            options={noteSpns.map(note => ({ value: note, label: note }))}
            multi
            value={selected}
        />
    </div>
;

const mapStateToProps = ({ noteSpns, selectedNotes }) => ({
    noteSpns,
    selected: selectedNotes
});

const mapDispatchToProps = (dispatch) => ({
    handleSelect: (options) => dispatch(selectedNotesUpdate(options.map(opt => opt.value)))
});

export default connect(mapStateToProps, mapDispatchToProps)(NoteSelect);
