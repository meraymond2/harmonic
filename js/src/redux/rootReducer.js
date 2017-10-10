import { combineReducers } from "redux";

const initialState = {
    noteSpns: [],
    notesBySpn: {},
    selectedNotes: [],
    results: []
};

const rootReducer = (state = initialState, action) => {
    switch (action.type) {
        case NOTES_RECEIVE:
            return {
                ...state,
                noteSpns: action.notes.map(n => n.spn),
                notesBySpn: action.notes.reduce((acc, n) => ({
                    ...acc,
                    [n.spn]: n
                }), {})
            };
        default:
            return state;
    }
};

export default rootReducer;