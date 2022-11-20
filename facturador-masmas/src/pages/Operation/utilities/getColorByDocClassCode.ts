import { documentClassCode } from "../models/operation";


/**Devuelve un color correspondiente a un documento comercial.*/
export default function getColorByDocClassCode(cc: (documentClassCode | "default")): string {

    const documentColors = {
        Fa: "#fa1",
        Nc: "#285",
        Nd: "hotpink",
        Tk: "black",
        Oc: "#248",
        Rm: "#841",
        Rx: "purple",
        Rs: "#299",
        Pa: "#842",
        Ch: "green",
        default: "#999"
    }

    return documentColors[cc] ?? documentColors.default;
}