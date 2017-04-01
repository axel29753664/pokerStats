angular.module("mainApp")
    .factory("gameService", function ($http, $q) {
        return {
            convertGameToTableFormat: function (game, allPlayers) {
                var formatGame = {
                    id: game.id,
                    date: game.date,
                    playersPlaces: []
                };

                for (var i = 0; i < allPlayers.length; i++) {
                    var playerPlace = this.getPlayerPlaceFromGame(allPlayers[i], game);
                    if (playerPlace == null) {
                        playerPlace = {player: allPlayers[i], place: 0};
                    }
                    formatGame.playersPlaces.push(playerPlace);
                }
                return formatGame;
            },
            getPlayerPlaceFromGame: function (player, game) {
                var playerPlaces = game.playersPlaces;
                for (var i = 0; i < playerPlaces.length; i++) {
                    if (angular.equals(player, playerPlaces[i].player)) {
                        return playerPlaces[i];
                    }
                }
                return null;
            },
            prepareGame: function (game) {
                var playersPlaces = game.playersPlaces;
                for (var i = 0; i < playersPlaces.length; i++) {
                    if ((playersPlaces[i].place == undefined) || (playersPlaces[i].place == "0")) {
                        playersPlaces.splice(i, 1);
                        i--;
                    }
                }
                return game;
            },
            sendGameToServer: function (game) {
                this.prepareGame(game);
                var deferred = $q.defer();
                if (game.id == undefined) {
                    $http.post("api/auth/addGame", game).then(success, error);
                } else {
                    $http.post("api/auth/admin/updateGame", game).then(success, error);
                }

                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    console.warn("Error sending game to server");
                    console.warn(err);
                }

                return deferred.promise;
            },
            prepareIdsArr: function (ids) {
                for (var i = 0; i < ids.length; i++) {
                    if (ids[i] == undefined) {
                        ids.splice(i, 1);
                        i--;
                    }
                }
            },
            deleteGames: function (ids) {
                this.prepareIdsArr(ids);
                var deferred = $q.defer();
                $http.post("api/auth/admin/deleteGames", ids).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    console.warn("Error deleting game");
                    console.warn(err);
                }

                return deferred.promise;
            }
        }
    });