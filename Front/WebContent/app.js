var app = angular.module("myApp", [ 'ngRoute' ]);

app.config(function($routeProvider) {
	$routeProvider

	/*.when('/', {
		templateUrl : 'User/Home.html'

	})*/

	.when('/Registerpage', {
		templateUrl : 'User/Register.html',
		controller : 'UserController',
		controllerAs : 'rc'

	})
	
	.when('/Loginpage', {
		templateUrl : 'User/Login.html',
		controller : 'UserController',
		controllerAs : 'lc'

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