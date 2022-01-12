import React, { useRef, useState } from "react";
import {BiChevronDown, BiChevronUp} from 'react-icons/bi';
import { clearCookies, getCookie } from "../../../script/cookies";
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';
import profilePicture from '../../../asset/vector/solucion.svg'

export default function ProfileMenu():JSX.Element {
    const [active, setActive] = useState(false);
    const wrapperRef = useRef(null);
    return (
        <>
        <OutsideClickHandler onOutsideClick={()=>setActive(false)}>
        <div id="profile-menu" onMouseDown={()=>setActive(!active)}>
            <img src={profilePicture}></img>{active?<BiChevronUp />:<BiChevronDown />}
        </div>

        <div id="profile-menu-list" className={active?'active':''} ref={wrapperRef}>
            <ul>
                <li>{getCookie('username')+`: `}</li>
                <li>Configuración</li>
                <li onMouseDown={()=>logOut()}>Cerrar sesión</li>
            </ul>
        </div>
        </OutsideClickHandler>
        </>
    )
}

function logOut():void {
    clearCookies();
    window.location.reload();
}