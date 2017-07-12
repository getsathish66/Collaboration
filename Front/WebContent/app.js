var app = angular.module("myApp", [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider

	.when('/homepage', {
		templateUrl : 'User/Home.html'

	})

	.when('/Loginpage', {
		templateUrl : 'User/Account.html',
		controller : 'UserController',
		controllerAs : 'control'

	})

	.when('/Blogform', {
		templateUrl : 'Blog/Blogform.html',
		controller : 'BlogController',
		controllerAs : 'bcontrol'
	})

	.when('/Addforum', {
		templateUrl : 'Forum/Addforum.html',
		controller : 'ForumController',
		controllerAs : 'fcontrol'
	})

	
	.otherwise({
		redirectTo : '/'
	});
});