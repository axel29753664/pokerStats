angular.module("mainApp")
    .factory("loginService", function ($rootScope, $http, $q, urls) {
            return {
                authenticate: function (credentials) {
                    var deferred = $q.defer();

                    var headers = credentials ? {
                        authorization: "Basic "
                        + btoa(credentials.username + ":" + credentials.password)
                    } : {};

                    $http.get(urls.HOME + 'user', {headers: headers}).then(success, error);
                    function success(response) {
                        if (response.data.name) {
                            deferred.resolve(response);
                            $rootScope.authenticated = true;
                            $rootScope.adminAccess = false;
                            var authorities = response.data.authorities;
                            for (var i = 0; i < authorities.length; i++) {
                                if (authorities[i].authority == "ADMIN") {
                                    $rootScope.adminAccess = true;
                                }
                            }
                        } else {
                            $rootScope.authenticated = false;
                            $rootScope.adminAccess = false;
                        }
                    }

                    function error(err) {
                        deferred.reject(err);
                        $rootScope.authenticated = false;
                        $rootScope.adminAccess = false;
                    }

                    return deferred.promise;

                }
            }
        }
    );
