import React, { Component } from 'react'
import ApiService from "../../service/ApiService";
import ContractItemList from "./ContractItemList";
import Pagination from "react-js-pagination";

class ListContractComponent extends Component {

    constructor(props) {
        super(props)
        this.state = {
            contracts: [],
            activePage: 1,
            totalPages: null,
            itemsCountPerPage: null,
            totalItemsCount: null,
            message: null
        }
        this.deleteContract = this.deleteContract.bind(this);
        this.editContract = this.editContract.bind(this);
        this.addContract = this.addContract.bind(this);
        this.fetchUserList = this.fetchUserList.bind(this);
        this.handlePageChange = this.handlePageChange.bind(this);
    }

    componentDidMount() {
        this.fetchUserList(1);
    }

    fetchUserList(page) {
        ApiService.fetchContracts(page-1)
            .then((res) => {
                const totalPages = res.data.totalPages;
                const itemsCountPerPage = res.data.size;
                const totalItemsCount = res.data.totalElements;

                this.setState({totalPages: totalPages})
                this.setState({totalItemsCount: totalItemsCount})
                this.setState({itemsCountPerPage: itemsCountPerPage})
                this.setState({contracts: res.data.content});
            });
    }

    handlePageChange(pageNumber) {
        this.setState({activePage: pageNumber})
        this.fetchUserList(pageNumber);
    }

    deleteContract(contractId) {
        const conf = window.confirm('Вы действительно хотите удалить запись?');
        if (conf) {
            ApiService.deleteContract(contractId)
                .then(res => {
                    this.setState({message : 'Contract deleted successfully.'});

                    //this.setState({contracts: this.state.contracts.filter(contract => contract.id !== contractId)});
                    this.fetchUserList(this.state.activePage);
                })
        }
    }

    editContract(contractId) {
        window.localStorage.setItem("contractId", contractId);
        this.props.history.push('/edit-contract');
    }

    addContract() {
        window.localStorage.removeItem("contractId");
        this.props.history.push('/add-contract');
    }

    render() {
        return (
            <div>
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
                <div className="row">
                    <div className="col">
                    </div>
                    <div className="col d-flex justify-content-center">
                        <Pagination
                            activePage={this.state.activePage}
                            itemsCountPerPage={this.state.itemsCountPerPage}
                            totalItemsCount={this.state.totalItemsCount}
                            linkClass='btn btn-light'
                            activeLinkClass='active'
                            onChange={this.handlePageChange}
                        />
                    </div>
                    <div className="col"></div>
                </div>
            </div>
        );
    }

}

export default ListContractComponent;