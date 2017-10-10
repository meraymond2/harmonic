import { checkStatusAndGetJSON } from "../utils";
export const NOTES_RECEIVE = "NOTES_RECEIVE";

export const notesReceive = (notes) => ({
    type: NOTES_RECEIVE,
    notes
});

export const fetchNotes = (dispatch) => {
    fetch("/api/notes")
        .then(checkStatusAndGetJSON)
        .then(json => dispatch(notesReceive(json)));
};
