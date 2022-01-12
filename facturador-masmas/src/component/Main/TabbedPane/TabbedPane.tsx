import React, { ReactNode } from 'react';
import { useState } from 'react';
import './TabbedPane.css';

type props = {
  children: JSX.Element[]
}

export default function TabbedPane ({children}: props): JSX.Element { 
  const [currentTab, setCurrentTab] = useState(children[0].props.label);
  const handleTabSwitch = (newActiveTab:ReactNode) => {
    setCurrentTab(newActiveTab);
  }

  return (
    <div className='tab-set'>
        <ul>
            {
            children.map((tab:JSX.Element) => (
            <li className={tab.props.label === currentTab ? 'current-tab' : ''} key={tab.props.label}
                onMouseDown={() => {handleTabSwitch(tab.props.label)}}>{tab.props.label}
            </li>
            ))
            }
        </ul>

        {children.map(tab => {
            if (tab.props.label === currentTab)
            return <div className={'tab-content'} key={tab.props.label}>{tab.props.children}</div>
        })}
    </div>
  );
}