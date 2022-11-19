import React from 'react';
import { Navigate, Route, Routes} from 'react-router-dom';

//Componentes.
import LoggedHeader from './components/LoggedHeader/LoggedHeader';

//Stateless.
import { Main } from 'components/wrappers';
import { Footer, NavBar } from 'components/standalone';
import {MdOutlineHomeWork, MdMonetizationOn} from 'react-icons/md';

//Páginas.
import OperationSelection from 'pages/Operation/OperationSelection';
import Branches  from 'pages/Branches/Branches';

const paths = {
    operation:  "/operacion",
    branches:   "/sucursales",
}

const tabs = [
    {path:paths.operation, icon: <MdMonetizationOn />, label:'operación'},
    {path:paths.branches,  icon: <MdOutlineHomeWork />,  label:'sucursales'},
]

/**Devuelve la página principal dependiente de una sesión iniciada para mostrarse.
 * Contiene todas las vistas principales de la aplicación.
 * */
export default function Home():JSX.Element {

    return (
    <>  
        <LoggedHeader />
        
        <Main 
        menu={<NavBar tabs={tabs} />} 
        content={

            <Routes>
                <Route index element={<Navigate to={'.'+paths.operation}/>}        />

                <Route path={paths.operation+"/*"}  element={<OperationSelection />}        />
                <Route path={paths.branches +"/*"}  element={<Branches  />}        />
                <Route path="*"                     element={<Navigate to={"/"} />}/>
            </Routes>

        } />
        
        <Footer />
    </>)
}