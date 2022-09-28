import React, { useEffect, useState } from "react";
import { NavLink } from "react-router-dom";

import { AiOutlineMore } from "react-icons/ai";
import OutsideClickHandler from 'react-outside-click-handler';

import closeSession from "services/closeSession";
import getUserAvatar from '../../../services/getUserAvatar';

import defaultImg from 'assets/svg/user.svg';
import './ProfileMenu.css';
import { Button } from "components/formComponents";



/**Imágen de avatar de usuario con menú de opciones. */
export default function ProfileMenu(): JSX.Element {
    //Imágen de avatar de usuario.
    const [img, setImg] = useState(defaultImg);

    //Pedir la imágen en el primer renderizado.
    useEffect(() => {
        getUserAvatar().then( response => {
            if (response.ok && response.content.size>1) setImg(URL.createObjectURL(response.content));
        });
    });

    //Controlador de estado del menú.
    const [active, setActive] = useState(false);

    return (
        <OutsideClickHandler onOutsideClick={() => setActive(false)}>
            <div id="profile-menu" onMouseDown={() => setActive(!active)}>
                <img src={img}></img><AiOutlineMore/>
            </div>

            <div id="profile-menu-list" className={active ? 'extended' : ''}>

            <p>{sessionStorage.getItem("username")?
                sessionStorage.getItem("username")
                : "???"}
            </p>
            
                <ul>
                    <li><NavLink to="/cuenta">Administrar la cuenta</NavLink></li>
                    <li onClick={closeSession}><Button type="delete">Cerrar sesión</Button></li>
                </ul>
            </div>
        </OutsideClickHandler>
    )
}