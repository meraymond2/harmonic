import React from "react";

const array = ["one", "two", "three"];

class App extends React.Component {

    constructor(props) {
        super(props);
        this.handleChange = this.handleChange.bind(this);
    }

    handleChange(event) {
        console.log(event);
    }

    render() {
        return (
            <select size={array.length} multiple value={[]} onChange={this.handleChange}>
                {array.map((opt) => <option value={opt}>{opt}</option>)}
            </select>
        );
    }
}

export default App;