import React from "react";
import ReactDOMClient from "react-dom/client";
import { HashRouter } from "react-router-dom";
import FacturadorMasMas from './FacturadorMasMas';
import './assets/css/index.css';

const root = document.getElementById('root')!;

ReactDOMClient.createRoot(root).render(
  <React.StrictMode>
    <HashRouter>
      <FacturadorMasMas />
    </HashRouter>
  </React.StrictMode>
);