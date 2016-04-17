(function () {
  'use strict';

  angular
    .module('users')
    .controller('UserProfileController', UserProfileController);

  UserProfileController.$inject = ['$scope', '$state', 'UsersService', 'Authentication'];

  function UserProfileController($scope, $state, UsersService, Authentication) {
    var vm = this;
    vm.user = UsersService.me();
    vm.addInterest = addInterest;
    vm.removeInterest = removeInterest;
    vm.interest = '';

    // Update a userInterests
    function addInterest() {
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
