(function () {
  'use strict';

  angular
    .module('users')
    .controller('UserProfileController', UserProfileController);

  UserProfileController.$inject = ['$scope', '$state', '$http', 'UsersService', 'AdminService', 'Authentication'];

  function UserProfileController($scope, $state, $http, UsersService, AdminService, Authentication) {
    var vm = this;
    vm.user = UsersService.me();
    vm.matchedUser = matchedUser;
    vm.addInterest = addInterest;
    vm.removeInterest = removeInterest;
    vm.redirect = redirect;
    vm.interest = '';

    function matchedUser() {
      var match = new AdminService();
      vm.displayMatchedUser = match.get({userId: '@id'}, function(){
        console.log(vm.displayMatchedUser);
      })
    }

    function redirect() {
      GetUserID.redirect(); // eslint-disable-line no-undef

      // Simple GET request example:
      // $http({
      //   method: 'GET',
      //   url: '/api/users/match/5713025f7749839023751950/5713ee80670cf09137a97db3'
      // }).then(function successCallback(response) {
      //   console.log(response);
      // }, function errorCallback(response) {
      //   console.log(response);
      // });
    }

    // Update a userInterests
    function addInterest() {

      if (document.getElementById('interest').value === '') {
        return;
      }

      var user = new UsersService(vm.user);
      user.userInterest.push(vm.interest);

      document.getElementById('interest').value = '';

      user.$update(function (response) {
        vm.user = response;
      }, function (response) {
        vm.error = response.data.message;
      });
    }
    
    function removeInterest(index) {
      var user = new UsersService(vm.user);
      user.userInterest.splice(index, 1);

      user.$update(function (response) {
        vm.user = response;
      }, function (response) {
        vm.error = response.data.message;
      });
    }
  }
}());
