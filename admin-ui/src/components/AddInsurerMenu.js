import React, {Component} from "react";
import insurerConnectionType from "../insurerConnectionType"
import * as R from "ramda";
import createInsurer from "../createInsurer";
import testInsurer from "../testInsurer";
import AlertBox from "./AlertBox";
import SuccessBox from "./SuccessBox";

class AddInsurerMenu extends Component {

    state = this.initialState;

    render() {
        const {handleSubmit, handleInput, handleTest} = this;
        const {fieldsAreBlocked, newInsurerData} = this.state;
        const {name, insuranceURL, premiumPath} = newInsurerData;
        const testResult = (this.state.errorMsg !== "" && <AlertBox msg={this.state.errorMsg}/>)
            || (this.state.testPassed && <SuccessBox msg="Test passed!"/>)
        return (
            <div className="card bg-light" style={{margin: '10px'}}>
                <div className="text-center">
                    Create new insurer
                </div>
                <form className="bg-secondary text-white" onSubmit={handleTest}>
                    <div className="form-group row" style={{margin: '10px'}}>
                        <label htmlFor="name" className="col-sm-2 col-form-label">Name</label>
                        <div className="col-sm-10">
                            <input type="text" className="form-control" id="name" name="name" onChange={handleInput}
                                   value={name} disabled={fieldsAreBlocked} required/>
                        </div>
                    </div>
                    <div className="form-group row" style={{margin: '10px'}}>
                        <label htmlFor="insuranceURL" className="col-sm-2 col-form-label">URL</label>
                        <div className="col-sm-10">
                            <input type="text" className="form-control" id="insuranceURL" name="insuranceURL"
                                   value={insuranceURL} onChange={handleInput} disabled={fieldsAreBlocked} required/>
                        </div>
                    </div>
                    <div className="form-group row" style={{margin: '10px'}}>
                        <label htmlFor="premiumPath" className="col-sm-2 col-form-label">Path</label>
                        <div className="col-sm-10">
                            <input type="text" className="form-control" id="premiumPath" name="premiumPath"
                                   value={premiumPath} onChange={handleInput} disabled={fieldsAreBlocked} required/>
                        </div>
                    </div>
                    <div className="form-group row" style={{margin: '10px'}}>
                        <label htmlFor="insurerConnectionType" className="col-sm-2 col-form-label">Type</label>
                        <div className="col-sm-10">
                            <select className="form-control" name="insurerConnectionType" onChange={handleInput}
                                    disabled={fieldsAreBlocked}>
                                <option value={insurerConnectionType.GET_XML}>{insurerConnectionType.GET_XML}</option>
                                <option
                                    value={insurerConnectionType.POST_JSON}>{insurerConnectionType.POST_JSON}</option>
                            </select>
                        </div>
                    </div>
                    <button type="button" className="btn btn-dark col-md-2 float-left" style={{margin: '10px'}}
                            onClick={this.reset}>Refresh
                    </button>
                    <button type="button" className="btn btn-light col-md-2 float-right" style={{margin: '10px'}}
                            onClick={handleSubmit} disabled={this.state.createButtonDisabled}>Create
                    </button>
                    <input className="btn btn-primary float-right" type="submit" value="Test" style={{margin: '10px'}}/>
                </form>
                {testResult}
            </div>
        )
    }

    handleInput = event => {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            newInsurerData: R.mergeDeepRight(this.state.newInsurerData, {[name]: value}),
        });
    }

    handleSubmit = event => {
        this.setState({
            testPassed: null,
        })
        createInsurer(this.state.newInsurerData)
            .then(data => this.props.onInsurerAdded(data));
        this.reset();
        event.preventDefault();
    }

    handleTest = event => {
        testInsurer(this.state.newInsurerData).then(data => {
            console.log(data)
            if (data.status === 500) {
                this.setState({
                    testPassed: false,
                    errorMsg: data.entity,
                    createButtonDisabled: true,
                })
            }
            if (data.status === 200) {
                this.setState({
                    errorMsg: "",
                    testPassed: true,
                    createButtonDisabled: false,
                    fieldsAreBlocked: true,
                })
            }
        })
        event.preventDefault();
    }

    reset = () => {
        this.setState(this.initialState)
    }

    get initialState() {
        return {
            fieldsAreBlocked: false,
            createButtonDisabled: true,
            testPassed: null,
            errorMsg: "",
            newInsurerData: {
                name: "",
                insuranceURL: "",
                premiumPath: "",
                insurerConnectionType: insurerConnectionType.GET_XML
            }
        }
    }

}

export default AddInsurerMenu;