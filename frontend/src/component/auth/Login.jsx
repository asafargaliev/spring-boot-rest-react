import React, { Component } from 'react'
import AuthService from "../../service/AuthService";

class Login extends Component {

    constructor(props){
        super(props);
        this.state ={

            // for development
            username: 'testUser',
            password: 'test',

            redirect: false
        }
        this.login = this.login.bind(this);
        this.onSuccessLogin = this.onSuccessLogin.bind(this);
    }

    onChange = (e) =>
        this.setState({ [e.target.name]: e.target.value });

    login = (e) => {
        e.preventDefault();
        AuthService.login(this.state.username, this.state.password, this.onSuccessLogin);
    }

    onSuccessLogin() {
        console.log("success login");

        /*this.setState({
            redirect: true
        });*/

        window.location.href = '/'
    }

    render() {
        /*if (this.state.redirect) {
            console.log("redirect");
            return <Redirect to="/add-contract" />;
        }*/

        return (
        <div className="row h-100 align-items-center">
            <div className="col"></div>
            <div className="col text-center">
                <form>
                    <i className="fa fa-university fa-4x mb-2" aria-hidden="true"></i>
                    <h1 className="h3 mb-3 font-weight-normal">Вход</h1>
                    <label htmlFor="username" className="sr-only">Логин</label>
                    <input id="username" name="username" value={this.state.username} onChange={this.onChange} className="form-control" placeholder="Логин"
                           required autoFocus/>
                    <label htmlFor="password" className="sr-only">Пароль</label>
                    <input type="password" id="password" name="password" value={this.state.password} onChange={this.onChange} className="form-control" placeholder="Пароль"
                           required/>
                    <div className="checkbox mb-3">
                        <label className="my-2">
                            <input type="checkbox" value="remember-me"/> Запомнить меня
                        </label>
                    </div>
                    <button className="btn btn-lg btn-primary btn-block" onClick={this.login}>Войти</button>
                    <p className="mt-3 mb-3 text-muted">&copy; Safargaliev Artem 2020</p>
                </form>
            </div>
            <div className="col"></div>
        </div>
        )
    }
}

export default Login;