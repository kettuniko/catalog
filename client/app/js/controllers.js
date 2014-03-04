'use strict';

/* Controllers */

angular.module('myApp.controllers', []).
    controller('ItemsCtrl',function ($scope, Items) {
      $scope.items = Items.query({type: 'GAME'});
    }).controller('OptionsCtrl', function () {

    });