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
// app.directive('playerPlace', function (gameService) {
//     return {
//         restrict: 'E',
//         scope: {
//             player: '=',
//             game: '='
//         },
//         template: "<div>{{playerPlace.place}}</div>",
//
//         link: function (scope) {
//             scope.playerPlace = gameService.getPlayerPlaceInGame(scope.player, scope.game);
//         }
//
//     }
// });
});
