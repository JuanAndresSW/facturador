import React from "react";
import ReactDOMClient from "react-dom/client";
import { HashRouter } from "react-router-dom";
import App from './App';
import './index.css';

const root = document.getElementById('root')!;

ReactDOMClient.createRoot(root).render(
  <React.StrictMode>
    <HashRouter>
      <App />
    </HashRouter>
  </React.StrictMode>
);