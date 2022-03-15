import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import { BiChevronDown, BiChevronUp, BiUserCircle } from 'react-icons/bi';
import OutsideClickHandler from 'react-outside-click-handler';

import Session from "services/Session";
import UserAvatar from 'services/UserAvatar';

import defaultImg from 'assets/img/punto.jpg';
import './ProfileMenu.css';



export default function ProfileMenu(): JSX.Element {
    //Imágen de avatar de usuario.
    const [img, setImg] = useState(defaultImg);

    //Pedir la imágen en el primer renderizado.
    useEffect(() => {
        UserAvatar.retrieve((HTTPState: number, blob: File) => {
            if (HTTPState === 200) setImg(URL.createObjectURL(blob));
        });
    }, []);

    //Navegación al cerrar sesión.
    function logOut(): void {
        Session.close();
        window.location.reload();
    }

    //Controlador de estado del menú.
    const [active, setActive] = useState(false);

    return (
        <OutsideClickHandler onOutsideClick={() => setActive(false)}>
            <div id="profile-menu" onMouseDown={() => setActive(!active)}>

                <img src={img}></img>

                {active ? <BiChevronUp /> : <BiChevronDown />}
            </div>

            <div id="profile-menu-list" className={active ? 'extended' : ''}>

            <p>{sessionStorage.getItem("username")?
                sessionStorage.getItem("username") + ': '
                : "???"}
            </p>
            
                <ul>
                    <li><NavLink to="/cuenta">Configuración</NavLink></li>
                    <li onMouseDown={() => logOut()}>Cerrar sesión</li>
                </ul>
            </div>
        </OutsideClickHandler>
    )
}