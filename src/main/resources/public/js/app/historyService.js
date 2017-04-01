angular.module("mainApp")
    .factory("historyService", function ($http, $q) {
        return {
            getHistoryFromServer: function (startDate, endDate) {
                var deferred = $q.defer();

                $http.post("api/auth/getHistory", {startDate: startDate, endDate: endDate}).then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(err) {
                    console.warn("Error getting history from server");
                    console.warn(err);
                }

                return deferred.promise;
            }
        }
    });
