import React from 'react';
import {Link} from 'react-router-dom';
import Headroom from 'react-headroom';
import './Header.css';
import logo from '../../asset/vector/logo.svg';
import isUserAuthenticated from '../../index';

const header = (
  <>
  <Link to={'/LogIn'} target='_blank'>Ingresar</Link>
  <button type='button'><Link to={'/signup'}>Crea una cuenta</Link></button>
  </>
);

export default function Header() {

  return (
    <Headroom>
    <header>
        <div id="logo"><img src={logo} alt='Facturador++' /><p>facturador++</p></div>
        { 
          isUserAuthenticated() ? <input type={"search"} placeholder=''></input> : 
          <div id='header-links'>
            <a href='about:blank' target='_blank'>Aplicación móvil</a>
            <Link to={'/LogIn'} target='_blank'>Ingresar</Link>
            <button type='button'><Link to={'/signup'}>Crea una cuenta</Link></button>
          </div>
        }
    </header>
    </Headroom>
  )
}