import React, { Suspense, lazy } from 'react';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import ReactDOM from 'react-dom';
import reportWebVitals from './reportWebVitals';
import FallBack from './component/FallBack/FallBack.jsx';
import './global-style/normalize.css';
import './global-style/outer.css';
//importar diferidamente los contenedores
const Home = lazy(() => import('./container/Home.js'));
const Start = lazy(() => import('./container/Start.js'));
const Stats = lazy(() => import('./container/Stats.js'));
const SignUp = lazy(() => import('./container/SignUp.js'));
const LogIn = lazy(() => import('./container/LogIn.js'));
const Account = lazy(() => import('./container/Account.js'));
const About = lazy(() => import('./container/About.js'));
const Error404 = lazy(() => import('./container/Error404.js'));
//mostrar el contenedor indicado
ReactDOM.render(
  <Router forceRefresh={true}>
    <Suspense fallback={<FallBack />}>
      <Routes>
        <Route exact path="/" element={<Home />} />
        <Route path="/start" render={<Start />}/>
        <Route path="/stats" element={<Stats />}/>
        <Route path="/signup" element={<SignUp />}/>
        <Route path="/account" element={<Account />}/>
        <Route path="/about" element={<About />}/>
        <Route path="*" element={<Error404 />}/>
      </Routes>
    </Suspense>
  </Router>,
  document.getElementById('root')
);
reportWebVitals(console.log);

export default function isUserAuthenticated() {
  return false;
}