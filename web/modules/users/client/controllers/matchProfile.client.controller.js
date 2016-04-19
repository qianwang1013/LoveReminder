(function () {
  'use strict';

  angular
    .module('users')
    .controller('MatchUserProfileController', MatchUserProfileController);

  MatchUserProfileController.$inject = ['$scope', '$state', '$http', '$stateParams', 'UsersService'];

  function MatchUserProfileController($scope, $state, $http, $routeParams, UsersService) {

    $scope.find = function () {
      // grabing the id from the stateParams
      var id = $routeParams.uid;

      console.log($routeParams.uid);
      // use this id to grab the proile object
      $http.post('/api/users/getUserProfile', { '_id' : id}).then(function(success, error){
        if(error){
          console.log(error);
        }
        else{
          $scope.user = success.data;
        }
      });
    }
  }
}());
