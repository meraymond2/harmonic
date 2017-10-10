import { checkStatusAndGetJSON } from "../utils";

// Action Constants

export const NOTES_RECEIVE = "NOTES_RECEIVE";
export const SELECTED_NOTES_UPDATE = "SELECTED_NOTES_UPDATE";
export const CHORD_RECEIVE = "CHORD_RECEIVE";
export const ERROR_UPDATE = "ERROR_UPDATE";
export const ERROR_CLEAR = "ERROR_CLEAR";

// Action Creators

export const notesReceive = (notes) => ({
    type: NOTES_RECEIVE,
    notes
});

export const selectedNotesUpdate = (notes) => ({
    type: SELECTED_NOTES_UPDATE,
    notes
});

export const chordReceive = (chord) => ({
    type: CHORD_RECEIVE,
    chord
});

export const errorUpdate = (errorMsg) => ({
    type: ERROR_UPDATE,
    error: errorMsg
});

export const errorClear = () => ({
    type: ERROR_CLEAR
});

// Async Actions

export const fetchNotes = () => (dispatch) => {
    fetch("/api/notes")
        .then(checkStatusAndGetJSON)
        .then(json => dispatch(notesReceive(json)));
};

export const notesSubmit = () => (dispatch, getState) => {
    dispatch(errorClear());
    const { selectedNotes, notesBySpn } = getState();
    const fullNotes = selectedNotes.map(spn => notesBySpn[spn]);
    fetch("/api/chord", {
        body: JSON.stringify(fullNotes),
        headers: {
            "Accept": "application/json",
            "Content-Type": "application/json"
        },
        method: "POST"
    })
        .then(checkStatusAndGetJSON)
        .then(json => dispatch(chordReceive(json)))
        .catch(error => dispatch(errorUpdate(JSON.stringify(error))))
};