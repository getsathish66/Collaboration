var app = angular.module("myApp", [ 'ngRoute','ngCookies' ]);

app.config(function($routeProvider) {
	$routeProvider
	
	

	
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

	.when('/Viewblog', {
		templateUrl : 'Blog/Viewblog.html',
		controller : 'BlogController',
		controllerAs : 'bcontrol'
	})
	
	.when('/Addforum', {
		templateUrl : 'Forum/Addforum.html',
		controller : 'ForumController',
		controllerAs : 'fcontrol'
	})
	
	.when('/Viewforum', {
		templateUrl : 'Forum/Viewforum.html',
		controller : 'ForumController',
		controllerAs : 'fcontrol'
	})
	
	.when('/Addjobs', {
		templateUrl : 'Jobs/Jobform.html',
		controller : 'JobController',
		controllerAs : 'jcontrol'
	})

	.when('/adminBlog', {
		templateUrl : 'Admin/Blog.html',
		controller : 'BlogController',
		controllerAs : 'bcc'
	})
	
	.when('/adminForum', {
		templateUrl : 'Admin/Forum.html',
		controller : 'ForumController',
		controllerAs : 'fcc'
	})
	
	.when('/admin', {
		templateUrl : 'Admin/Adminhome.html'

	})
	
	.otherwise({
		redirectTo : '/'
	});
});