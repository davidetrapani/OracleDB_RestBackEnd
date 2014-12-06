'use strict';

/**
 * @ngdoc overview
 * @name restFrontendApp
 * @description
 * # restFrontendApp
 *
 * Main module of the application.
 */
angular
  .module('restFrontendApp', [
    'ngAnimate',
    'ngCookies',
    'ngResource',
    'ngRoute',
    'ngSanitize',
    'ngTouch'
  ])
  .config(function ($routeProvider) {
    $routeProvider
      .when('/', {
        templateUrl: 'views/main.html',
        controller: 'MainCtrl'
      })
      .otherwise({
        redirectTo: '/'
      });
  });
