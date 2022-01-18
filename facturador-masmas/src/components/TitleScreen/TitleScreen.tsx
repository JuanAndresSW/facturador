import React, {useState} from 'react';
import Session from '../../script/Session';
import './TitleScreen.css';

//objeto de usuario que representa un usuario en la base de datos
const tempUser = {
    name: 'niño',
    password: 'software'
}
//objeto de usuario a enviar al servidor
const user = {name: "", password: ""};

export default function TitleScreen():JSX.Element {
    
    const [loginType, setLoginType] = useState('text');
    let placeholder = (loginType==='text')? 'nombre':'contraseña';
    const [loginValue, setLoginValue] = useState('');
    const [errorMessage, setErrorMessage] = useState('');
    
    //comprobar la validez del valor de login
    function checkValidity():void {
        if (loginType === 'text') {
            if (loginValue.length <= 20 && loginValue.length >= 3) {
                user.name = loginValue.trim();
                console.log(loginValue);
                console.log(user.name);
                setLoginValue("");
                setLoginType('password');
                setErrorMessage('');
            } else setErrorMessage('nombre inválido');
            return;
        }
        if (loginType === 'password') {
            if (loginValue.length <= 30 && loginValue.length >= 8) {
                user.password = loginValue.trim();
                authenticate(user);
                setLoginValue("");
                setLoginType('text');
            } else 
            setErrorMessage("Nombre o contraseña incorrecta");
            setLoginValue("");
            setLoginType('text');
        }
    }

    //comprobar la autenticidad de los valores de login
    function authenticate(user:{name:string, password: string}):void {
        //enviar el objeto al servidor, que devuelve una página con un objeto cookie o un mensaje de error
        //por ahora, se establece la cookie localmente (1209600=14d)
        if (user.name === tempUser.name && user.password === tempUser.password) {
            console.log(user === tempUser);
            Session.open(user.name);
            window.location.reload();
            return;
        }       
        setErrorMessage("Nombre o contraseña incorrecta");
    }

    return (
        <div className='title-wrapper'>
            <h1>Más que un facturador</h1>
            <h2>facturador++ fue diseñado para facilitar el proceso contable para pequeñas empresas y empresas simuladas.
            </h2>

            <input type={loginType} name='name' placeholder={placeholder} value={loginValue}
            onChange={e=>{setLoginValue(e.target.value)}}
            onKeyPress={(e) => {if (e.key === 'Enter')checkValidity()}}>
            </input>
            <button type='button' onClick={() => checkValidity()}>Iniciar sesión</button>

            <p className='message'>{errorMessage}</p>
        </div>
    );
}