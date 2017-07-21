'use strict';

app	.controller('JobController',['JobService','$location', '$rootScope',
						function(JobService,  $location, $rootScope) {
							console.log("JobController...")
							var self = this;

							this.job = {
									jobid : '',jobprofile : '',jobdesc : '',postdate : '',qualification : '',status : ''

								/*errorCode : '',
								errorMessage : ''*/
							};
							this.jobs = [];
							
							this.applyForJob = applyForJob

							function applyForJob(jobid) {
								console.log("applyForJob");
								var currentUser = $rootScope.currentUser
								console.log("currentUser.id:" + currentUser.id)
								//if(currentUser) -> not null, not empty and defined
								
								if (typeof currentUser.id == 'undefined') 
									{
									   alert("Please login to apply for the job")
	                                     console.log("User not logged in.  Can not apply for job")
	                                     /*$location
											.path('/login');*/
									   return
									
									}
								console.log("->userID :" + currentUser.id
										+ "  applying for job:" + jobid)
										
										
								JobService
										.applyForJob(jobid)
										.then(
												function(data) {
													self.job = data;
													alert(self.job.errorMessage)
												},
												function(errResponse) {
													console
															.error('Error while applying for job request');
												});

							}

							self.getMyAppliedJobs = function() {
								console.log('calling the method getMyAppliedJobs');
								JobService.getMyAppliedJobs()
								.then(
									function(d) {
								    	self.jobs = d;
									/* $location.path('/view_friend'); */
								    	$location.path("/view_applied_jobs")
								    }, 
								    
								    function(errResponse) {
									console.error('Error while fetching Jobs');
								});
							};

							self.rejectJobApplication = function(userid) {
						    var jobid =$rootScope.selectedJob.id;
								JobService.rejectJobApplication(userid,jobid												)
										.then(
												function(d) {
													self.job = d;
													alert("You have successfully rejected the job application of the " +
															"user : " +userid)
												},
												function(errResponse) {
													console
															.error('Error while rejecting Job application.');
												});
							};

							self.callForInterview = function(userid) {
								var jobid =$rootScope.selectedJob.id;	
								JobService
										.callForInterview(userid,
												jobid)
										.then(
												function(d) {
													self.job = d;
													alert("Application status changed as call for interview")
												},
												function(errResponse) {
													console
															.error('Error while changing the status "call for interview" ');
												});
							};
							self.selectUser = function(userid) {
								var jobid =$rootScope.selectedJob.id;		
								JobService
										.selectUser(userid, jobid)
										.then(
												function(d) {
													self.job = d;
													alert("Application status sta as selected")
												},
												function(errResponse) {
													console
															.error('Error while changing the status "select user" ');
												});
							};

							//definition of getAllJobs function
							self.getAllJobs = function() {
								console.log('calling the method getAllJobs');
								JobService
										.getAllJobs()
										.then(
												function(d) {
													self.jobs = d;
												},
												function(errResponse) {
													console
															.error('Error while fetching All opend jobs');
												});
							};

							self.getAllJobs(); // calling getAllJobs function

							self.submit = function() {
								{
									console.log('submit a new job', self.job);
									self.postAJob(self.job);
								}
								self.reset();
							};

							self.postAJob = function(job) {
								console.log('submit a new job', self.job);
								JobService.postAJob(job).then(function(d) {
								alert("You successfully posted the job")
								$location.path('/');
								}, function(errResponse) {
									console.error('Error while posting job.');
								});
							};

							self.getJobDetails = getJobDetails

							function getJobDetails(jobid) {
								console.log('get Job details of the id', jobid);
								JobService
										.getJobDetails(jobid)
										.then(
												function(d) {
													self.job = d;
													
													$location.path('/view_job_details');
												},
												function(errResponse) {
													console
															.error('Error while fetching blog details');
												});
							};

							self.reset = function() {
								console.log('resetting the Job');
								self.job = {
										jobid : '',jobprofile : '',jobdesc : '',postdate : '',qualification : '',status : ''
									/*errorCode : '',
									errorMessage : ''*/
								};
								//$scope.myForm.$setPristine(); // reset Form
							};
							
							
						} ]);