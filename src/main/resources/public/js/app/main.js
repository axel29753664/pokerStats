var app = angular.module("mainApp", ['ngRoute']);

app.config(function ($routeProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "stats.html",
            controller: "statsController"
        })
        .otherwise({
            template: "<h1>Wrong url</h1>"
        })
});

app.constant(
    "urls", {
        HOME: "http://192.168.1.104:8080/",
        API: "http://192.168.1.104:8080/api/",
        GET_STATS: "http://192.168.1.104:8080/api/getStats",
        SAVE_PLAYER: "http://192.168.1.104:8080/api/addPlayer",
        SAVE_GAME: "http://192.168.1.104:8080/api/addGame",
        UPDATE_GAME: "http://192.168.1.104:8080/api/updateGame",
        DELETE_GAMES:"http://192.168.1.104:8080/api/deleteGames"
    }
);
app.constant(
    "values", {
        ZERO: 0
    }
);