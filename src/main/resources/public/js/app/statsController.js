angular.module("mainApp")
    .controller("statsController", function ($scope, statsService, playerService, gameService, loginService, ratedPlacesService) {
        $scope.deleteButton = "default";
        $scope.deletePlayersButton = "default";
        $scope.deleteGameTrigger = false;
        $scope.deletePlayersTrigger = false;
        $scope.noPlaceValue = "-";
        init();

        function init() {
            loginService.authenticate();
            $scope.stats = undefined;
            $scope.ratedPlaces = undefined;

            statsService.getStatsFromServer().then(function success(stats) {

                var formatStats = statsService.convertStatsToTableFormat(stats);
                $scope.stats = formatStats;
                $scope.playersAveragePlaces = playerService.getPlayersAveragePlaces(formatStats.games);

                ratedPlacesService.getRatedPlacesFromServer().then(success);
                function success(ratedPlaces) {
                    $scope.ratedPlaces = ratedPlaces;
                    $scope.playersRating = playerService.getPlayersRating(stats, ratedPlaces);
                }

            });

        }

        $scope.addPlayer = function (player) {
            playerService.sendPlayerToServer(player).then(success);
            function success() {
                init();
            }

        };
        $scope.deleteGames = function (ids) {

            if ($scope.deleteButton == "default") {
                $scope.deleteButton = "danger";

            } else {
                gameService.deleteGames(ids).then(success);
                function success() {
                    init();
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
                playerService.deletePlayers(ids).then(success);
                function success() {
                    init();
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

            gameService.sendGameToServer(game).then(success);
            function success() {
                init();
            }

        };
        $scope.saveRatedPlaces = function (allRatedPlaces) {
            var newRatedPlaces = $scope.newRatedPlaces;
            var err = ratedPlacesService.addNeRatedPlaces(allRatedPlaces, newRatedPlaces);
            if (err != undefined) {
                alert(err);
            }
            ratedPlacesService.sendRatedPlacesToServer(allRatedPlaces).then(success,error);
            function success() {
            }

            function error(err) {
                alert(err);
            }
            $scope.newRatedPlaces = undefined;
            init();
        };

    })
;
