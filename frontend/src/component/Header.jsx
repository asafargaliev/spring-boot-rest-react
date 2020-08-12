import React, { Component } from 'react'
import AuthService from "../service/AuthService";
import {Redirect} from "react-router-dom";

class Header extends Component {

    constructor(props){
        super(props);
        this.state ={
            redirect: false
        }
        this.logout = this.logout.bind(this);
        this.backToLoginPage = this.backToLoginPage.bind(this);
    }


    logout = (e) => {
        e.preventDefault();
        AuthService.logout(this.backToLoginPage);
    }

    backToLoginPage() {
        this.setState({redirect:true});
    }

    render() {
        const profile = AuthService.getProfile();

        if (this.state.redirect) {
            return <Redirect to="/login" />;
        }

        return (
            <div className="row">
                <div className="col">
                    <nav className="navbar navbar-expand-md navbar-light bg-light">
                        <div className="navbar-collapse collapse w-100 order-1 order-md-0 dual-collapse2">
                            <ul className="navbar-nav mr-auto">
                                <a className="navbar-brand" href="/">
                                    <i className="fa fa-university" aria-hidden="true"></i>
                                </a>
                                <li className="nav-item">
                                    <a className="nav-link active" href="/">Договора</a>
                                </li>
                                <li className="nav-item">
                                    <a className="nav-link" href="/">Справка</a>
                                </li>
                            </ul>
                        </div>
                        <div className="navbar-collapse collapse w-100 order-3 dual-collapse2">
                            <ul className="navbar-nav ml-auto">
                                <li className="nav-item dropdown">
                                    <a className="nav-link dropdown-toggle" id="navbarDropdownMenuLink-4" data-toggle="dropdown"
                                       aria-haspopup="true" aria-expanded="false">
                                        <i className="fa fa-user mr-2" aria-hidden="true"></i>
                                        {profile.sub}
                                    </a>
                                    <div className="dropdown-menu dropdown-menu-right dropdown-info" aria-labelledby="navbarDropdownMenuLink-4">
                                        <a className="dropdown-item" href="/" onClick={this.logout}>Выйти</a>
                                    </div>
                                </li>
                            </ul>
                        </div>
                    </nav>
                </div>
            </div>
        )
    }
}

export default Header;