import React from 'react';
import { Navigate, Route, Routes} from 'react-router-dom';

import Header from 'components/layout/Header/Header';
import Subheader from './SubHeader/Subheader';
import NavBar from './NavBar/NavBar';
import Footer from 'components/layout/Footer/Footer';
import {Operation, Books, Stats, Points} from './main';

import {AiFillDollarCircle} from 'react-icons/ai';
import {MdClass, MdPoll, MdPinDrop} from 'react-icons/md';

const paths = {
    operation: "/operacion",
    books: "/libros",
    stats: "/estadisticas",
    points: "/puntos-de-venta"
}

//key, si bien obligatoria, no puede ser leída como propiedad, por tanto el uso de accessKey
const tabs = [
    <div tabHeader={<><AiFillDollarCircle />Operación</>} key={paths.operation} accessKey={'.'+paths.operation}><Operation /></div>,
    <div tabHeader={<><MdClass />Libros</>} key={paths.books} accessKey={'.'+paths.books}><Books /></div>,
    <div tabHeader={<><MdPoll />Estadística</>} key={paths.stats} accessKey={'.'+paths.stats}><Stats /></div>,
    <div tabHeader={<><MdPinDrop />Puntos de venta</>} key={paths.points} accessKey={'.'+paths.points}><Points /></div>
]

//devuelve la página principal dependiente de una sesión iniciada
export default function Start():JSX.Element {

    return (
    <>  
        <Header isAuthenticated={true} />
        <Subheader />
        <NavBar>
            {tabs}
        </NavBar>
        <Routes>
            <Route index element={<Navigate to={'.'+paths.operation} />} />
            <Route path={paths.operation+"/*"} element={<Operation />} />
            <Route path={paths.books+"/*"} element={<Books />}/>
            <Route path={paths.stats+"/*"} element={<Stats />}/>
            <Route path={paths.points+"/*"} element={<Points />}/>
            <Route path="*" element={<Navigate to={"/"} />}/>
        </Routes>
        <Footer />
    </>)
}