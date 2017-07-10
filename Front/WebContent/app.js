var app = angular.module("myApp", [ 'ngRoute']);

app.config(function($routeProvider) {
	$routeProvider
	
	.when('/homepage', {
		templateUrl : 'User/Home.html'
			
			})
	
	
			
			.when('/Loginpage', {
		templateUrl : 'User/Account.html',
			controller:'UserController',
			controllerAs:'control'
			
			})
			
			
			
			.otherwise({
				redirectTo : '/'
			});
});