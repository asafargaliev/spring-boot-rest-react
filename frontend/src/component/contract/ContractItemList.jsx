import React, { Component } from 'react'

class ContractItemList extends Component {

    render() {
        let contract = this.props.contract;
        return (
            <tr key={contract.id}>
                <td>{contract.id}</td>
                <td>{contract.regNumber}</td>
                <td>{contract.insuranceType.name}</td>
                <td>{contract.insuranceAmount}</td>
                <td>{contract.insurancePayment}</td>

                <td>
                   <button className="btn" onClick={() => this.props.editContract(contract.id)}>
                        <i className="fa fa-pencil" aria-hidden="true"></i>
                    </button>
                </td>
                <td>
                    <button className="btn" onClick={() => this.props.deleteContract(contract.id)}>
                        <i className="fa fa-trash" aria-hidden="true"></i>
                    </button>
                </td>
            </tr>
        )
    }
}

export default ContractItemList;