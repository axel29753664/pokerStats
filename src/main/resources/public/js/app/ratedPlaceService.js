angular.module("mainApp")
    .factory("ratedPlacesService", function ($http, $q) {
        return {

            getRatedPlacesFromServer: function () {
                var self = this;
                var deferred = $q.defer();
                $http.get("api/getAllRatedPlaces").then(success, error);
                function success(response) {
                    deferred.resolve(response.data);
                }

                function error(error) {
                    console.warn("Error getting rated places from server")
                }

                return deferred.promise;
            },

            getRatedPlaces: function () {
                var self = this;
                var deferred = $q.defer();
                self.getRatedPlacesFromServer().then(success);
                function success(ratedPlacesFromServer){
                    deferred.resolve(self.convertRatedPlacesToSet(ratedPlacesFromServer));
                }

                return deferred.promise;
            },
            convertRatedPlacesToSet: function (ratedPlaces) {
                var ratedPlacesSet = {};
                for (var i = 0; i < ratedPlaces.length; i++) {
                    ratedPlacesSet[ratedPlaces[i].playersQuantity] = ratedPlaces[i].ratedPlacesQuantity;
                }
                return ratedPlacesSet;
            }
        }

    });