'use strict';

/* Services */
var SERVER_URL = 'http://localhost:8081';

angular.module('myApp.services', ['ngResource']).factory('Items', function ($resource) {
    return $resource('http://localhost:8081/items', {}, {
        query: {method: 'GET', isArray: true}
    });
}).factory('Import', function ($resource) {
    return $resource(SERVER_URL + '/import/steam', {}, {
        query: {method: 'GET', isArray: true}
  });
});