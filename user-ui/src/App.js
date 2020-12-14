import React, {Component} from "react";
import 'bootstrap/dist/css/bootstrap.css'
import UserInput from "./components/UserInput"
import InsurerBrowser from "./components/InsurerBrowser";

class App extends Component {

    state = {
        userData: null
    }


    render() {
        const userInput = !this.state.userData && <UserInput handleSubmit={this.handleSubmit}/>;
        const insurerBrowser = this.state.userData && <InsurerBrowser userData={this.state.userData}/>;
        return (
            <div>
                <div className="jumbotron" style={{margin: '0 auto'}}>
                    <h1>Insurer aggregator</h1>
                </div>
                <nav className="navbar navbar-expand-lg navbar-light bg-light">
                    <div className="collapse navbar-collapse" id="navbarSupportedContent">
                        <div className="navbar-nav mr-auto">
                            <a className="nav-link" href="/">Home <span className="sr-only">(current)</span></a>
                        </div>
                    </div>
                </nav>
                {userInput}
                {insurerBrowser}
            </div>
        );
    }


    handleSubmit = (userData) => {
        this.setState({userData: userData});
    }
}

export default App;
