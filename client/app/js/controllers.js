'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
    controller('ItemsCtrl',function ($scope, Items) {
      $scope.items = Items.query({type: 'GAME'});
        console.log($scope.items);
    }).controller('OptionsCtrl', function ($scope, Import) {
        $scope.importGames = function () {
            $scope.importedItems = Import.query({username: $scope.username}, function () {
                $scope.error = false;
            }, function () {
                $scope.error = true;
            });
        }
    });