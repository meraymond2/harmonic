import React from "react";
import { connect } from "react-redux";

import NoteSelect from "./NoteSelect";
import Submit from "./Submit";
import { fetchNotes } from "../redux/actions";

class App extends React.Component {

    componentDidMount() {
        this.props.fetchNotes();
    }

    render() {
        return (
            <div>
                <NoteSelect />
                <Submit />
            </div>
        );
    }

}

const mapStateToProps = () => ({});
const mapDispatchToProps = (dispatch) => ({
    fetchNotes: () => dispatch(fetchNotes())
});

export default connect(mapStateToProps, mapDispatchToProps)(App);
