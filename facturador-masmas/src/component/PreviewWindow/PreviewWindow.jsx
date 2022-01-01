import React from 'react';
import './PreviewWindow.css';
import { TabbedPane } from './TabbedPane/TabbedPane';
import DocumentGroup from '../DocumentForms/DocumentGroup';
import libro from '../../asset/img/libro.jpg';
import stats from '../../asset/img/stats.jpg';
export default function PreviewWindow() {
  return (
    <div className='tab-div'>
      <TabbedPane> 
        <div label="Documento"><DocumentGroup /></div>
        <div label="Libro"><img src={libro} alt="libro"></img></div>
        <div label="Estadística"><img src= {stats} alt="stats" /></div>
      </TabbedPane>
    </div>
  );
}