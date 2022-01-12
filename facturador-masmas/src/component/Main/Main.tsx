import React from "react";
import {AiFillDollarCircle} from 'react-icons/ai';
import {MdClass, MdPoll, MdPinDrop} from 'react-icons/md';
import TabbedPane from './TabbedPane/TabbedPane'
import {Transaction, Books, Stats, Spots} from './Section';

export default function Main(){
    return (
    <TabbedPane>
        <div label={<><AiFillDollarCircle />transacción</>}><Transaction /></div>
        <div label={<><MdClass />libros</>}><Books /></div>
        <div label={<><MdPoll />estadísticas</>}><Stats /></div>
        <div label={<><MdPinDrop />puntos de venta</>}><Spots /></div>
    </TabbedPane>
    )
}