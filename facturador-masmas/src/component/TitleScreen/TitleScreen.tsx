import React, { useState } from 'react';
import './TitleScreen.css';
export default function TitleScreen() {
    //objeto de usuario a enviar al servidor
    const user = {name: "", password: ""};

    const [currentLoginValue, setCurrentLoginValue] = useState('text');
    const [placeholder, setPlaceHolder] = useState('Nombre');
    const [errorMessage, setErrorMessage] = useState('');
    
    //comprobar la validez del valor de login
    function checkValidity() {
        if (currentLoginValue === 'text') {
            setErrorMessage('Todavía no implementado :^)');
            //enviar el nombre al servidor
            //si valida, guardar el nombre y cambiar el estado a password
            //si no valida, cambiar el texto de mensaje
            //luego, enviar a validar $user y redirigir acorde a la respuesta
        }
    }
    return (
        <div className='title-wrapper'>
            <h1>Más que un facturador</h1>
            <h2>facturador++ fue diseñado para facilitar el proceso contable para pequeñas empresas y empresas simuladas.
            </h2>
            <input type={currentLoginValue} name='name' placeholder={placeholder}></input>
            <button type='button' onClick={() => checkValidity()}>Iniciar sesión</button>
            <p className='message'>{errorMessage}</p>
        </div>
    );
}