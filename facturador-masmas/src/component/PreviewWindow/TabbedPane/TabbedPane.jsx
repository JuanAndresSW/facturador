import React from 'react';
import { useState } from 'react';
import './TabbedPane.css';

export function TabbedPane ({children}) {
  const [currentTab, setCurrentTab] = useState(children[0].props.label);
  const handleTabSwitch = (newActiveTab) => {
    setCurrentTab(newActiveTab);
  }

  return (
    <div className='tab-set'>
        <ul>
            {
            children.map(tab => (
            <li className={tab.props.label === currentTab ? 'current-tab' : ''} key={tab.props.label}
                onMouseDown={() => {handleTabSwitch(tab.props.label)}}>{tab.props.label}
            </li>
            ))
            }
        </ul>

        {
        children.map(tab => {
            if (tab.props.label === currentTab) {
                return (<div className={'tab-content'} key={tab.props.label}>{tab.props.children}</div>)
            }
        })
        }
    </div>
  );
}