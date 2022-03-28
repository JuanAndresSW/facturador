import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import { BiChevronDown, BiChevronUp } from 'react-icons/bi';
import OutsideClickHandler from 'react-outside-click-handler';

import closeSession from "services/closeSession";
import getUserAvatar from '../../../services/getUserAvatar';

import defaultImg from 'assets/svg/user.svg';
import './ProfileMenu.css';



export default function ProfileMenu(): JSX.Element {
    //Imágen de avatar de usuario.
    const [img, setImg] = useState(defaultImg);

    //Pedir la imágen en el primer renderizado.
    useEffect(() => {
        getUserAvatar((ok:boolean, blob: File) => {
            if (ok) setImg(URL.createObjectURL(blob));
        });
    }, []);

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
                    <li onMouseDown={closeSession}>Cerrar sesión</li>
                </ul>
            </div>
        </OutsideClickHandler>
    )
}