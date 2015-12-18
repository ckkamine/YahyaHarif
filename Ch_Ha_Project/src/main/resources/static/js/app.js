var app = angular.module('myApp', [ 'ui.router', 'ngCookies', 'ngMessages',
		'ui.bootstrap' ]);
var isConnected = false;
app
		.config(
				[
						'$stateProvider',
						'$locationProvider',
						'$httpProvider',
						'$urlRouterProvider',
						function($stateProvider, $locationProvider,
								$httpProvider, $urlRouterProvider) {
							$urlRouterProvider.otherwise('/login');

							$stateProvider.state('login', {
								url : '/login',
								templateUrl : 'partials/login.html',
								controller : loginCtrl,
								resolve : {
									connection : checkIsNonConnected
								}
							});
							$stateProvider.state('admin', {
								templateUrl : 'partials/admin/index.html',
								resolve : {
									connection : checkIsConnected,
									role : checkIsAdmin
								}
							});
							$stateProvider.state('admin.dashbord', {
								url : '/admin',
								controller : adminCtrl,
								templateUrl : 'partials/admin/dashbord.html'
							});
							$stateProvider.state('admin.user', {
								url : '/admin/profile',
								templateUrl : 'partials/user/profile.html'
							});
							$stateProvider.state('admin.bilan', {
								url : '/admin/bilan',
								templateUrl : 'partials/admin/bilan.html'
							});
							$stateProvider.state('admin.bap', {
								url : '/admin/bilan/bap',
								templateUrl : 'partials/admin/bap.html',
								controller : bapCtrl
							});
							$stateProvider.state('admin.projet', {
								url : '/admin/projets',
								controller : projetCtrl,
								templateUrl : 'partials/admin/projet.html'

							});
							$stateProvider.state('admin.archive', {
								url : '/admin/archives',
								templateUrl : 'partials/admin/archive.html'
							});
							$stateProvider.state('admin.parametre', {
								url : '/admin/parametre',
								controller : parametreCtrl,
								templateUrl : 'partials/admin/parametre.html'

							});
							$stateProvider.state('admin.parametre.id', {
								url : '/:id',
								controller : parametreIdCtrl,
								templateUrl : 'partials/admin/id.html'

							});
							$stateProvider.state('manager', {
								templateUrl : 'partials/manager/index.html',
								resolve : {
									connection : checkIsConnected,
									role : checkIsManager
								}
							});
							$stateProvider.state('manager.dashbord', {
								url : '/manager',
								controller : managerCtrl,
								templateUrl : 'partials/manager/dashbord.html'
							});

							$stateProvider
									.state(
											'collaborateur',
											{
												templateUrl : 'partials/collaborateur/index.html',
												resolve : {
													connection : checkIsConnected,
													role : checkIsCollaborateur
												}
											});
							$stateProvider
									.state(
											'collaborateur.dashbord',
											{
												url : '/collaborateur',
												templateUrl : 'partials/collaborateur/dashbord.html'

											});
							$stateProvider.state('encadrant', {
								templateUrl : 'partials/encadrant/index.html',
								resolve : {
									connection : checkIsConnected,
									role : checkIsEncadrant
								}
							});
							$stateProvider
									.state(
											'encadrant.dashbord',
											{
												url : '/encadrant',
												templateUrl : 'partials/encadrant/dashbord.html'
											});
							$stateProvider
									.state(
											'encadrant.feedbacks',
											{
												url : '/encadrant/feedbacks',
												controller : feedbackCtrl,
												templateUrl : 'partials/encadrant/feedbacks.html'
											});
							$httpProvider.interceptors
									.push(function($q, $rootScope, $location,
											$cookieStore) {
										return {
											'request' : function(config) {
												var isRestCall = config.url
														.indexOf('rest') == 0;
												if (isRestCall
														&& angular
																.isDefined($cookieStore
																		.get('token'))) {
													var authToken = $cookieStore
															.get('token');
													config.headers['X-Auth-Token'] = signatureHMAC(
															authToken,
															config.url);
												}
												return config
														|| $q.when(config);
											}
										};
									});

						} ]).run(
				function($rootScope, $location, $cookieStore, $http, $state) {
					var today = new Date();
					var token = $cookieStore.get('token');
					if (token !== undefined) {
						
						var part = token.split(':');
						if (today.getTime() > part[1]) {
							delete $rootScope.user;
							$cookieStore.remove('user');
							$cookieStore.remove('token');
							isConnected = false;
							$location.url("/login");
							$rootScope.username = '';
							$rootScope.password = '';
						} else {
							var originalPath = $location.path();
							var user = $cookieStore.get('user');
							if (user !== undefined) {
								$rootScope.user = user;
								isConnected = true;
								$location.path(originalPath);
							}
						}
					} else {
						$location.url('/login');
					}

					/* Reset error when a new view is loaded */
					$rootScope.$on('$viewContentLoaded', function() {
						delete $rootScope.error;
					});
					$rootScope.hasRole = function(role) {
						if (role = $rootScope.user.role) {
							return true;
						} else {
							return false;
						}
					};
					$rootScope.logout = function() {
						delete $rootScope.user;
						$cookieStore.remove('user');
						$cookieStore.remove('token');
						isConnected = false;
						$location.url("/login");
						$rootScope.username = '';
						$rootScope.password = '';
					};

					$rootScope.date = today.getDate() + ' / '
							+ (today.getMonth() + 1);

					$rootScope.reload = function() {
						$state.go($state.current, {}, {
							reload : true
						});
					};

					$rootScope.redirectRole = function() {
						switch ($rootScope.user.role) {
						case 'ADMIN':
							$location.url('/admin');
							break;
						case 'MANAGER':
							$location.url('/manager');
							break;
						case 'COLLABORATEUR':
							$location.url('/collaborateur');
							break;
						case 'ENCADRANT':
							$location.url('/encadrant');
							break;
						}
						;

					};
					$rootScope.initialized = true;
				});
