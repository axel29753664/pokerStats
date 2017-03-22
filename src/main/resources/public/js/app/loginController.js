angular.module("mainApp")
    .controller("loginController", function ($rootScope, $http, $location, loginService) {
        var self = this;
        self.credentials = {};

        loginService.authenticate();

        self.login = function () {
            loginService.authenticate(self.credentials).then(success, error);
            function success() {
                self.error = false;
                $location.path("/");
            }

            function error(error) {
                self.error = true;
                $location.path("/login");
            }
        }
    })
;
