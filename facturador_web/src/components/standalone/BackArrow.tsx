import React from "react";
import {BiChevronLeft} from "react-icons/bi";
import { useNavigate } from "react-router-dom";

export default function BackArrow() {
    const navigate = useNavigate();
    return (
        <BiChevronLeft onClick={() => navigate(-1)} style={{fontSize:"2rem", color:"rgb(44,44,44)",cursor:"pointer"}} />
    );
}