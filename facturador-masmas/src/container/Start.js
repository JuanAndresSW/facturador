import React from 'react';
import {Navigate} from 'react-router-dom';
import isUserAuthenticated from '../index';

export default function Start() {
    if (isUserAuthenticated()) {
        return (
            <>
            PÁGINA DE INICIO
            </>
        )
    }
    else {
        return (
            <Navigate to="/LogIn" />
        )
    }
}