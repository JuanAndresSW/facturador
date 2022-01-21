import React, { useState } from "react";
import '../style/form.css'

export default function LogIn() {
    const [user, setUser] = useState('');
    const [password, setPassword] = useState('');
    const [error, setError] = useState('ERROR');
    
    return (
        <div className="panel">
            <h1 className="title">Iniciar sesión</h1>

            <label> Nombre o correo electrónico
                <input type="text" maxLength={254}></input>
            </label>

            <label> Contraseña
                <input type="password" maxLength={128}></input>
            </label>
            <a href="about:blank" target="_blank">Olvidé mi contraseña</a>

            <button type="submit">Ingresar</button>

            <p className="error">{error}</p>
        </div>
    )
}