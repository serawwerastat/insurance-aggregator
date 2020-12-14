import React, {Component} from "react";
import * as R from 'ramda'
import insurerConnectionType from "../insurerConnectionType"

class InsurerFullInfo extends Component {

    state = {
        buttonDisabled: true,
        newInsurerData: {
            id: this.props.insurer.id,
        },
    }

    render() {
        const {insurer} = this.props;
        const {handleInput, handleSubmit} = this;
        return (
            <form className="bg-secondary text-white" onSubmit={handleSubmit}>
                <div className="form-group row" style={{margin: '10px'}}>
                    <label htmlFor="id" className="col-sm-2 col-form-label">ID</label>
                    <div className="col-sm-10">
                        <input type="text" className="form-control" id="id" name="id" value={insurer.id} disabled/>
                    </div>
                </div>
                <div className="form-group row" style={{margin: '10px'}}>
                    <label htmlFor="name" className="col-sm-2 col-form-label">Name</label>
                    <div className="col-sm-10">
                        <input type="text" className="form-control" id="name" name="name" defaultValue={insurer.name}
                               onChange={handleInput}/>
                    </div>
                </div>
                <div className="form-group row" style={{margin: '10px'}}>
                    <label htmlFor="insuranceURL" className="col-sm-2 col-form-label">URL</label>
                    <div className="col-sm-10">
                        <input type="text" className="form-control" id="insuranceURL" name="insuranceURL"
                               defaultValue={insurer.insuranceURL} onChange={handleInput}/>
                    </div>
                </div>
                <div className="form-group row" style={{margin: '10px'}}>
                    <label htmlFor="premiumPath" className="col-sm-2 col-form-label">Path</label>
                    <div className="col-sm-10">
                        <input type="text" className="form-control" id="premiumPath" name="premiumPath"
                               defaultValue={insurer.premiumPath} onChange={handleInput}/>
                    </div>
                </div>
                <div className="form-group row" style={{margin: '10px'}}>
                    <label htmlFor="insurerConnectionType" className="col-sm-2 col-form-label">Type</label>
                    <div className="col-sm-10">
                        <select className="form-control" name="insurerConnectionType"
                                value={insurerConnectionType[insurer.insurerConnectionType]} onChange={handleInput}>
                            <option value={insurerConnectionType.GET_XML}>{insurerConnectionType.GET_XML}</option>
                            <option value={insurerConnectionType.POST_JSON}>{insurerConnectionType.POST_JSON}</option>
                        </select>
                    </div>
                </div>
                <input className="btn btn-light float-right" type="submit" value="Update" style={{margin: '10px'}}
                       disabled={this.state.buttonDisabled}/>
            </form>
        )
    }

    handleInput = event => {
        const target = event.target;
        const value = target.type === 'checkbox' ? target.checked : target.value;
        const name = target.name;

        this.setState({
            newInsurerData: R.mergeDeepRight(this.state.newInsurerData, {[name]: value}),
            buttonDisabled: false
        });
        console.log(this.state)
    }

    handleSubmit = event => {
        this.props.onSumbit(this.state.newInsurerData);
        event.preventDefault();
    }
}

export default InsurerFullInfo;