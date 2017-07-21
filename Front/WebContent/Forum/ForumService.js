'use strict';

 

app.service('ForumService', ['$http', '$q', function($http, $q){

	console.log("forumService...")

    var BASE_URL = 'http://localhost:8083/Restservices';



	 var factory = {

		        fetchAllForums: fetchAllForums,

		        createForum: createForum,

		        updateForum:updateForum,

		        AcceptedForums : AcceptedForums,

		        notAcceptedForums : notAcceptedForums,

				//accept: accept,

		        deleteForum:deleteForum

		    };

		 

		    return factory;

		 

		    function fetchAllForums() {

				console.log("calling fetchAllforums ")

				return $http.get(BASE_URL+'/forum').then(function(response) {

					return response.data;

				}, null);

			};

			function AcceptedForums() {

				console.log("calling AcceptedForums ")



				return $http.get(BASE_URL + '/acceptedforum').then(

						function(response) {

							console.log('response');

							return response.data;

							console.log(response)

						}, null);

			};

			function notAcceptedForums() {
				console.log("calling notAcceptedForums ")

				return $http.get(BASE_URL + '/notAcceptedforum').then(
						function(response) {
							console.log(response)
							return response.data;

						}, null);
			};

			function createForum(Forum) {

				console.log("calling create Forum")

				return $http.post(BASE_URL+'/forum', Forum) // 1

				.then(function(response) {

					return response.data;

				}, function(errResponse) {

					console.error('Error while creating Forum');

					return $q.reject(errResponse);

				});

			};



		 

			function updateForum(Forum) {

				console.log("calling fetchAllForums ")

				return $http.put(BASE_URL+'/updateForum/', Forum) // 2

				.then(function(response) {

					return response.data;

				}, function(errResponse) {

					console.error('Error while updating Forum');

					return $q.reject(errResponse);

				});

			};

		 

		    function deleteForum(id) {

		    	console.log("Deleting Forum Request");

				return $http.delete(BASE_URL + '/deleteForum/'+id).then(function(response){

						

					return response.data;

						},function(errResponse) {

							console.error('Error while deleting Forum request');

							return $q.reject(errResponse);

						});

		

			};



}]);