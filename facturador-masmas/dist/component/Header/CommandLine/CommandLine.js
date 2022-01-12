import React from 'react';
import { DiTerminal } from 'react-icons/di';
import './CommandLine.css';
export default function CommandLine() {
    return (React.createElement("div", { id: 'command' },
        React.createElement(DiTerminal, null),
        React.createElement("input", { type: "search", placeholder: 'comando' })));
}
