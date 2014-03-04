'use strict';

/* Services */

angular.module('myApp.services', ['ngResource']).factory('Items', function ($resource) {
  return $resource('http://localhost:8081/items?type=:type', {}, {
    query: {method: 'GET', isArray: true}
  });
});