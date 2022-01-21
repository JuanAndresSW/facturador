/**
 * get data needed to display the document form component;
 * save data gathered in the form to be sent to the server;
 */
var DocData = /** @class */ (function () {
    function DocData() {
    }
    /**DATA DISPLAY METHODS***********************************************************/
    //request list of points of sell, third parties and groups from the server, store on session
    DocData.fetchFormData = function () {
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
        }
        return;
    };
    //get array of objects to build <select> element's children
    DocData.getPointsOfSale = function () {
        return JSON.parse(sessionStorage.getItem("points-of-sale"));
    };
    DocData.getThirdParties = function () {
        return JSON.parse(sessionStorage.getItem("third-parties"));
    };
    DocData.getGroups = function () {
        return JSON.parse(sessionStorage.getItem("groups"));
    };
    //check if string corresponds with any item's position's name, get index of the matching name's array position
    //needed to know if search term is to be accepted or ignored
    DocData.getIndexIfExists = function (searchTerm, item) {
        var matches = function (element) {
            return element.name.toLowerCase() === searchTerm.toLowerCase().trim();
        };
        return JSON.parse(sessionStorage.getItem(item)).findIndex(matches);
    };
    /**DATA STORAGE METHODS***********************************************************/
    DocData.setPointOfSale = function (index) {
        var data = JSON.parse(sessionStorage.getItem("doc-data"));
        data.pointOfSale = this.getPointsOfSale()[index].id;
        sessionStorage.setItem("doc-data", JSON.stringify(data));
    };
    DocData.setThirdParty = function (index) {
        console.log("huh");
        var data = JSON.parse(sessionStorage.getItem("doc-data"));
        data.thirdParty = this.getThirdParties()[index].id;
        sessionStorage.setItem("doc-data", JSON.stringify(data));
    };
    DocData.setGroup = function (index) {
        if (index !== -1) {
            var data = JSON.parse(sessionStorage.getItem("doc-data"));
            data.group = this.getGroups()[index].id;
            sessionStorage.setItem("doc-data", JSON.stringify(data));
        }
    };
    //set default values for fields on render
    DocData.initializeValues = function () {
        if (sessionStorage.getItem("doc-data") === undefined)
            sessionStorage.setItem("doc-data", JSON.stringify({
                pointOfSale: this.getPointsOfSale()[0].id,
                thirdParty: this.getThirdParties()[0].id
            }));
    };
    return DocData;
}());
export default DocData;
