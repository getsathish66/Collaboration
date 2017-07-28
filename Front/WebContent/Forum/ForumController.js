'use strict';

app.controller('ForumController',['$scope', '$location', '$cookieStore','ForumService','$rootScope', '$http',

	function($scope, $location, $cookieStore, ForumService,$rootScope,$http) {

	console.log("ForumController...")

	

	var self = this;

	self.forum = {forumid : '',forumname:'',forumcontent:'',userid:'',createdate:'',status:''};

	

	self.forums = [];

	self.submit = submit;

 self.edit = edit;

    self.remove = remove;

    self.reset = reset;
	self.accept = accept;
	self.rejectForum = rejectForum;
	self.get = get;
    self.adminDetails=adminDetails;
    self.forumDetails=forumDetails;

    

    fetchAllForums();

    AcceptedForums();
    
    notAcceptedForums();

    reset();

    function fetchAllForums(){

    	ForumService.fetchAllForums()

            .then(

            function(d) {

                self.forums = d;

            },

            function(errResponse){

                console.error('Error while fetching Forums');

            }

        );

    }

    function AcceptedForums() {

		console.log("AcceptedForums...")

		ForumService.AcceptedForums().then(function(d) {

							//alert("Thank you for creating message")

			console.log(d)

							self.forumsAccept = d;

						},

						function(errResponse) {

							console.error('Error while creating AcceptedForums.');

						});

	};
	
	function notAcceptedForums() {
		console.log("notAcceptedForums...")
		ForumService.notAcceptedForums().then(function(d) {
						
			console.log(d)
							self.forumsNotAccepted = d;
							console.log(self.forumsNotAccepted)
						},
						function(errResponse) {
							console.error('Error while creating notAcceptedForums.');
						});
	};

    function createForum(forum){

		console.log("createForum...")

		ForumService.createForum(forum).then(function(d) {

			alert("Thank you for creating message")

			$location.path("/login")

		}, function(errResponse) {

			console.error('Error while creating Forum.');

		});

	};



    /*function deleteforum(id){

    	ForumService.deleteForum(id)

            .then(

            		fetchAllForums,

            function(errResponse){

                console.error('Error while deleting jobs');

            }

        );

    }
*/
    

    function edit(id){

        console.log('id to be edited', id);

        for(var i = 0; i < self.forums.length; i++){

            if(self.forums[i].forumid === id) {

                self.forum = angular.copy(self.forums[i]);

                break;

            }

        }

    }

    function updateForum(forum, id){

    	ForumService.updateForum(forum, id)

            .then(

            		fetchAllForums,

            function(errResponse){

                console.error('Error while updating jobs');

            }

        );

    }

 

    function remove(id){

        console.log('id to be deleted', id);

        if(self.forum.forumid === id) {//clean form if the user to be deleted is shown there.

            reset();

        }

        deleteforum(id);

    }

 

    function get(forum) {

    	$scope.bc=forum;

		console.log($scope.bc);

		$rootScope.forum=$scope.bc;

		$location.path("viewForum");

    	

	}

    function submit() {

        console.log('Creating New Forum', self.forum);

           createForum(self.forum);

       

   };

   function adminDetails(forum) {
		$scope.bvv = forum;
		console.log($scope.bvv);
		$rootScope.ViewForum = $scope.bvv;
		$location.path('/adminForum')
	}
	
	function forumDetails(forum) {
		$scope.bvv = forum;
		console.log($scope.bvv);
		$rootScope.ViewForum = $scope.bvv;
		$location.path('/forumdetails')
	}
	
	function accept(ViewForum) {
		{
			console.log('accept the Forum details')

			ForumService.accept(ViewForum);
			console.log(ViewForum)
			$location.path("/admin")
		}

	};
	
	function rejectForum(ViewForum){
    	ForumService.deleteForum(ViewForum.forumid).then(function(d) {
			self.deleteBlogRequestId = d;		    			
			console.log(self.deleteBlogRequestId);
    			$location.path("/admin")
    	}, function(errResponse){
                console.error('Error while deleting BlogRequest');
            });
    };
	
   
    function reset(){

    	self.forum={forumid : 'null',forumname:'',title : '',status : '',description : '',createdate : '',username:'',likes:'',userid:''};



       //$scope.myform.$setPristine(); //reset Form

    };

    

    

    

	

}]);