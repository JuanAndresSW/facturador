/**
 * get data needed to display the document form component;
 * save data gathered in the form to be sent to the server;
 */

export default class DocData {

    /**DATA DISPLAY METHODS***********************************************************/

    //request list of points of sell, third parties and groups from the server, store on session
    //get other properties to display the form: allowAB
    public static fetchFormData(): void {
        if (sessionStorage.getItem("points-of-sale") === null) {
            sessionStorage.setItem("points-of-sale", JSON.stringify([
                {
                    id: 2,
                    name: "punto1",
                    address: "calle1"
                },
                {
                    id: 1,
                    name: "punto2",
                    address: "calle2"
                }
            ]));
            sessionStorage.setItem("third-parties", JSON.stringify([
                {
                    id: 2,
                    name: "empresa",
                    vatType: "monotributista"
                },
                {
                    id: 1,
                    name: "persona",
                    vatType: "consumidor final"
                }
            ]));
            sessionStorage.setItem("groups", JSON.stringify([
                {
                    id: 2,
                    name: "grupo A",
                    members: 22
                },
                {
                    id: 1,
                    name: "grupo B",
                    members: 12
                }
            ]));
            sessionStorage.setItem("allowAB", "1");
        }
        return;
    }


    //get array of objects to build <select> element's children
    public static getPointsOfSale(): { id: number; name: string; address: string; }[] {
        return JSON.parse(sessionStorage.getItem("points-of-sale"));
    }
    public static getThirdParties(): { id: number; name: string; vatType: string; }[] {
        return JSON.parse(sessionStorage.getItem("third-parties"));
    }
    public static getGroups(): { id: number; name: string; members: number; }[] {
        return JSON.parse(sessionStorage.getItem("groups"));
    }

    //check if document type A and B should be allowed on forms
    public static allowAB = (): boolean => {
        return !!parseInt(sessionStorage.getItem("allowAB"));
    }


    //check if string corresponds with any item's position's name, get index of the matching name's array position
    //needed to know if search term is to be accepted or ignored
    public static getIndexIfExists(searchTerm: string, item: string): number {
        const matches = (element: { name: string }) =>
            element.name.toLowerCase() === searchTerm.toLowerCase().trim();

        return JSON.parse(sessionStorage.getItem(item)).findIndex(matches);
    }


    /**DATA STORAGE METHODS***********************************************************/

    public static setPointOfSale(index: number): void {
        let data = JSON.parse(sessionStorage.getItem("doc-data"));
        data.pointOfSale = this.getPointsOfSale()[index].id;
        sessionStorage.setItem("doc-data", JSON.stringify(data));
    }
    public static setThirdParty(index: number): void {
        console.log("huh")
        let data = JSON.parse(sessionStorage.getItem("doc-data"));
        data.thirdParty = this.getThirdParties()[index].id;
        sessionStorage.setItem("doc-data", JSON.stringify(data));
    }
    public static setGroup(index: number): void {
        if (index !== -1) {
            let data = JSON.parse(sessionStorage.getItem("doc-data"));
            data.group = this.getGroups()[index].id;
            sessionStorage.setItem("doc-data", JSON.stringify(data));
        }
    }

    //set default values for fields on render
    public static initializeValues() {
        if(sessionStorage.getItem("doc-data") === null)
        sessionStorage.setItem("doc-data", JSON.stringify({
            pointOfSale: this.getPointsOfSale()[0].id,
            thirdParty: this.getThirdParties()[0].id
        }))
    }
}