function adminCtrl($scope) {

}
function managerCtrl($scope) {

}
function bapCtrl($scope, $http, $rootScope) {
	var role = $rootScope.user.role.toLowerCase();
	$scope.errorSome=false;
	$scope.tab=0;
	$scope.descriptions=new Array();
	$scope.editerDescription= function(d){
		$scope.descriptions.splice(d, 1);
	}
	$scope.calculeSomme = function() {
		$scope.somme=0;
		for(i=0; $scope.bapCurrent.objectifsSortantes.length >i;i++){
			for(j=0; $scope.bapCurrent.objectifsSortantes[i].descriptions.length >j;j++){
				$scope.somme +=$scope.bapCurrent.objectifsSortantes[i].descriptions[j].poids;
			}
		}
		for(i=0; $scope.descriptions.length >i; i++){
			$scope.somme += $scope.descriptions[i].poids;
		}
		
	}
	
	$scope.ajouterUneAutre= function() {		
		$scope.descriptions.push($scope.description);
		$scope.description={};
	}
	
	
	$scope.editResultatF= false;
	$scope.editResultat= function() {
		$scope.editResultatF= true;
	}
	$scope.editRemarqueF= false;
	$scope.editRemarque= function() {
		$scope.editRemarqueF= true;
	}
	$scope.clearDecision= function(){
		$scope.bapCurrent.decision='';
	}
	$scope.editObjectifsS= function(o , d) {
		var c=0;
		for(i=0; $scope.bapCurrent.objectifsEntrantes.length>i;i++){
			for(j=0; $scope.bapCurrent.objectifsEntrantes[i].descriptions.length>j;j++){
				if(($scope.bapCurrent.objectifsEntrantes[i].idObjectif==o)&&($scope.bapCurrent.objectifsEntrantes[i].descriptions[j].idDescription==d)){
					$scope.editObjectifSF[c]=true;
				}
				c++;
			}
		}
	}
	$scope.detail = false;
	
	$http({
		method : 'GET',
		url : 'rest/'+role+'/encadrants'
	}).then(function(response) {
		$scope.encadrants = response.data;
	}, function() {

	});
	$scope.getBaps = function(page) {
		$http({
			method : 'GET',
			url : 'rest/'+ role +'/baps',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				page : page
			}
		}).then(function(response) {
			$scope.baps = response.data.content;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
		}, function() {

		});
	}
	$scope.addObjectif= function(){
		$scope.ajouterUneAutre();
		for(i=0; $scope.descriptions.length > i; i++){
			$scope.descriptions[i].responsable.type='ENC';
			$scope.descriptions[i].responsable.user='';
			$scope.descriptions[i].responsable.password='';
		}
		$scope.objectif.descriptions= $scope.descriptions;
		$http({
			method : 'POST',
			url : 'rest/' + role + '/objectif',
			params:{
				idBap: $scope.bapCurrent.id
			},
			data : $scope.objectif
		}).then(function(response) {
			$scope.descriptions={};
			$scope.description={};
			$scope.objectif={};
			$scope.getBaps($scope.pageCurrent);
			for(i=0; $scope.baps.length > i; i++){
				if($scope.baps[i].id==$scope.bapCurrent.id){
					$scope.bapCurrent=$scope.baps[i];
				}
			}
			$scope.tab=0;
		}, function() {

		});
	}
	$scope.getBaps(0);
	$scope.seeMore = function(bap) {
		$scope.bapCurrent = bap;
		$scope.detail = true;
		$scope.editObjectifsSF=[];
		for(i=0; bap.objectifsSortantes.length>i; i++){
			$scope.editObjectifsSF[i]= false;
		}
		$scope.calculeSomme();
	}
	$scope.retour = function() {
		$scope.detail = false;
		$scope.getBaps($scope.pageCurrent);
	}
	
	$scope.calculNoteGlobale= function(){
		$scope.bapCurrent.noteGlobale=0;
		for(i=0; $scope.bapCurrent.objectifsEntrantes.length>i;i++){
			for(j=0; $scope.bapCurrent.objectifsEntrantes[i].descriptions.length>j;j++){
				$scope.bapCurrent.noteGlobale+= $scope.bapCurrent.objectifsEntrantes[i].descriptions[j].note;
			}
		}
	}
	
	$scope.updateBap= function(){
		$scope.bapCurrent.type='BAP';
		$http({
			method : 'PUT',
			url : 'rest/' + role + '/bilan',
			data : $scope.bapCurrent
		}).then(function(response) {
			$scope.bapCurrent=response.data;
			var c=0;
			for(i=0; $scope.bapCurrent.objectifsEntrantes.length>i;i++){
				for(j=0; $scope.bapCurrent.objectifsEntrantes[i].descriptions.length>j;j++){
					$scope.editObjectifSF[c]=false;
					c++;
				}
			}
			$scope.editResultatF= false;
			$scope.editRemarqueF= false;
			$scope.getBaps($scope.pageCurrent);
		}, function() {

		});
	}
}

