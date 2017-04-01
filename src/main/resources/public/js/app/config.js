var app = angular.module("mainApp", ['ngRoute']);

app.config(function ($routeProvider, $httpProvider) {
    $routeProvider
        .when("/", {
            templateUrl: "stats.html",
            controller: "statsController",
            controllerAs:"statsCtrl"
        })
        .when('/history', {
            templateUrl: 'history.html',
            controller: 'historyController',
            controllerAs: 'historyCtrl'
        })
        .when('/login', {
            templateUrl: 'login.html',
            controller: 'loginController',
            controllerAs: 'loginCtrl'
        })
        .otherwise({
            template: "<h1>Wrong url</h1>"
        });
    $httpProvider.defaults.headers.common["X-Requested-With"] = 'XMLHttpRequest';

});

