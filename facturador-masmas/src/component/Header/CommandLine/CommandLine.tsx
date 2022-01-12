import React from 'react';
import { DiTerminal } from 'react-icons/di';
import './CommandLine.css';
export default function CommandLine() {
    return (
        <div id='command'><DiTerminal /><input type={"search"} placeholder='comando'></input></div>
    )
}