function parametreCtrl($scope, $http, $location, $rootScope, $cookieStore) {
	$scope.today = function() {
		$scope.dt = new Date();
	};
	$scope.today();

	$scope.clear = function() {
		$scope.dt = null;
	};
	$scope.minDate = new Date(2008, 0, 1);

	$scope.maxDate = new Date();

	$scope.open = function($event) {
		$scope.status.opened = true;
	};
	$scope.setDate = function(year, month, day) {
		$scope.dt = new Date(year, month, day);
	};
	$scope.status = {
		opened : false
	};
	$scope.update = false;
	$http({
		method : 'GET',
		url : 'rest/admin/usernames'

	}).then(function(response) {
		$scope.usernames = response.data;
	}, function() {

	});
	$http({
		method : 'GET',
		url : 'rest/admin/managers'

	}).then(function(response) {
		$scope.managers = response.data;
	}, function() {

	});
	$scope.checkUsername = function() {

		$scope.errorUsername = true;

		for (i = 0; i < $scope.usernames.length; i++) {
			if ($scope.u.username == $scope.usernames[i]) {
				$scope.errorUsername = false;
				break;

			}
		}
		;
	};

	$scope.add = false;
	$scope.changeAdd = function() {
		switch ($scope.add) {
		case true:
			$scope.add = false;
			break;
		case false:
			$scope.add = true;
			$scope.u = null;
			break;
		}
		$scope.update = false;
	};
	$scope.addUser = function() {

		if ($scope.update) {
			switch ($scope.u.role) {
			case "ADMIN":
				$scope.u.type = 'ADM';
				break;
			case "MANAGER":
				$scope.u.type = 'MAG';
				break;
			case "COLLABORATEUR":
				$scope.u.type = 'COL';
				break;
			case "ENCADRANT":
				$scope.u.type = 'ENC';
				break;
			}
			$http({
				method : 'PUT',
				url : 'rest/admin/user',
				data : $scope.u
			}).then(function(response) {

				if (response.data.matricule == $rootScope.user.matricule) {
					$cookieStore.put('user', response.data);
					$rootScope.user = response.data;
				}
				$scope.getUsers($scope.pageCurrent);
				$scope.add = false;
				$scope.update = false;

			}, function() {

			});

		} else {
			if ($scope.errorUsername) {
				$scope.error = false;
				$scope.errorEmail = false;
				$scope.errorManager = false;
				if (!($scope.u.type == null)) {
					if ($scope.u.type == 'COL'
							&& $scope.managerMatricule == null) {
						$scope.errorManager = true;
					} else {
						$scope.u.password = '';
						$scope.u.dateRecrutement = $scope.dt;
						$http(
								{
									method : 'POST',
									url : 'rest/admin/user',
									data : $scope.u,
									params : {
										matricule : $scope.managerMatricule == null ? 0
												: $scope.managerMatricule
									}
								}).then(function(response) {
							$scope.getUsers($scope.pageCurrent);
							$scope.add = false;
							$scope.errorRole = false;
							$scope.u = null;
						}, function() {
							$scope.errorEmail = true;
						});
					}
				} else {
					$scope.errorRole = true;
				}
				;
			} else {
				$scope.error = true;
			}
		}
	}
	$scope.updateUser = function(user) {
		$scope.u = user;
		$scope.update = true;
		$scope.add = true;
	}
	$scope.getUsers = function(page) {
		if ($scope.mc) {
			$http({
				method : 'GET',
				url : 'rest/admin/userbymc',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				params : {
					mc : $scope.mc,
					page : page
				}
			}).then(function(response) {
				var users = response.data.content;
				$scope.users = users;
				$scope.last = response.data.last;
				$scope.first = response.data.first;
				$scope.pages = new Array(response.data.totalPages);
				$scope.pageCurrent = page;
			}, function() {

			});
		} else {
			$http({
				method : 'GET',
				url : 'rest/admin/users',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				params : {
					page : page
				}
			}).then(function(response) {
				var users = response.data.content;
				$scope.users = users;
				$scope.last = response.data.last;
				$scope.first = response.data.first;
				$scope.pages = new Array(response.data.totalPages);
				$scope.pageCurrent = page;
			}, function() {

			});
		}
		;
	};

	$scope.deleteUser = function(id) {
		$http({
			method : 'DELETE',
			url : 'rest/admin/user',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				id : id
			}
		}).then(function(response) {
			$scope.getUsers($scope.pageCurrent);
		}, function() {

		});
	};
	$scope.getUsers(0);
	$scope.goToUser = function(id) {
		$location.url('/admin/parametre/' + id);
	};
}
function parametreIdCtrl($scope, $stateParams, $http) {
	var id = $stateParams.id;
	$scope.getUser = function(matricule) {
		$http({
			method : 'GET',
			url : 'rest/admin/user',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				id : matricule
			}
		}).then(function(response) {
			$scope.u = response.data;
		}, function() {

		});
	};
	$scope.getUser(id);
}
function projetCtrl($scope, $http) {
	$scope.ajout = false;
	$scope.projet = {};
	$scope.updateProject = function(projet) {
		$scope.projet = projet;
		$scope.ajout = true;
	}
	$http({
		method : 'GET',
		url : 'rest/admin/encadrants'
	}).then(function(response) {
		$scope.encadrants = response.data;
	}, function() {

	});
	$scope.addProject = function() {
		$scope.projet.chefProjet.type = 'ENC';
		$http({
			method : 'POST',
			url : 'rest/admin/projet',
			data : $scope.projet
		}).then(function(response) {
			$scope.getProjects(0);
			$scope.projet = {};
			$scope.ajout = false;
		}, function() {

		});
	}
	$scope.getProjects = function(page) {
		if ($scope.mc) {
			$http({
				method : 'GET',
				url : 'rest/admin/projetbymc',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				params : {
					mc : $scope.mc,
					page : page
				}
			}).then(function(response) {
				var projets = response.data.content;
				$scope.projets = projets;
				$scope.last = response.data.last;
				$scope.first = response.data.first;
				$scope.pages = new Array(response.data.totalPages);
				$scope.pageCurrent = page;
			}, function() {

			});
		} else {
			$http({
				method : 'GET',
				url : 'rest/admin/projets',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				params : {
					page : page
				}
			}).then(function(response) {
				var projets = response.data.content;
				$scope.projets = projets;
				$scope.last = response.data.last;
				$scope.first = response.data.first;
				$scope.pages = new Array(response.data.totalPages);
				$scope.pageCurrent = page;
			}, function() {

			});

		}
	};
	$scope.getProjects(0);
	$scope.deleteProject = function(id) {
		$http({
			method : 'DELETE',
			url : 'rest/admin/projet',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				id : id
			}
		}).then(function(response) {
			$scope.getProjects($scope.pageCurrent);
		}, function() {

		});
	};
	$scope.ajouter = function() {
		switch ($scope.ajout) {
		case true:
			$scope.ajout = false;
			break;
		case false:
			$scope.ajout = true;
			break;
		}
	}
}
function loginCtrl($scope, $rootScope, $location, $cookieStore, $http, $state) {
	$rootScope.rememberMe = true;
	$scope.inClick = function() {
		delete $rootScope.error;
	}
	$scope.login = function() {
		$http({
			method : 'POST',
			url : 'user/authenticate',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			data : serializeData({
				username : $rootScope.username,
				password : $rootScope.password
			})
		}).then(function(response) {
			var user = response.data;
			if ($rootScope.rememberMe) {
				$cookieStore.put('user', user);
				$cookieStore.put('token', user.token);
			}
			;
			$rootScope.user = user;
			isConnected = true;
			$rootScope.redirectRole();
		}, function() {
			$rootScope.error = 'Login ou mot de passe incorrect !';
		});

	};
}
function feedbackCtrl($scope, $rootScope, $http) {
	$scope.libelles = [ "Productivite", "Qualité / Fiabilité", "Conception",
			"Avant-vente", "Gestion de projet", "Gestion de relation client",
			"Polyvalence" ];
	$scope.deleteFeedback = function(id) {
		$http({
			method : 'DELETE',
			url : 'rest/encadrant/feedback',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				idFeedback: id,
				matricule: $rootScope.user.matricule
			}
		}).then(function(response) {
			$scope.getFeedbacks($scope.pageCurrent);
		}, function() {

		});
	};
	$scope.detail= false;
	$scope.seeMore=function(feedback){
		$scope.feedback= feedback;
		$scope.detail=true;
	}
	$scope.changeDetail= function () {
		$scope.detail=false;
	}
	$scope.update=false;
	$scope.today = function() {
		$scope.finInter = new Date();
	};
	$scope.today();

	$scope.clear = function() {
		$scope.debutInter = null;
		$scope.finInter = null;
	};
	$scope.minDate = new Date(2008, 0, 1);

	$scope.maxDate = new Date();

	$scope.open1 = function($event) {
		$scope.status.opened1 = true;
	};
	$scope.open2 = function($event) {
		$scope.status.opened2 = true;
	};
	$scope.setDate = function(year, month, day) {
		$scope.debutInter = new Date(year, month, day);
	};
	$scope.setDate = function(year, month, day) {
		$scope.finInter = new Date(year, month, day);
	};
	$scope.status = {
		opened1 : false,
		opened2 : false
	};
	$scope.calculNombreJour = function() {
		$scope.nombreJ = Math.round(($scope.finInter - $scope.debutInter)
				/ (1000 * 60 * 60 * 24));
	}

	$scope.calculNoteGlobale = function() {
		$scope.f.nombreThemeCalifie = 0;
		$scope.f.totalPoids = 0;
		$scope.f.noteGlobal = 0;
		for (i = 0; 7 > i; i++) {
			try {
				if (!isNaN($scope.qualifications[i].qualification)) {
					$scope.f.nombreThemeCalifie++;
					$scope.f.totalPoids = parseInt($scope.qualifications[i].qualification)
							+ $scope.f.totalPoids;
					$scope.f.noteGlobal = $scope.f.totalPoids
							/ $scope.f.nombreThemeCalifie;
				}
			} catch (e) {

			}
		}

	}
	$scope.chargerProjets = function() {
		$scope.projets = [];
		for (i = 0; $scope.collaborateurs.length > i; i++) {
			if ($scope.collaborateurs[i].matricule == $scope.f.collaborateur.matricule) {
				for (j = 0; $scope.collaborateurs[i].projets.length > j; j++) {
					if ($scope.collaborateurs[i].projets[j].chefProjet.matricule == $rootScope.user.matricule) {
						$scope.projets
								.push($scope.collaborateurs[i].projets[j]);
						try {
							$scope.f.projet.idProjet = '';
						} catch (e) {
						}
					}
				}
			}
		}
	}
	
	$scope.add = false;
	$scope.changeAdd = function() {
		switch ($scope.add) {
		case true:
			$scope.add = false;
			$scope.f=null;
			$scope.qualifications=null;
			$scope.update=false;
			break;
		case false:
			$scope.add = true;
			$scope.f= null;
			break;
		}
	}
	$http({
		method : 'GET',
		url : 'rest/encadrant/collaborateurs',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		params : {
			matricule : $rootScope.user.matricule
		}
	}).then(function(response) {
		$scope.collaborateurs = response.data;
	}, function() {

	});
	$scope.isCollaborateurCurrent= function(id) {
		for(i=0; $scope.collaborateurs.length>=i; i++){
			if($scope.collaborateurs[i].matricule== id){
				return true;
			}
			
		}
		return false;
	}
	$scope.getFeedbacks = function(page) {
		$http({
			method : 'GET',
			url : 'rest/encadrant/feedbacks',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				page : page,
				matricule : $rootScope.user.matricule
			}
		}).then(function(response) {
			$scope.feedbacks = response.data.content;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
		}, function() {

		});
	}
	$scope.updateFeedback= function(feedback) {
		$scope.f= feedback;
		$scope.debutInter= feedback.debutInter;
		$scope.finInter= feedback.finInter;
		$scope.nombreJ= feedback.nombreJourValorise
		$scope.update= true;
		$scope.add=true;
	}
	$scope.getFeedbacks(0);
	$scope.addFeedback = function() {
			$rootScope.initialized=false;
			$scope.f.nombreThemeCalifie = $scope.f.nombreThemeCalifie == undefined ? 0
					: $scope.f.nombreThemeCalifie;
			$scope.f.totalPoids = $scope.f.totalPoids == undefined ? 0
					: $scope.f.totalPoids;
			$scope.f.noteGlobal = $scope.f.noteGlobal == undefined ? 0
					: $scope.f.noteGlobal;
			$scope.f.encadrant = $rootScope.user;
			$scope.f.encadrant.matricule = $rootScope.user.matricule;
			$scope.f.encadrant.type = "ENC";
			$scope.f.finInter = $scope.finInter;
			$scope.f.debutInter = $scope.debutInter;
			$scope.f.nombreJourValorise = $scope.nombreJ;
			$scope.f.collaborateur.type = "COL";
			$scope.f.qualifications = new Array();
			for (i = 0; $scope.libelles.length > i; i++) {
				try {
					$scope.qualifications[i].theme = $scope.libelles[i];
					$scope.f.qualifications.push($scope.qualifications[i]);
				} catch (e) {
				}
			}
			$http({
				method : 'POST',
				url : 'rest/encadrant/feedback',
				data : $scope.f
			}).then(function(response) {
				$scope.getFeedbacks(0);
				$scope.add = false;
				$scope.update=false;
				$scope.f=null;
				$scope.qualifications=null;
				$rootScope.initialized=true;
			}, function() {

			});

	}
}
function checkIsConnected($q, $timeout, $location, $state) {
	var deffered = $q.defer();
	if (isConnected) {
		$timeout(deffered.resolve, 0);
	} else {
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsNonConnected($q, $timeout, $location, $state, $rootScope,
		$cookieStore) {
	var deffered = $q.defer();
	if (isConnected) {
		$timeout(deffered.reject, 0);
		$rootScope.redirectRole();
	} else {

		$timeout(deffered.resolve, 0);
	}
	return deffered.promise;
}
function checkIsAdmin($q, $timeout, $location, $state, $rootScope) {
	var deffered = $q.defer();
	if (isConnected && $rootScope.user.role == 'ADMIN') {
		$timeout(deffered.resolve, 0);
	} else {
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsManager($q, $timeout, $location, $state, $rootScope) {
	var deffered = $q.defer();
	if (isConnected && $rootScope.user.role == 'MANAGER') {
		$timeout(deffered.resolve, 0);
	} else {
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsCollaborateur($q, $timeout, $location, $state, $rootScope) {
	var deffered = $q.defer();
	if (isConnected && $rootScope.user.role == 'COLLABORATEUR') {
		$timeout(deffered.resolve, 0);
	} else {
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsEncadrant($q, $timeout, $location, $state, $rootScope) {
	var deffered = $q.defer();
	if (isConnected && $rootScope.user.role == 'ENCADRANT') {
		$timeout(deffered.resolve, 0);
	} else {
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}

function serializeData(data) {
	if (!angular.isObject(data)) {
		return ((data == null) ? '' : data.toString());
	}
	var buffer = [];
	for ( var name in data) {
		if (!data.hasOwnProperty(name)) {
			continue;
		}
		var value = data[name];
		buffer.push(encodeURIComponent(name) + "="
				+ encodeURIComponent((value == null) ? "" : value));
	}
	var source = buffer.join("&").replace(/%20/g, "+");
	return (source);
}
function signatureHMAC(token, url) {
	var part = token.split(':');
	var hash = CryptoJS.HmacSHA1(url, part[2]);
	return part[0] + ':' + part[1] + ':' + hash;
}

