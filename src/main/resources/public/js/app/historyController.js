angular.module("mainApp")
    .controller("historyController", function ($scope, historyService) {

        $scope.startDate = new Date();
        $scope.endDate = new Date();

        $scope.getHistory = function () {

            historyService.getHistoryFromServer($scope.startDate, $scope.endDate).then(function success(history) {
                $scope.history = history;
            });
        };
    });
