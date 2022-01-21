import React, { useRef, useState } from "react";
import {BiChevronDown, BiChevronUp} from 'react-icons/bi';
import Session from "../../../script/Session";
import OutsideClickHandler from 'react-outside-click-handler';
import './ProfileMenu.css';
import profilePicture from '../../../asset/svg/solucion.svg'


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
                <li>{Session.getUsername()+`: `}</li>
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