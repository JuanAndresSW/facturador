import React, { useState } from 'react';
import './SplashScreen.css';
import poster from '../../asset/img/poster.jpg';
export default function SplashScreen() {
    //objeto de usuario a enviar al servidor
    const user = {name: null, password: null};

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
        <div className='splash-wrapper'>

        <div className='title-wrapper'>
            <h1>Facturador++</h1>
            <h2>Crea documentos comerciales, observa el libro diario y libro mayor, obtén estadísticas y más</h2>
            <input type={currentLoginValue} name='name' placeholder={placeholder}></input>
            <button type='button' onClick={() => checkValidity()}>Iniciar sesión</button>
            <p className='message'>{errorMessage}</p>
        </div>

        <video autoPlay poster={poster}>
            <source src="../../asset/presentation.webm" />
            Error al cargar el video
        </video>
        </div>
    
    );
}