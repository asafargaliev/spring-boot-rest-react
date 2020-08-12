import React from 'react';
import { BrowserRouter as Router, Route, Switch } from 'react-router-dom'
import ListContractComponent from "./component/contract/ListContractComponent";
import EditContractComponent from "./component/contract/EditContractComponent";
import AddContractComponent from "./component/contract/AddContractComponent";
import {AppContextProvider} from "./AppContext";
import PrivateRoute from "./component/auth/PrivateRoute";
import Login from "./component/auth/Login";
import AuthService from "./service/AuthService";
import Header from "./component/Header";

function App() {
  return (
      <AppContextProvider>
          <div className="container h-100">
              <Router>
                  {AuthService.isAuthenticated() && <Header/>}
                  <Switch>
                      <Route path="/login" component={Login} />
                      <PrivateRoute exact path="/"  component={ListContractComponent} auth={AuthService.isAuthenticated()} />
                      <PrivateRoute path="/edit-contract" component={EditContractComponent} auth={AuthService.isAuthenticated()}/>
                      <PrivateRoute path="/add-contract" component={AddContractComponent} auth={AuthService.isAuthenticated()}/>
                  </Switch>
              </Router>
          </div>
      </AppContextProvider>
  );
}

export default App;
