import React from "react";

import { checkStatusAndGetJSON } from "./utils";
import Selects from "./Selects";

class App extends React.Component {

    constructor(props) {
        super(props);
        this.state = {
            noteSpns: [],
            notesBySpn: {},
            selected: {},
            chord: {
                chordClass: "",
                root: ""
            },
            keys: []
        };
        this.handleChange = this.handleChange.bind(this);
        this.submit = this.submit.bind(this);
    }

    componentDidMount() {
        fetch("/api/notes")
            .then(resp => resp.json())
            .then(json => this.setState({
                noteSpns: json.map(note => note.spn),
                notesBySpn: json.reduce((acc, note) => ({ ...acc, [note.spn]: note }), {})
            }))
    }

    handleChange(event) {
        const select = event.target;
        const values = [...select.options].filter(opt => opt.selected).map(opt => opt.value);
        this.setState({ selected: { ...this.state.selected, [select.id]: values }});
    }

    submit() {
        const selectedNotes = Object.keys(this.state.selected).reduce((acc, octave) => acc.concat(this.state.selected[octave]), []);
        const fullNotes = selectedNotes.map(spn => this.state.notesBySpn[spn]);
        fetch("/api/chord", {
            body: JSON.stringify(fullNotes),
            headers: {
                "Content-Type": "application/json"
            },
            method: "POST",
        }).then(checkStatusAndGetJSON).then(json => this.setState({
            chord: json.chord,
            keys: json.keys
        })).catch(console.error);
    }

    render() {
        return (
            <div>
                <Selects
                    noteSpns={this.state.noteSpns}
                    handleChange={this.handleChange}
                    submit={this.submit}
                />
                <p></p>
            </div>
        );
    }
}

export default App;