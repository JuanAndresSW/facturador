import React from 'react';
import {Link} from 'react-router-dom';
import {DiGithubBadge} from 'react-icons/di';
import './Footer.css';
export default function Footer() {

    return (
        <div className='footer'>
            <div>
                <p>© Conjunto Solución 2022 (GNU V.3)</p>
                <Link to={"/about"}>Acerca de</Link>    
            </div>
            <div>
                <a href="https://github.com/conjunto-solucion/facturador" className='github'><DiGithubBadge /></a>
                <p>facturador++ versión 0</p>
            </div>
        </div>
    );
}