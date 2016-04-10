(function () {
  'use strict';

  angular
    .module('users.routes')
    .controller('UserProfileController', UserProfileController);

  UserProfileController.$inject = ['$scope', '$state', 'UsersService'];

  function UserProfileController($scope, $state, UsersService) {
    var vm = this;
  }
}());
