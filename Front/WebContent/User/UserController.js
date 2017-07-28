'use strict';

app.controller('UserController',['$scope','UserService','$location','$rootScope','$http','$cookieStore','$cookies','FriendService',
						function($scope, UserService, $location, $rootScope, $http,$cookieStore,$cookies,FriendService) {
							console.log("UserController...")
							var self = this;
							self.user = {userId : '',username : '',password : '',contact : '',address : '',isonline : '',role : ''/*,errorCode : '',errorMessage : ''*/};

							self.userLoggedIn = "";

							self.currentUser = {userId : '',username : '',password : '',contact : '',address : '',isonline : '',role : ''/*,errorCode : '',errorMessage : ''*/};

							self.friend={id:'',userid:'',friendid:'',username:'',status:'', friendname:'',isonline:''};
							
							self.users = []; // json array
							self.friends = [];// json array
							var arr=[];
							var friendarr=[];
							var i = 0;
							var j = 0;
							var self = this;

							$scope.orderByMe = function(x) {
								$scope.myOrderBy = x;
							}
							
							var currentLoginUser = $cookies.getObject('currentLoginUser');
							console.log(currentLoginUser);
							
							self.fetchAllUsers = function() {
								self.asd = null;
								self.us = '';
								console.log("fetchAllUsers...")
								$scope.loginUser =$rootScope.currentUser;
								console.log("fetchUserList...")
								UserService .fetchAllUsers().then(function(d) {
									self.users = d;
									for(i=0; i<self.users.length; i++)
										{
										if(self.users[i].role!='admin'){
											arr.push(self.users[i])													
										}
										}
									self.us = arr;	
									console.log(self.us)	
									
									console.log("fetchAllRequestedFriend...")
									FriendService.fetchAllRequestedfriends($scope.loginUser.userId).then(function(d) {
										self.friends = d;
										console.log(self.friends)					
							
										
											for(j=0; j<self.us.length; j++){
												for(i=0; i<self.friends.length; i++){
												if(self.friends[i].friendid === self.us[j].userid){
													self.us.splice(j, 1);
													console.log(self.us)
												}
											}
										}
										
										self.asd = self.us;
										
										
										},function(errResponse) {
											console.error('Error while fetching Friends');
										} );
									
									
									},function(errResponse) {
										console.error('Error while fetching Users');
									});		
								
							};
							

							/*self.fetchAllUsers = function() {
								console.log("fetchAllUsers...")
								UserService
										.fetchAllUsers()
										.then(
												function(d) {
													self.users = d;
													$rootScope.users = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching Users');
												});
							};*/

						//	self.fetchAllUsers();
							
							self.acceptFriend = function(reqFriend) {
								
								
								console.log('accept the friend request')
								FriendService.updateFriendReq(reqFriend);
								
								console.log('Accepted')
							$location.path("/find")
							
							
						};
						
						self.deleteFriendRequest = function(req){
					    	FriendService.deleteFriendRequest(req.id).then(function(d) {
								self.deleteFriendRequestid = d;		    			
								console.log(self.deleteFriendRequestid)
					    			$location.path("/find")
					    	}, function(errResponse){
					                console.error('Error while deleting FriendRequest');
					            });
					    };
						
						self.requestedFriend = function() {
							$rootScope.loginUser =$rootScope.currentUser;
							console.log("GetAllRequestedFriends...")
							FriendService.fetchRequestedfriends($rootScope.loginUser.username).then(function(d) {
												self.reqFriend = d;
												
												console.log(self.reqFriend)
											},function(errResponse) {  
												console.error('Error while fetching By Friend Name');
											});
						};
						
						self.AcceptedFriendCurrentUser = function() {
							$rootScope.loginUser =$rootScope.currentUser;
							console.log("GetAllAcceptedFriendCurrentUser...")
							FriendService.fetchAcceptedFriends($rootScope.loginUser.username).then(function(d) {
												self.accFriend = d;													
												console.log(self.accFriend)
												FriendService.fetchAcceptedFriends1($rootScope.loginUser.username).then(function(d) {
													self.accFriend1 = d;													
													console.log(self.accFriend)
														
												},function(errResponse) {  
													console.error('Error while fetching Accepted list');
												});
												
												
											},function(errResponse) {  
												console.error('Error while fetching Accepted list');
											});
						};

							self.createUser = function(user) {
								console.log("createUser...")
								UserService
										.createUser(user)
										.then(
												function(d) {
													alert("Thank you for registration")
													$location.path("/")
												},
												function(errResponse) {
													console
															.error('Error while creating User.');
												});
							};

							self.myProfile = function() {
								console.log("myProfile...")
								UserService
										.myProfile()
										.then(
												function(d) {
													self.user = d;
													$location
															.path("/myProfile")
												},
												function(errResponse) {
													console
															.error('Error while fetch profile.');
												});
							};

							self.accept = function(id) {
								console.log("accept...")
								UserService
										.accept(id)
										.then(
												function(d) {
													self.user = d;
													self.fetchAllUsers
													$location
															.path("/manage_users")
													alert(self.user.errorMessage)

												},

												function(errResponse) {
													console
															.error('Error while updating User.');
												});
							};

							self.reject = function(id) {
								console.log("reject...")
								var reason = prompt("Please enter the reason");
								UserService.reject(id, reason).then(
										function(d) {
											self.user = d;
											self.fetchAllUsers
											$location.path("/manage_users")
											alert(self.user.errorMessage)

										}, null);
							};

							self.updateUser = function(currentUser) {
								console.log("updateUser...")
								UserService.updateUser(currentUser).then(
										self.fetchAllUsers, null);
							};

							self.update = function() {
								{
									console.log('Update the user details',
											$rootScope.currentUser);
									self.updateUser($rootScope.currentUser);
								}
								self.reset();
							};

							self.authenticate = function(user) {
								console.log("authenticate...")
								UserService.authenticate(user).then(function(d) {
													self.user = d;
													console.log("user.errorCode: "+ self.user.errorCode)
													if (self.user.errorCode == "404")
													{
														alert(self.user.errorMessage)
														self.user.id = "";
														self.user.password = "";
													} else { 
														console.log("Valid credentials. Navigating to home page")
														
														console.log('Current user : '+ self.user)
														$rootScope.currentUser = self.user
													    $cookieStore.put('currentUser',self.user);

														$http.defaults.headers.common['Authorization'] = 'Basic '
																+ $rootScope.currentUser;
														
														self.userLoggedIn = "true"
														if (self.user.role == "admin") {
															console.log("You are admin")
															$location.path('/admin')															
														}else{
															console.log("You are User")
															$location.path('/')
														}

														
													
													}

												},
												function(errResponse) {

													console
															.error('Error while authenticate Users');
												});
							};

							self.logout = function() {
								console.log("logout")
								self.userLoggedIn = "false"
								$rootScope.currentUser = {};
								$cookieStore.remove('currentUser');
								UserService.logout()
								$location.path('/');

							}

							// self.fetchAllUsers(); //calling the method

							// better to call fetchAllUsers -> after login ???

							self.login = function() {
								{
									console.log('login validation????????',
											self.user);
									self.authenticate(self.user);
								}

							};
							
							self.send = function(friendUser){
								console.log("sending friend request...")
								FriendService.createFriend(friendUser).then(function(d) {
									console.log(d)
									self.frndreq=d;
									console.log(self.frndreq)
										$location.path("/find")
									
												},
												function(errResponse) {
													console.error('Error while creating friend..');
												});
							
							};

							self.submit = function() {
								{
									console.log('Saving New User', self.user);
									self.createUser(self.user);
								}
								self.reset();
							};

							self.reset = function() {
								self.user = {userId : '',username : '',password : '',contact : '',address : '',isonline : '',role : ''/*,errorCode : '',errorMessage : ''*/};
								/*$scope.myForm.$setPristine();*/ // reset Form
							};

						} ]);
