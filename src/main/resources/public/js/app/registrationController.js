angular.module("mainApp")
    .controller("registrationController", function ($scope, $http, regValidator) {

        $scope.initRegModal = function () {
            $scope.regSuccess = false;
            $scope.errors = {};
            $scope.newUser = {};
        };

        $scope.addNewUser = function () {
            if ($scope.registrationForm.$valid) {
                var errors = regValidator.validate($scope.newUser);
                if (!errors.hasError) {
                    var user = {
                        name: $scope.newUser.name,
                        password: $scope.newUser.password
                    };
                    $http.post("admin/createNewUser", user).then(success, error);

                    function success(response) {
                        $scope.regSuccess = true;
                    }

                    function error(err) {
                        if (err.data !== "") {
                            $scope.errors = err.data;
                        } else {
                            console.warn("Error sending create user data to server.");
                            console.warn(err);
                        }
                    }
                } else {
                    $scope.errors = errors;
                }
            }
        };
    });
