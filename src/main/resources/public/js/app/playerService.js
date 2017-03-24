angular.module("mainApp")
    .factory("playerService", function ($http, $q, ratedPlacesService) {
        return {
            getPlayersRating: function (stats) {
                var self = this;
                var playersRating = self.initPlayerRating(stats.allPlayers);
                ratedPlacesService.getRatedPlaces().then(success);
                function success(allRatedPlaceQuantity) {
                    var games = stats.games;
                    for (var i = 0; i < games.length; i++) {
                        var playersPlaces = games[i].playersPlaces;
                        var ratedPlaceQuantityInGame = self.getRatedPlaceQuantityInGame(playersPlaces, allRatedPlaceQuantity);
                        for (var j = 0; j < playersPlaces.length; j++) {
                            if ((playersPlaces[j].place > 0) && (playersPlaces[j].place <= ratedPlaceQuantityInGame)) {
                                playersRating[playersPlaces[j].player.name] += playersPlaces.length / playersPlaces[j].place;
                            }
                        }
                    }
                }

                return playersRating;
            },
            initPlayerRating: function (players) {
                var playersRating = {};
                for (var a = 0; a < players.length; a++) {
                    playersRating[players[a].name] = 0;
                }
                return playersRating;
            },
            getRatedPlaceQuantityInGame: function (playersPlaces, allRatedPlace) {
                if (playersPlaces.length in allRatedPlace) {
                    return allRatedPlace[playersPlaces.length];
                } else {
                    return 0;
                }
            },

            getPlayersAveragePlaces: function (games) {

                if (games.length != 0) {
                    var playersAveragePlaces = angular.copy(games[0].playersPlaces);
                } else {
                    return;
                }
                for (var a = 0; a < playersAveragePlaces.length; a++) {
                    if (playersAveragePlaces[a].place != 0) {
                        playersAveragePlaces[a].gamePlayed = 1;
                    } else {
                        playersAveragePlaces[a].gamePlayed = 0;
                    }
                }
                for (var i = 1; i < games.length; i++) {
                    var playersPlaces = games[i].playersPlaces;
                    for (var j = 0; j < playersPlaces.length; j++) {
                        if (playersPlaces[j].place != 0) {
                            playersAveragePlaces[j].place += playersPlaces[j].place;
                            playersAveragePlaces[j].gamePlayed++;
                        }
                    }
                }
                for (var k = 0; k < playersAveragePlaces.length; k++) {
                    if (playersAveragePlaces[k].gamePlayed != 0) {
                        playersAveragePlaces[k].place = playersAveragePlaces[k].place / playersAveragePlaces[k].gamePlayed;
                    }
                }

                return playersAveragePlaces;
            },
            sendPlayerToServer: function (player) {
                var deferred = $q.defer();
                $http.post("api/addPlayer", player).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    console.warn("Error sending player to server");
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
            deletePlayers: function (ids) {
                this.prepareIdsArr(ids);
                var deferred = $q.defer();
                $http.post("api/deletePlayers", ids).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    console.warn("Error deleting player");
                    console.warn(err);
                }

                return deferred.promise;
            }
        }
    });