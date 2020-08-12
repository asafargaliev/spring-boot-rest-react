import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import ContractItemList from "./ContractItemList";
import Header from "../Header";

class Base extends Component {

    render() {
        return (
            <div className="container">
                <div className="row">
                    <div className="col">
                        <Header/>
                    </div>
                </div>
                <div className="row my-3">
                    <div className="col"></div>
                    <div className="col">
                        <h2 className="text-center m-2">Договора</h2>
                    </div>
                    <div className="col">
                        <button className="btn btn-success float-right mt-2" onClick={() => this.addContract()}>
                            <i className="fa fa-plus" aria-hidden="true"></i>
                            &nbsp; Создать
                        </button>
                    </div>
                </div>
                <div className="row">
                    <div className="col">
                        <table className="table table-striped">
                            <thead>
                            <tr>
                                <th className="hidden">ИД</th>
                                <th>Номер</th>
                                <th>Тип</th>
                                <th>Страховая сумма</th>
                                <th>Платеж</th>
                                <th></th>
                                <th></th>
                            </tr>
                            </thead>
                            <tbody>
                            {
                                this.state.contracts.map(
                                    contract => {
                                        return <ContractItemList key={contract.id} contract={contract}
                                                                 editContract={this.editContract}
                                                                 deleteContract={this.deleteContract} />
                                    }
                                )
                            }
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        );
    }

}

export default Base;