import React, {Component} from "react";
import InsurerList from "./InsurerList";
import AddInsurerMenu from "./AddInsurerMenu";


class InsurersConfiguration extends Component {

    state = {
        addIsOpen: false,
        newInsurer: {}
    }

    render() {
        const {onInsurerAdded, onAddClicked} = this;
        const addMenu = this.state.addIsOpen && <AddInsurerMenu onInsurerAdded={onInsurerAdded}/>
        return (
            <div className="card" style={{width: '50%', margin: '0 auto', marginTop: "10px"}}>
                <div className="card-header row no-gutters">
                    <h2 className="text-center col-md-10">Insurers</h2>
                    <button type="button" className="btn btn-outline-secondary col-md-2 float-right"
                            onClick={onAddClicked}>Add
                    </button>
                </div>
                {addMenu}
                <InsurerList newInsurer={this.state.newInsurer}/>
            </div>
        )
    }

    onAddClicked = () => {
        this.setState({
            addIsOpen: !this.state.addIsOpen
        })
    }

    onInsurerAdded = (insurer) => {
        this.setState({
            newInsurer: insurer
        })
    }
}

export default InsurersConfiguration;