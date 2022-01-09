import React from 'react';
import { Navigate } from 'react-router-dom';
import isUserAuthenticated from '../index';
export default function Start() {
    if (isUserAuthenticated()) {
        return (React.createElement(React.Fragment, null, "P\u00C1GINA DE INICIO"));
    }
    else {
        return (React.createElement(Navigate, { to: "/LogIn" }));
    }
}
