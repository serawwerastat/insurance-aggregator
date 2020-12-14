import React, {Component} from "react";
import 'bootstrap/dist/css/bootstrap.css'
import {BrowserRouter as Router, Link, Route} from 'react-router-dom';
import InsurersConfiguration from "./components/InsurersConfiguration";

class App extends Component {
    render() {
        return (
            <div>
                <div className="jumbotron" style={{margin: '0 auto'}}>
                    <h1>Insurer aggregator admin panel</h1>
                </div>
                <Router>
                    <nav className="navbar navbar-expand-lg navbar-light bg-light">
                        <div className="collapse navbar-collapse" id="navbarSupportedContent">
                            <ul className="navbar-nav mr-auto">
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/">Home</Link>
                                </li>
                                <li className="nav-item active">
                                    <Link className="nav-link" to="/insurers">Insurers</Link>
                                </li>
                            </ul>
                        </div>
                    </nav>
                    <Route exact path='/insurers' component={InsurersConfiguration}/>
                </Router>
            </div>
        );
    }

}

export default App;
