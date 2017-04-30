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

            convertRatedPlacesToSet: function (ratedPlaces) {
                var ratedPlacesSet = {};
                for (var i = 0; i < ratedPlaces.length; i++) {
                    ratedPlacesSet[ratedPlaces[i].playersQuantity] = ratedPlaces[i].ratedPlacesQuantity;
                }
                return ratedPlacesSet;
            },

            checkRatedPlacesToWrongFormat: function (ratedPlaces) {
                var errors = "";
                for (var i = 0; i < ratedPlaces.length; i++) {
                    if (ratedPlaces[i].ratedPlacesQuantity === undefined) {
                        errors += ratedPlaces[i].playersQuantity
                            + ": " + ratedPlaces[i].ratedPlacesQuantity + " ";
                    }
                }
                if (errors !== "") {
                    return "Wrong enter at: " + errors;
                }
                return errors;
            },
            sendRatedPlacesToServer: function (ratedPlaces) {
                var errors = this.checkRatedPlacesToWrongFormat(ratedPlaces);
                var deferred = $q.defer();
                if (errors !== "") {
                    deferred.reject(errors);
                } else {
                    $http.post("api/auth/admin/updateRatedPlaces", ratedPlaces).then(success, error);
                    function success(response) {
                    }

                    function error(err) {
                        console.warn("Error sending ratedPlaces to server");
                        console.warn(err);
                    }
                }

                return deferred.promise;
            },
            addNeRatedPlaces: function (allRatedPlaces, newRatedPlaces) {
                if (newRatedPlaces !== undefined) {
                    if ((newRatedPlaces.playersQuantity !== undefined)
                        && (newRatedPlaces.ratedPlacesQuantity !== undefined)
                        && (newRatedPlaces.playersQuantity >= newRatedPlaces.ratedPlacesQuantity)) {
                        allRatedPlaces[allRatedPlaces.length] = newRatedPlaces;
                    } else {
                        return "Wrong new rated place format";
                    }
                }
            }
        }

    });