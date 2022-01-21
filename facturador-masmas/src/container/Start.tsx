import React from 'react';
import { Navigate, Route, Routes } from 'react-router-dom';
import Header from '../components/Header/Header';
import Subheader from '../components/SubHeader/Subheader';
import NavBar from '../components/NavBar/NavBar';
import Footer from '../components/Footer/Footer';
import {Transaction, Books, Stats, Spots} from '../components/Main';
import Protected from '../script/Protected';
import {AiFillDollarCircle} from 'react-icons/ai';
import {MdClass, MdPoll, MdPinDrop} from 'react-icons/md';

const paths = {
    transaction: "/transaccion",
    books: "/libros",
    stats: "/estadisticas",
    spots: "/puntos-de-venta"
}

//key, although necessary, cannot be used as a property, hence the use of accessKey
const tabs = [
    <div tabHeader={<><AiFillDollarCircle />Transacción</>} key={paths.transaction} accessKey={paths.transaction}><Transaction /></div>,
    <div tabHeader={<><MdClass />Libros</>} key={paths.books} accessKey={paths.books}><Books /></div>,
    <div tabHeader={<><MdPoll />Estadística</>} key={paths.stats} accessKey={paths.stats}><Stats /></div>,
    <div tabHeader={<><MdPinDrop />Puntos de venta</>} key={paths.spots} accessKey={paths.spots}><Spots /></div>
]

export default function Start():JSX.Element {
    return (
    <Protected>  
        <Header />
        <Subheader />
        <NavBar>
            {tabs}
        </NavBar>
        <Routes>
            <Route path='' element={<Navigate to={paths.transaction} />} />
            <Route path={paths.transaction+"/"} element={<Transaction />} />
            <Route path={paths.books+"/*"} element={<Books />}/>
            <Route path={paths.stats+"/*"} element={<Stats />}/>
            <Route path={paths.spots+"/*"} element={<Spots />}/>
            <Route path="*" element={<Navigate to={"/"} />}/>
        </Routes>
        <Footer />
    </Protected>)
}