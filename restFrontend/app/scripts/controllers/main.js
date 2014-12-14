'use strict';

angular.module('restFrontendApp')
    .controller('MainCtrl', function ($scope, $timeout, MainSrvc) {
        //http://www.sitepoint.com/creating-crud-app-minutes-angulars-resource/
        //$scope.contactData = MainSrvc.get({id: 28});
        $scope.id = null;

        $scope.allContactsData = MainSrvc.query();
        $scope.hash = "";
        var payload = {};

        $scope.reverse = false;
        $scope.click = function (name) {
            $scope.sort = name;
            $scope.reverse = !$scope.reverse;
        }


        $scope.insertedNewRecord = false;
        $scope.createOrUpdate = function () {
            var payload = {
                "email": $scope.email,
                "name": $scope.name,
                "phoneNumber": $scope.phoneNumber
            };
            if($scope.id != null)
                payload.id= $scope.id;

            console.log(JSON.stringify(payload));

            $scope.allContactsData.push(payload);
            $scope.email = null;
            $scope.name = null;
            $scope.phoneNumber= null;
            console.log(JSON.stringify($scope.allContactsData));
        }

        $scope.saveOnDB = function () {
            MainSrvc.postData({},  $scope.allContactsData,
                function (data) {
                    $scope.allContactsData = MainSrvc.query();
                    $scope.insertedNewRecord = true;

                    $timeout(function () {
                        $scope.insertedNewRecord = false;
                    }, 5000);
                }
            );
        }

        $scope.deletedRecord = false;
        $scope.removeItem = function (id) {
            MainSrvc.remove({id: id}, function(){
                $scope.deletedRecord = true;
                $scope.allContactsData = MainSrvc.query();
                $timeout(function () {
                    $scope.deletedRecord = false;
                }, 5000);
            }, function(){
                $scope.deletedRecord = false;
                $scope.allContactsData = MainSrvc.query();
            });
        }

        $scope.editRecord = false;
        $scope.edit = function () {
            //http://stackoverflow.com/questions/8668174/indexof-method-in-an-object-array
            var i = arrayObjectIndexOf($scope.allContactsData, $scope.hash, "$$hashKey");
            console.log("Indice: "+ i);
            $scope.allContactsData.splice(i, 1); //rimuovo l'elemento
            $scope.createOrUpdate(); //lo aggiungo ex-novo
            $scope.editRecord = false;
            $scope.id = null;
        }

        $scope.editItem = function (data) {
            var payload = {
                "id": data.id,
                "email": data.email,
                "name": data.name,
                "phoneNumber": data.phoneNumber
            };
            $scope.id = data.id;
            $scope.hash =  data.$$hashKey;
            $scope.editRecord = true;
            $scope.email = data.email;
            $scope.name = data.name;
            $scope.phoneNumber= data.phoneNumber;
            console.log("HASH:" + $scope.hash);
            console.log(JSON.stringify($scope.allContactsData));
        }

        function arrayObjectIndexOf(myArray, searchTerm, property) {
            for(var i = 0, len = myArray.length; i < len; i++) {
                if (myArray[i][property] === searchTerm) return i;
            }
            return -1;
        }
    });
