angular.module("mainApp")
    .factory("statsService", function ($http, $q, gameService) {
        return {
            convertStatsToTableFormat: function (stats) {
                var games = stats.games;
                var formatStats = {games: [], allPlayers: []};
                formatStats.allPlayers = stats.allPlayers;

                for (var i = 0; i < games.length; i++) {
                    var game = gameService.convertGameToTableFormat(games[i], stats.allPlayers);
                    formatStats.games.push(game);
                }
                return formatStats;
            },

            getStatsFromServer: function () {
                var deferred = $q.defer();

                $http.get("api/getStats").then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    console.warn("Error getting stats from server");
                    console.warn(err);
                }

                return deferred.promise;
            }

        }
    });
