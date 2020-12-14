import React, {Component} from "react";
import MyCalendar from "./MyCalendar";

class UserInputList extends Component{
    render() {
        return(
            <div>
                <div className="form-group" style={{margin: '10px'}}>
                    <label className="control-label" htmlFor="technicalPassportNumber">
                        Technical passport number
                    </label>
                    <input className="form-control" id="technicalPassportNumber" name="technicalPassportNumber"
                           type="text" placeholder="1VW BN7A35 CCXXXXXX" onChange={this.handleInput} required/>
                </div>
                <div className="form-group" style={{margin: '10px'}}>
                    <label className="control-label" htmlFor="drivingExperience">
                        Driving experience
                    </label>
                    <input className="form-control" id="drivingExperience" name="drivingExperience"
                           type="number" placeholder="10" onChange={this.handleInput} required/>
                </div>
                <div className="form-group" style={{margin: '10px'}}>
                    <label className="control-label" htmlFor="dayOfBirth">
                        Day of birth
                    </label>
                    <MyCalendar handleCalendar={this.handleCalendar}/>
                </div>
            </div>
        )
    }

    handleCalendar = (event) => {
        this.props.handleCalendar(event);
    }


    handleInput = (event) => {
        this.props.handleInput(event);
        event.preventDefault();
    }

}

export default UserInputList;