//define a gateway to data needed to display the document form component
var FormDataClass = /** @class */ (function () {
    function FormDataClass() {
    }
    //get list of points of sell, third parties and groups from the server, store on session
    FormDataClass.prototype.fetchFormData = function () {
        if (sessionStorage.getItem("pointsOfSale") === null) {
            sessionStorage.setItem("pointsOfSale", JSON.stringify([
                {
                    id: 2,
                    name: "punto1",
                    address: "calle1",
                },
                {
                    id: 1,
                    name: "punto2",
                    address: "calle2",
                }
            ]));
            sessionStorage.setItem("thirdParties", JSON.stringify([
                {
                    id: 2,
                    name: "empresa",
                    vatType: "monotributista",
                },
                {
                    id: 1,
                    name: "persona",
                    address: "consumidor-final",
                }
            ]));
        }
        return;
    };
    //get array of points of sale objects to build <select> element's children
    FormDataClass.prototype.getPointsOfSale = function () {
        return JSON.parse(sessionStorage.getItem("pointsOfSale"));
    };
    //get array of third party objects to build <select> element's children
    FormDataClass.prototype.getThirdParties = function () {
        return JSON.parse(sessionStorage.getItem("thirdParties"));
    };
    //check if string corresponds with any item's position's name, get index of the matching name's array position
    FormDataClass.prototype.getIndexIfExists = function (searchTerm, item) {
        JSON.parse(sessionStorage.getItem(item)).forEach(function (party, index) {
            if (party.name.toLowerCase() === searchTerm.toLowerCase().trim())
                return index;
        });
        return -1;
    };
    return FormDataClass;
}());
var FormData = new FormDataClass();
export default FormData;
