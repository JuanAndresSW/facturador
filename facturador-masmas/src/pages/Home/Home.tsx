import React from 'react';
import { Navigate, Route, Routes} from 'react-router-dom';

//Componentes.
import LoggedHeader from './components/LoggedHeader/LoggedHeader';
import Subheader from './components/SubHeader/Subheader';
import NavBar from './components/NavBar/NavBar';
import Main from './components/Main/Main';

//Stateless.
import { Footer } from 'styledComponents';
import {MdClass, MdPoll, MdOutlineHomeWork, MdOutlineEmojiPeople, MdMonetizationOn} from 'react-icons/md';

//Páginas.
import Operation from 'pages/Operation/Operation';
import Books from 'pages/Books/Books';
import Stats from 'pages/Stats/Stats';
import Branches from 'pages/Branches/Branches';

const paths = {
    operation: "/operacion",
    books: "/libros",
    stats: "/estadisticas",
    branches: "/sucursales",
    clients: "/clientes"
}

const tabs = [
    {path:paths.operation, icon: <MdMonetizationOn />, label:'operación'},
    {path:paths.books,     icon: <MdClass />,            label:'libros'},
    {path:paths.stats,     icon: <MdPoll />,             label:'estadísticas'},
    {path:paths.branches,  icon: <MdOutlineHomeWork />,  label:'sucursales'},
    {path:paths.clients,   icon: <MdOutlineEmojiPeople />, label:'clientes'}
]

//Devuelve la página principal dependiente de una sesión iniciada.
export default function Home():JSX.Element {

    return (
    <>  
        <LoggedHeader />
        <Subheader />
        
        <Main 
        menu={<NavBar tabs={tabs} />} 
        content={

            <Routes>
                <Route index element={<Navigate to={'.'+paths.operation}/>}        />

                <Route path={paths.operation+"/*"}  element={<Operation />}        />
                <Route path={paths.books    +"/*"}  element={<Books     />}        />
                <Route path={paths.stats    +"/*"}  element={<Stats     />}        />
                <Route path={paths.branches +"/*"}  element={<Branches  />}        />
                <Route path={paths.clients  +"/*"}  element={<></>}        />
                <Route path="*"                     element={<Navigate to={"/"} />}/>
            </Routes>

        } />
        
        
        <Footer />
    </>)
}