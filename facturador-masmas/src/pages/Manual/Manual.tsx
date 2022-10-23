import React from 'react';
import { Link, Navigate, Route, Routes } from 'react-router-dom';
import {Main} from "components/wrappers";
import { NavBar } from 'components/standalone';
import { BiPowerOff } from 'react-icons/bi';
import Sitemap from "./components/Sitemap";
import Description from "./components/Description";


const tabs = [
    {path: "/descripcion",  label:'Descripción de la aplicación'},
    {path: "/mapa",         label:'Mapa del sitio'},
   /*  {path: "/tareas",       label:'Tareas comunes'},
    {path: "/problemas",    label:'Problemas comunes'}, */
]

export default function Manual() {
    return <Main menu={<NavBar tabs={tabs} />} content={
        <div className='manual'>
        <Link to="/" title='Cerrar'><BiPowerOff /></Link>
        <Routes>
            <Route index element={<Navigate to={'./descripcion'}/>}             />

            <Route path={"descripcion/*"}  element={<Description/>}                  />
            <Route path={"mapa/*"}         element={<Sitemap/>}                  />
            <Route path="*"                element={<Navigate to={"/manual"} />}/>
        </Routes>
        </div>
    } />
}