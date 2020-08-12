import React, {createContext, useState, useEffect} from 'react';
import ApiService from "./service/ApiService";

export const AppContext = createContext();

export const AppContextProvider = ({children}) => {

    const [types, setTypes] = useState(null);

    useEffect(() => {
        fetchTypes();
    }, []);

    function fetchTypes() {
        ApiService.fetchTypes()
            .then((res) => {
                setTypes(res.data);
            });
    }

    return (<AppContext.Provider value={{types: types}}>
        {children}
    </AppContext.Provider>);

}