angular.module("mainApp")
    .controller("statsController", function ($scope, statsService, $filter) {
        $scope.deleteButton = "default";
        $scope.deleteGameTrigger = false;
        init();

        function init() {
            statsService.getStatsFromServer().then(function success(stats) {
                    $scope.stats = stats;
                },
                function error(error) {
                    alert(error)
                });
        }

        $scope.addPlayer = function (player) {
            statsService.sendPlayerToServer(player).then(success, error);
            function success() {
                init();
            }

            function error(err) {
                alert(err);
            }

        };
        $scope.deleteGames = function (games) {

            if ($scope.deleteButton == "default") {
                $scope.deleteButton = "danger";

            } else {
                statsService.deleteGames(games).then(success, error);
                function success() {
                    init();
                }

                function error(err) {
                    alert(err);
                }
                $scope.deleteButton = "default";
            }

            this.deleteGameTrigger = !this.deleteGameTrigger;
            $scope.deletedGames = [];

        };
        $scope.cancelDeleteGames = function () {
            $scope.deletedGames = [];
            $scope.deleteButton = "default";
            this.deleteGameTrigger = false;
        };

        $scope.createNewGame = function (players) {
            var newGame = {};
            newGame.date = new Date();
            newGame.playersPlaces = [];
            for (var i = 0; i < players.length; i++) {
                newGame.playersPlaces.push({player: players[i]});
            }
            $scope.newGame = newGame;
        };
        $scope.editGame = function (game) {
            $scope.newGame = game;
            $scope.newGame.date = new Date(game.date);
        };
        $scope.saveGame = function (game) {

            statsService.sendGameToServer(game).then(success, error);
            function success() {
                init();
            }

            function error(err) {
                alert(err);
            }

        };

    });
