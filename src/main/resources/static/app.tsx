import React from "react";
import ReactDOM from "react-dom";
import Beer from "./components/Beer";


class App extends React.Component {

    constructor(props) {
        super(props);
    }

    render() {
        return (
            <React.Fragment>
                <div className="row">
                    <div className="col-sm">
                        <div className="card margin-40">
                            <div className="card-body">
                                <h1>The random beer app</h1>
                            </div>
                        </div>
                    </div>
                </div>
                <Beer/>
            </React.Fragment>
        )
    }
}

ReactDOM.render(
    <App/>,
    document.getElementById('react')
);

