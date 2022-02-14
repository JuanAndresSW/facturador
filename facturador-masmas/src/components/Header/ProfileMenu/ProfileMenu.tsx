import React, { useState } from "react";
import {BiChevronDown, BiChevronUp} from 'react-icons/bi';
import Session from "utils/Session";
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';
import profilePicture from 'assets/svg/solucion.svg'

export default function ProfileMenu():JSX.Element {
    const [active, setActive] = useState(false);
    return (
        <>
        <OutsideClickHandler onOutsideClick={()=>setActive(false)}>
        <div id="profile-menu" onMouseDown={()=>setActive(!active)}>
            <img src={profilePicture}></img>{active?<BiChevronUp />:<BiChevronDown />}
        </div>

        <div id="profile-menu-list" className={active?'active':''}>
            <ul>
                <li>{Session.getUsername()+': '}</li>
                <li>Configuración</li>
                <li onMouseDown={()=>logOut()}>Cerrar sesión</li>
            </ul>
        </div>
        </OutsideClickHandler>
        </>
    )
}

function logOut():void {
    Session.close();
    window.location.reload();
}