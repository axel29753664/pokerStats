angular.module("mainApp")
    .controller("logoutController", function ($rootScope, $http, $location) {
        var self = this;
        self.logout = function () {
            $http.post('logout', {}).finally(function () {
                $rootScope.authenticated = false;
                $rootScope.adminAccess = false;
                $location.path("/");
            });
        }
    });