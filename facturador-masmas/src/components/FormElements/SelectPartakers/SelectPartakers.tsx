import React, { useState } from "react";
import DocData from '../../../script/DocData';
import './SelectPartakers.css';

//return a selection list for points of sale, third party partakers and groups
export default function SelectPartakers(): JSX.Element {

    //define index of default point of sale, third party and group
    DocData.initializeValues();
    const [pointOfSale, setPointOfSale] = useState(0);
    const [thirdParty, setThirdParty] = useState(0);
    const [group, setGroup] = useState(-1); //default is undefined

    //sets the search term as the item value if exists
    function search(searchTerm: string, item: string): void {
        const index = DocData.getIndexIfExists(searchTerm, item);
        if (index !== -1) {
            switch (item) {
                case "third-parties": setThirdParty(index); break;
                case "groups": setGroup(index); break;
            }
        }
    };

    //updates values to send on input value change event
    function handlePointChange(e: React.ChangeEvent<HTMLSelectElement>): void {
        DocData.setPointOfSale(parseInt(e.target.value));
        setPointOfSale(parseInt(e.target.value));
        return;
    };
    function handlePartyChange(e: React.ChangeEvent<HTMLSelectElement>): void {
        DocData.setThirdParty(parseInt(e.target.value));
        setThirdParty(parseInt(e.target.value));
        return;
    };
    function handleGroupChange(e: React.ChangeEvent<HTMLSelectElement>): void {
        DocData.setGroup(parseInt(e.target.value));
        setGroup(parseInt(e.target.value));
        return;
    };

    return (
        <>
            <div className="form-section">
                <label htmlFor="select-point">Punto de venta</label>
                <select className="input-control" value={pointOfSale} id="selec-point" required onChange={e => handlePointChange(e)}>
                    {DocData.getPointsOfSale().map((point, index) => (
                        <option value={index} key={index} title={point.name}>
                            {point.address}
                        </option>
                    ))}
                </select>
            </div>

            <div className="form-section">
                <label htmlFor="select-third-party">Terceros</label>
                <select className="input-control" value={thirdParty} id="select-third-party" required onChange={e => handlePartyChange(e)}>
                    {DocData.getThirdParties().map((party, index) => (
                        <option value={index} key={index} title={party.vatType}>
                            {party.name}
                        </option>
                    ))}
                </select>
                <input className="search-box" type="text"
                    onChange={(e) => search(e.target.value, "third-parties")}
                />


                <label htmlFor="select-group">Grupos</label>
                <select className="input-control" value={group} id="select-group" required onChange={e => handleGroupChange(e)}>
                    <option value={-1}></option>
                    {DocData.getGroups().map((party, index) => (
                        <option value={index} key={index} title={party.members.toString()}>
                            {party.name}
                        </option>
                    ))}
                </select>
                <input className="search-box" type="search"
                    onChange={(e) => search(e.target.value, "groups")}
                />
            </div>
        </>
    )
}