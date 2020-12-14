import React, {Component} from "react";
import InsurerFullInfo from "./InsurerFullInfo";
import updateInsurer from "../updateInsurer";
import changeInsurerActiveState from "../changeInsurerActiveState";

class Insurer extends Component {

    state = {
        fullInfo: false,
        insurer: {},
    }

    componentDidMount() {
        const {insurer} = this.props;
        this.setState({
            insurer: insurer,
        })
    }

    render() {
        const {insurer} = this.state
        const {onSubmit, onSwitch, onDelete} = this;
        const fullInfo = this.state.fullInfo && <InsurerFullInfo insurer={insurer} onSumbit={onSubmit}/>;
        return (
            <div key={insurer.id} className="card bg-light" style={{margin: '10px'}}>
                <div className="row no-gutters">
                    <div className="text-center col-md-8" onClick={this.onNameClick}>
                        {insurer.name}
                    </div>
                    <div className="col-md-2">
                        <div className="custom-control custom-switch">
                            <input type="checkbox" className="custom-control-input" id="customSwitch1"
                                   defaultChecked={insurer.active} onChange={onSwitch}/>
                            <label className="custom-control-label" htmlFor="customSwitch1">Active</label>
                        </div>
                    </div>
                    <button type="button" className="close col-md-1 btn-danger" style={{margin: 'auto'}}
                            aria-label="Close" onClick={onDelete}>
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
                {fullInfo}
            </div>
        )
    }

    onNameClick = () => {
        this.setState({
            fullInfo: !this.state.fullInfo,
        })
    }

    onSwitch = () => {
        changeInsurerActiveState(this.state.insurer).then(data => {
            this.setState({
                insurer: data,
            })
        });
    }

    onSubmit = (insurer) => {
        updateInsurer(insurer).then(data => {
            this.setState({
                insurer: data,
            })
        });
    }

    onDelete = () => {
        this.props.onDelete(this.state.insurer);
    }
}

export default Insurer;