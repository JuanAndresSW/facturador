import React from 'react';
import { Link, Navigate, Route, Routes } from 'react-router-dom';
import {FlexDiv, Main} from "components/wrappers";
import { NavBar } from 'components/standalone';
import { BiPowerOff } from 'react-icons/bi';
import Sitemap from "./components/Sitemap";
import Description from "./components/Description";


const tabs = [
    {path: "/descripcion",  label:'Descripción de la aplicación'},
    {path: "/mapa",         label:'Mapa del sitio'}
]

export default function Manual() {
    return <Main menu={<NavBar tabs={tabs} />} content={

        <div className='manual'>

            <Link to="/" title='Cerrar' style={{margin:"1rem", display: "block"}} >↩️ Cerrar</Link>

            <Routes>
                <Route index element={<Navigate to={'./descripcion'}/>}             />

                <Route path={"descripcion/*"}  element={<Description/>}                  />
                <Route path={"mapa/*"}         element={<Sitemap/>}                  />
                <Route path="*"                element={<Navigate to={"/manual"} />}/>
            </Routes>
        </div>

    } />
}