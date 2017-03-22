app = angular.module("mainApp");
app.directive("addPlayer", function () {
    return {
        restrict: "E",
        templateUrl: "addPlayerModal.html"

    }

});
app.directive("addGame", function () {
    return {
        restrict: "E",
        templateUrl: "editGameModal.html"

    };

});
