import React from 'react';
import {Link} from 'react-router-dom';
import './Footer.css';
export default function Footer() {

    return (
        <div className='footer'>
            <div>
                <p>© --- 2022 (GNU V.3)</p>
                <Link to={"/about"}>Acerca de</Link>
            </div>
            <div>
                <a href="https://github.com/JuanAndresSW/facturador"><div className='github'></div></a>
                <p>Facturador++ versión 0</p>
            </div>
        </div>
    );
}