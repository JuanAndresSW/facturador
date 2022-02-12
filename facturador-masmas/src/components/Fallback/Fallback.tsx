import React from 'react';
import './Fallback.css';

/**
 * Una pantalla de carga para cuando no es posible mostrar otro componente.
 */
export default function Fallback() {

    return (
        <div className='fallback'>
            <div></div>
        </div>
    );
}