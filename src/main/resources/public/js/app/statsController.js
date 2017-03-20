angular.module("mainApp")
    .controller("statsController", function ($scope, statsService, playerService, gameService) {
        $scope.deleteButton = "default";
        $scope.deletePlayersButton = "default";
        $scope.deleteGameTrigger = false;
        $scope.deletePlayersTrigger = false;
        $scope.noPlaceValue = "-";
        init();

        function init() {

            $scope.stats="";

            statsService.getStatsFromServer().then(function success(stats) {

                    var formatStats = statsService.convertStatsToTableFormat(stats);
                    $scope.stats = formatStats;
                    $scope.playersAveragePlaces = playerService.getPlayersAveragePlaces(formatStats.games);

                },
                function error(error) {
                    alert(error.data)
                });
        }

        $scope.addPlayer = function (player) {
            playerService.sendPlayerToServer(player).then(success, error);
            function success() {
                init();
            }

            function error(err) {
                alert(err);
            }

        };
        $scope.deleteGames = function (ids) {

            if ($scope.deleteButton == "default") {
                $scope.deleteButton = "danger";

            } else {
                gameService.deleteGames(ids).then(success, error);
                function success() {
                    init();
                }

                function error(err) {
                    alert(err);
                }

                $scope.deleteButton = "default";
            }

            this.deleteGameTrigger = !this.deleteGameTrigger;
            $scope.deletedGamesIds = [];

        };
        $scope.cancelDeleteGames = function () {
            $scope.deletedGamesIds = [];
            $scope.deleteButton = "default";
            this.deleteGameTrigger = false;
        };
        $scope.deletePlayers = function (ids) {

            if ($scope.deletePlayersButton == "default") {
                $scope.deletePlayersButton = "danger";

            } else {
                playerService.deletePlayers(ids).then(success, error);
                function success() {
                    init();
                }

                function error(err) {
                    alert(err);
                }

                $scope.deletePlayersButton = "default";
            }

            this.deletePlayersTrigger = !this.deletePlayersTrigger;
            $scope.deletedPlayersIds = [];

        };
        $scope.cancelDeletePlayers = function () {
            $scope.deletedPlayersIds = [];
            $scope.deletePlayersButton = "default";
            this.deletePlayersTrigger = false;
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

            gameService.sendGameToServer(game).then(success, error);
            function success() {
                init();
            }

            function error(err) {
                alert(err);
            }

        };

    });
