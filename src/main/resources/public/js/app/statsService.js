angular.module("mainApp")
    .factory("statsService", function ($http, $q, urls, values) {
        return {
            getPlayerPlaceInGame: function (player, game) {
                var playerPlace = {player: player, place: values.ZERO};
                var playersPlaces = game.playersPlaces;
                for (var i = 0; i < playersPlaces.length; i++) {
                    if (angular.equals(player, playersPlaces[i].player)) {
                        playerPlace = playersPlaces[i];
                        break;
                    }
                }
                if (playerPlace.place == values.ZERO) {
                    game.playersPlaces.push(playerPlace)
                }
                return playerPlace;
            },

            getStatsFromServer: function () {
                var deferred = $q.defer();

                deferred.resolve(testStatsModel);
                return deferred.promise;

                // $http.get(urls.GET_STATS).then(success, error);
                // function success(response) {
                //     deferred.resolve(response.data);
                // }
                //
                // function error(err) {
                //     deferred.reject(err);
                // }
                //
                // return deferred.promise;
            },
            sendPlayerToServer: function (player) {
                var deferred = $q.defer();
                $http.post(urls.SAVE_PLAYER, player).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
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

            deleteGames: function (games) {
                var deferred = $q.defer();
                $http.post(urls.DELETE_GAMES, games).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
            },

            sendGameToServer: function (game) {
                this.prepareGame(game);
                var deferred = $q.defer();
                if (game.id == undefined) {
                    $http.post(urls.SAVE_GAME, game).then(success, error);
                } else {
                    $http.post(urls.UPDATE_GAME, game).then(success, error);
                }

                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    deferred.reject(err);
                }

                return deferred.promise;
            }
        }
    });
