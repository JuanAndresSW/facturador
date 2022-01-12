import React from 'react';
import {Link} from 'react-router-dom';
import Headroom from 'react-headroom';
import './Header.css';
import logo from '../../asset/vector/logo.svg';
import { getCookie } from '../../script/cookies';
import CommandLine from './CommandLine/CommandLine';
import ProfileMenu from './ProfileMenu/ProfileMenu';

const notLoggedHeader = (
  <div id='header-links'>
    <a href='about:blank' target='_blank'>Aplicación móvil</a>
    <Link to={'/LogIn'} target='_blank'>Ingresar</Link>
    <button type='button'><Link to={'/signup'}>Crea una cuenta</Link></button>
  </div>
);
const loggedHeader = (
  <div id='logged-header'>
  <CommandLine /><ProfileMenu />
  </div>
);

export default function Header() {

  return (
    <Headroom>
    <header>
        <div id="logo"><img src={logo} alt='' /><p>facturador++</p></div>
        { (getCookie('username')==="") ? notLoggedHeader:loggedHeader }
    </header>
    </Headroom>
  )
}