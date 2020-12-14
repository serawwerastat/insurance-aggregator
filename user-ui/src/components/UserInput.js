import React, {Component} from "react";
import Spinner from "./Spinner";
import * as R from 'ramda'
import UserInputList from "./UserInputList";

class UserInput extends Component {

    state = {
        loading: true,
        userDataFields: [],
        userData: {
            technicalPassportNumber: "",
            dayOfBirth: "01.01.1990",
            drivingExperience: 0
        },
    }

    render() {
        return (
            <div className="card" style={{width: '50%', margin: '0 auto', marginTop: "10px"}}>
                <div className="card-header">
                    <h2 className="text-center">User input</h2>
                </div>
                <form onSubmit={this.handleSubmit}>
                    <UserInputList userDataFields={this.state.userDataFields} handleInput={this.handleInput} handleCalendar={this.handleCalendar}/>
                    <input className="btn btn-primary float-right" type="submit" value="Find" style={{margin: '10px'}}/>
                </form>
            </div>
        )
    }


    handleInput = event => {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            userData: R.mergeDeepRight(this.state.userData, {[name]: value})
        });
    }

    handleCalendar = (date) => {
        this.setState({
            userData: R.mergeDeepRight(this.state.userData, {dayOfBirth: date})
        })
    }

    handleSubmit = (event) => {
        this.props.handleSubmit(this.state.userData);
        event.preventDefault();
    }

}

export default UserInput
