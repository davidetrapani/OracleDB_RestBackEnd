'use strict';
angular.module('restFrontendApp')
  .factory('MainSrvc', function ($resource) {
   return $resource('http://localhost:8080/RestBackEnd/services/rest/contact/:id',{id:'@_id'}, {
    getData: {
		method:'GET', 
		isArray: false
	     },
    postData: {
		method:'POST'
	      }
    });
  });
