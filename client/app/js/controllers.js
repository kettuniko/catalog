'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
    controller('ItemsCtrl',function ($scope, Items) {
        $scope.items = Items.query({type: 'GAME'});
    }).controller('OptionsCtrl', function ($scope, Import) {
        $scope.importGames = function () {
            $scope.importing = true;
            $scope.importedItems = Import.query({username: $scope.username}, function () {
                $scope.error = false;
                $scope.importing = false;
            }, function () {
                $scope.error = true;
                $scope.importing = false;
            });
        }
    });