import React from "react";

import NoteSelect from "./NoteSelect";
import { fetchNotes } from "../redux/actions";

class App extends React.Component {

    componentDidMount() {
        this.props.fetchNotes();
    }

    render() {
        return <NoteSelect />
    }

}

const mapStateToProps = () => ({});
const mapDispatchToProps = (dispatch) => ({
    fetchNotes: () => dispatch(fetchNotes())
});

export default connect(mapStateToProps, mapDispatchToProps)(App);
