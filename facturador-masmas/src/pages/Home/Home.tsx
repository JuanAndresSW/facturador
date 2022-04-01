import React from 'react';
import { Navigate, Route, Routes} from 'react-router-dom';

//Componentes.
import LoggedHeader from './components/LoggedHeader/LoggedHeader';
import Subheader from './components/SubHeader/Subheader';
import NavBar from './components/NavBar/NavBar';

//Stateless.
import { Footer } from 'styledComponents';
import {AiFillDollarCircle} from 'react-icons/ai';
import {MdClass, MdPoll, MdPointOfSale} from 'react-icons/md';

//Páginas.
import Operation from 'pages/Operation/Operation';
import Books from 'pages/Books/Books';
import Stats from 'pages/Stats/Stats';
import Points from 'pages/Points/Points';

const paths = {
    operation: "/operacion",
    books: "/libros",
    stats: "/estadisticas",
    points: "/puntos-de-venta"
}

const tabs = [
    {path:paths.operation, icon: <AiFillDollarCircle />, label:'Operación'},
    {path:paths.books, icon: <MdClass />, label:'Libros'},
    {path:paths.stats, icon: <MdPoll />, label:'Estadística'},
    {path:paths.points, icon: <MdPointOfSale />, label:'Puntos de venta'}
]

//devuelve la página principal dependiente de una sesión iniciada
export default function Home():JSX.Element {

    return (
    <>  
        <LoggedHeader />
        <Subheader />
        <NavBar tabs={tabs} />
        <Routes>
            <Route index element={<Navigate to={'.'+paths.operation}/>}        />

            <Route path={paths.operation+"/*"}  element={<Operation />}        />
            <Route path={paths.books    +"/*"}  element={<Books     />}        />
            <Route path={paths.stats    +"/*"}  element={<Stats     />}        />
            <Route path={paths.points   +"/*"}  element={<Points    />}        />
            <Route path="*"                     element={<Navigate to={"/"} />}/>
        </Routes>
        <Footer />
    </>)
}