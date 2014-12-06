'use strict';

angular.module('restFrontendApp')
  .controller('MainCtrl', function ($scope, MainSrvc) {
    //http://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/
    $scope.contactData = MainSrvc.get({id:1});
    $scope.allContactsData = MainSrvc.query();
    
    var payload = {
	    "email": "angular@js.it",
	    "name": "AngularJS",
	    "phoneNumber": "111"
	}

    MainSrvc.postData({}, payload,
        function (data) {alert('OK');}
	);
    	
  });
