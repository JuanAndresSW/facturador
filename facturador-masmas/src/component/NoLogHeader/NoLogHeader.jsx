import React from 'react';
import {Link} from 'react-router-dom';
import './NoLogHeader.css';
import logo from '../../asset/vector/logo.svg';

export default function NoLogHeader() {
  return (
    <header>
        <img src={logo} alt='Facturador++' />
        <button type='button'><Link to={'/signup'}>Crea una cuenta</Link></button>
    </header>
  );
}