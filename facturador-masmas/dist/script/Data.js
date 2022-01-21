//define a gateway to data needed to display the document form component
var DataClass = /** @class */ (function () {
    function DataClass() {
    }
    //get list of points of sell from the server; store the index of the last used one
    DataClass.prototype.fetchPointsOfSale = function () {
        var _this = this;
        if (this.pointsOfSale[0].id === null) {
            this.pointsOfSale = [
                {
                    id: 1,
                    name: "punto1",
                    address: "calle1",
                    last: false
                },
                {
                    id: 2,
                    name: "punto2",
                    address: "calle2",
                    last: true
                }
            ];
            this.pointsOfSale.forEach(function (point) {
                if (point.last) {
                    _this.lastPointOfSale = point.id;
                }
            });
        }
        return;
    };
    //get array of points of sale objects to build <select> element's children
    DataClass.prototype.getPointsOfSale = function () {
        return this.pointsOfSale;
    };
    //get the last used point of sale to determine default selection
    DataClass.prototype.getLastPointsOfSale = function () {
        return this.lastPointOfSale;
    };
    return DataClass;
}());
var Data = new DataClass();
export default Data;
