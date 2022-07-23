import React from "react";
import { BiCaretLeft, BiCaretRight } from "react-icons/bi";
import './Pagination.css';

type props = {
    page: number,
    setPage: Function,
    totalPages: number,
    last: boolean,
}

export default function Pagination({page,setPage, totalPages, last}:props): JSX.Element {
    return (
        <menu data-pagination>

            <BiCaretLeft 
            onMouseDown={page===0?null:()=>setPage(page--)}
            className={page===0?"unactive":null}/>

            <div><p>{page+1}/{totalPages}</p></div>

            <BiCaretRight
            onMouseDown={last?null:()=>setPage(page++)}
            className={last?"unactive":null}/>
        </menu>
    );
}