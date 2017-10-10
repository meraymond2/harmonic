import { combineReducers } from "redux";
import * as actions from "./actions";

const initialState = {
    noteSpns: [],
    notesBySpn: {},
    selectedNotes: [],
    error: "",
    result: {
        chord: {
            chordClass: "",
            root: ""
        },
        keys: []
    }
};

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
        case actions.NOTES_RECEIVE:
            return {
                ...state,
                noteSpns: action.notes.map(n => n.spn),
                notesBySpn: action.notes.reduce((acc, n) => ({
                    ...acc,
                    [n.spn]: n
                }), {})
            };
        case actions.SELECTED_NOTES_UPDATE:
            return {
                ...state,
                selectedNotes: action.notes
            };
        case actions.CHORD_RECEIVE:
            return {
                ...state,
                result: action.chord
            };
        case actions.ERROR_UPDATE:
            return {
                ...state,
                error: action.error
            };
        case actions.ERROR_CLEAR:
            return {
                ...state,
                error: ""
            };
        default:
            return state;
    }
};

export default rootReducer;