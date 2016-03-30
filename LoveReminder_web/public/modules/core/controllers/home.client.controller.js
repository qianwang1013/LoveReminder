'use strict';

var mongoose = require('mongoose'); 
var local = require('./local.js'); // local.js has db connection info
mongoose.connect(local.db.uri); 

angular.module('core').controller('HomeController', ['$scope', 'Authentication',
	function($scope, /*Authentication*/) { // ignoring authentication for now 
		// $scope.authentication = Authentication; 
		$scope.fname; 
		$scope.lname; 
		$scope.gender;
		$scope.age; 
		$scope.interests; 
		
		var submit = function() { 
		
		var info = {fname: $scope.fname, 
					lname: $scope.lname, 
					gender = $scope.gender, 
					age = $scope.age, 
					interests = $scope.interests}; 
		if (mongoose.find(info) // For changing information
			mongoose.update(info, update); 
		else // For first time users 
			mongoose.save(err, info) {
				if (err) return console.error(err); 
				console.log("There was an error"); 
			}
	}
	}
]);