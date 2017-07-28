var app = angular.module("myApp", [ 'ngRoute','ngCookies' ]);

app.config(function($routeProvider) {
	$routeProvider
	
	
	.when('/', {
		templateUrl : 'User/Home.html'
		

	})
	
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

	.when('/Addblog', {
		templateUrl : 'Blog/Blogform.html',
		controller : 'BlogController',
		controllerAs : 'bcontrol'
	})
	
	.when('/Viewblogs', {
		templateUrl : 'Blog/Viewblog.html',
		controller : 'BlogController',
		controllerAs : 'bcontrol'
	})
	
	.when('/blogdetails', {
		templateUrl : 'Blog/Blogdetails.html',
		controller : 'BlogController',
		controllerAs : 'u'
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
	
	.when('/forumdetails', {
		templateUrl : 'Forum/Forumdetails.html',
		controller : 'ForumController',
		controllerAs : 'u'
	})
	
	.when('/Addjobs', {
		templateUrl : 'Jobs/Jobform.html',
		controller : 'JobController',
		controllerAs : 'jcontrol'
	})
	
	.when('/Viewjobs', {
		templateUrl : 'Jobs/Viewjob.html',
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
	
	.when('/adminJob', {
		templateUrl : 'Admin/Job.html',
		controller : 'JobController',
		controllerAs : 'jcc'
	})
	
	.when('/jobdetails', {
		templateUrl : 'Jobs/Jobdetails.html',
		controller : 'JobController',
		controllerAs : 'u'
	})
	
	.when('/userlist', {
		templateUrl : 'Friend/Viewusers.html',
		controller : 'UserController',
		controllerAs : 'fl'
	})
	
	.when('/find', {
		templateUrl : 'User/Viewusers.html',
		controller : 'UserController',
		controllerAs : 'lc'
		
			
	})
	
	.when('/chat', {
		templateUrl : 'Chat/Chat.html',
		controller : 'ChatController'
	})
	
	.when('/admin', {
		templateUrl : 'Admin/Adminhome.html'

	})
	
	
	
	.otherwise({
		redirectTo : '/'
	});
});
app.run( function ($rootScope, $location,$cookieStore, $http) {

	 $rootScope.$on('$locationChangeStart', function (event, next, current) {
		 console.log("$locationChangeStart")
		
		 var userPages = ['/myProfile','/blog','/search_friend','/view_friend']
		 var adminPages = ["/job","/manage_users"]
		 
		
		 var currentPage = $location.path()
		 
		
		 var isUserPage = $.inArray(currentPage, userPages)
		 var isAdminPage = $.inArray(currentPage, adminPages)
		 
		 var isLoggedIn = $rootScope.currentUser.id;
	        
	     console.log("isLoggedIn:" +isLoggedIn)
	     console.log("isUserPage:" +isUserPage)
	     console.log("isAdminPage:" +isAdminPage)
	        
	        if(!isLoggedIn)
	        	{
	        	
	        	 if (isUserPage===0 || isAdminPage ===0) {
		        	  console.log("Navigating to login page:")
		        	  alert("You need to loggin to do this operation")

						            $location.path('/');
		                }
	        	}
	        
			 else
	        	{
	        	
				 var role = $rootScope.currentUser.role;
				 
				 if(isAdminPage===0 && role!='admin' )
					 {
					 
					  alert("You can not do this operation as you are logged as : " + role )
					   $location.path('/');
					 
					 }}}
				     
	        	
	        	      );
	 
	 
	 
   $rootScope.currentUser = $cookieStore.get('currentUser') || {};
   if ($rootScope.currentUser) {
       $http.defaults.headers.common['Authorization'] = 'Basic' + $rootScope.currentUser; 
   }

});
