app = angular.module("mainApp");
app.directive('playerPlace', function (statsService) {
    return {
        restrict: 'E',
        scope: {
            player: '=',
            game: '='
        },
        template: "<div>{{playerPlace.place}}</div>",

        link: function (scope) {
            scope.playerPlace = statsService.getPlayerPlaceInGame(scope.player, scope.game);
        }

    }
});
app.directive("addPlayer", function () {
    return {
        restrict: "E",
        templateUrl: "addPlayerModal.html"

    }

});
app.directive("addGame", function () {
    return {
        restrict: "E",
        templateUrl: "addGameModal.html"

    }

});
