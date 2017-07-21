'use strict';

app.controller('BlogController', [
		'$scope',
		'$location',
		'BlogService',
		'$rootScope',
		'$http',
		'$cookieStore',

		function($scope, $location, BlogService, $rootScope, $http,
				$cookieStore) {

			console.log("BlogController...")

			var self = this;

			self.blog = {
				blogid : '',
				blogname : '',
				title : '',
				status : '',
				description : '',
				createdate : '',
				likes : '',
				userid : ''
			};

			self.blogs = [];

			self.submit = submit;

			self.edit = edit;
			self.remove = remove;
			self.reset = reset;
			self.get = get;
			self.accept = accept;
			self.adminDetails = adminDetails;
			self.blogDetails = blogDetails;
			self.rejectBlog = rejectBlog;
			
			fetchAllBlogs();
			AcceptedBlogs();
			notAcceptedBlogs();
			//accept();
			reset();
			//adminGet();
			
			function fetchAllBlogs() {

				BlogService.fetchAllBlogs()

				.then(

				function(d) {

					self.blogs = d;

				},

				function(errResponse) {

					console.error('Error while fetching Blogs');

				}

				);

			}

			function AcceptedBlogs() {

				console.log("AcceptedBlogs...")

				BlogService.AcceptedBlogs().then(function(d) {

					// alert("Thank you for creating message")

					console.log(d)

					self.blogsAccept = d;

				},

				function(errResponse) {

					console.error('Error while creating AcceptedBlogs.');

				});

			}
			;

			function notAcceptedBlogs() {
				console.log("notAcceptedBlogs...")
				BlogService.notAcceptedBlogs().then(function(d) {

					console.log(d)
					self.blogsNotAccepted = d;
					console.log(self.blogsNotAccepted)
				}, function(errResponse) {
					console.error('Error while creating notAcceptedBlogs.');
				});
			}
			;

			function createBlog(blog) {

				console.log("createBlog...")

				BlogService.createBlog(blog).then(function(d) {

					alert("Thank you for creating message")

					$location.path("/login")

				}, function(errResponse) {

					console.error('Error while creating Blog.');

				});

			}
			;

			function deleteblog(id) {

				BlogService.deleteBlog(id)

				.then(

				fetchAllBlogs,

				function(errResponse) {

					console.error('Error while deleting jobs');

				}

				);

			}

			function edit(id) {

				console.log('id to be edited', id);

				for (var i = 0; i < self.blogs.length; i++) {

					if (self.blogs[i].blogid === id) {

						self.blog = angular.copy(self.blogs[i]);

						break;

					}

				}

			}

			function updateBlog(blog, id) {

				BlogService.updateBlog(blog, id)

				.then(

				fetchAllBlogs,

				function(errResponse) {

					console.error('Error while updating jobs');

				}

				);

			}

			function remove(id) {

				console.log('id to be deleted', id);

				if (self.blog.blogid === id) {// clean form if the user to be
					// deleted is shown there.

					reset();

				}

				deleteblog(id);

			}

			function get(blog) {

				$scope.bc = blog;

				console.log($scope.bc);

				$rootScope.blog = $scope.bc;

				$location.path("viewBlog");

			}

			function submit() {

				console.log('Creating New Blog', self.blog);

				createBlog(self.blog);

			}
			;

			function accept(ViewBlogs) {
				{
					console.log('accept the Blog details')

					BlogService.accept(ViewBlogs);
					console.log(ViewBlogs)
					$location.path("/admin")
				}

			}
			;

			function adminDetails(blog) {
				$scope.bvv = blog;
				console.log($scope.bvv);
				$rootScope.ViewBlog = $scope.bvv;
				$location.path('/adminBlog')
			}
			
			function blogDetails(blog) {
				$scope.bvv = blog;
				console.log($scope.bvv);
				$rootScope.ViewBlog = $scope.bvv;
				$location.path('/blogdetails')
			}
			
			function rejectBlog(ViewBlog){
		    	BlogService.deleteBlog(ViewBlog.blogid).then(function(d) {
					self.deleteBlogRequestId = d;		    			
					console.log(self.deleteBlogRequestId);
		    			$location.path("/admin")
		    	}, function(errResponse){
		                console.error('Error while deleting BlogRequest');
		            });
		    };

			function reset() {

				self.blog = {
					blogid : 'null',
					blogname : '',
					title : '',
					status : '',
					description : '',
					createdate : '',
					username : '',
					likes : '',
					userid : ''
				};

				// $scope.myform.$setPristine(); //reset Form

			}
			;

		} ]);