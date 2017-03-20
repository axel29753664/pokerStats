angular.module("mainApp")
    .controller("loginController", function ($rootScope, $http, $location) {
        var self = this;
        self.credentials = {};

        var authenticate = function (credentials, callback) {

            var headers = credentials ? {
                authorization: "Basic "
                + btoa(credentials.username + ":" + credentials.password)
            } : {};

            $http.get('user', {headers: headers}).then(function (response) {
                console.log("login");
                if (response.data.name) {
                    $rootScope.authenticated = true;
                    $rootScope.adminAccess = false;
                    var authorities = response.data.authorities;
                    for (var i = 0; i < authorities.length; i++) {
                        if (authorities[i].authority == "ROLE_ADMIN") {
                            $rootScope.adminAccess = true;
                        }
                    }
                } else {
                    $rootScope.authenticated = false;
                    $rootScope.adminAccess = false;
                }
                callback && callback();
            }, function (error) {
                $rootScope.authenticated = false;
                $rootScope.adminAccess = false;
                callback && callback();
            });

        };

        authenticate();

        self.login = function () {
            authenticate(self.credentials, function () {
                if ($rootScope.authenticated) {
                    $location.path("/");
                    self.error = false;
                } else {
                    $location.path("/login");
                    self.error = true;
                }
            });
        };
        self.logout = function () {
            $http.post('logout', {}).finally(function () {
                $rootScope.authenticated = false;
                $rootScope.adminAccess = false;
                $location.path("/");
            });
        }

    });
