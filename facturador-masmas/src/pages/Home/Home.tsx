import React from 'react';
import { Navigate, Route, Routes} from 'react-router-dom';

//Componentes.
import LoggedHeader from './components/LoggedHeader/LoggedHeader';
import Subheader from './components/SubHeader/Subheader';
import NavBar from './components/NavBar/NavBar';
import Main from './components/Main/Main';

//Stateless.
import { Footer } from 'styledComponents';
import {AiFillDollarCircle} from 'react-icons/ai';
import {MdClass, MdPoll, MdOutlineHomeWork} from 'react-icons/md';

//Páginas.
import Operation from 'pages/Operation/Operation';
import Books from 'pages/Books/Books';
import Stats from 'pages/Stats/Stats';
import Branches from 'pages/Branches/Branches';
import { BiGroup } from 'react-icons/bi';

const paths = {
    operation: "/operacion",
    books: "/libros",
    stats: "/estadisticas",
    branches: "/sucursales",
    partners: "/socios"
}

const tabs = [
    {path:paths.operation, icon: <AiFillDollarCircle />, label:'Operación'},
    {path:paths.books, icon: <MdClass />, label:'Libros'},
    {path:paths.stats, icon: <MdPoll />, label:'Estadística'},
    {path:paths.branches, icon: <MdOutlineHomeWork />, label:'Sucursales'},
    {path:paths.partners, icon: <BiGroup />, label:'Socios'}
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
                <Route path={paths.partners +"/*"}  element={<></>}        />
                <Route path="*"                     element={<Navigate to={"/"} />}/>
            </Routes>

        } />
        
        
        <Footer />
    </>)
}