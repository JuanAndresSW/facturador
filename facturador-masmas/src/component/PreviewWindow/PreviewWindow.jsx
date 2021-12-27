import React from 'react';
import './PreviewWindow.css';
import { TabbedPane } from './TabbedPane/TabbedPane';
export default function PreviewWindow() {

  return (
    <>
    <div className='top-wave'></div>
    <div className='tab-div'>

      <TabbedPane> 
        <div label="Documento">PARTE1</div>
        <div label="Libro">PARTE2</div>
        <div label="Estadística">PARTE3</div>
      </TabbedPane>

    </div>
    <div className='bottom-wave'></div>
    </>
  );
}