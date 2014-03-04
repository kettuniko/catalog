'use strict';


// Declare app level module which depends on filters, and services
angular.module('myApp', [
  'ngRoute',
  'myApp.filters',
  'myApp.services',
  'myApp.directives',
  'myApp.controllers'
]).
config(['$routeProvider', function($routeProvider) {
        $routeProvider.when('/items', {templateUrl: 'partials/items.html', controller: 'MyCtrl1'});
        $routeProvider.when('/options', {templateUrl: 'partials/options.html', controller: 'MyCtrl2'});
        $routeProvider.otherwise({redirectTo: '/items'});
    }]);
