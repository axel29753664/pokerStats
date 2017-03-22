angular.module("mainApp")
    .factory("playerService", function ($http, $q) {
        return {
            getPlayersAveragePlaces: function (games) {

                if (games.length !=0) {
                    var playersAveragePlaces = angular.copy(games[0].playersPlaces);
                }else {
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
                    deferred.reject(err);
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
                    deferred.reject(err);
                }

                return deferred.promise;
            }
        }
    });