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
								controller : dashbordCtrl,
								templateUrl : 'partials/admin/dashbord.html'
							});

							$stateProvider.state('admin.user', {
								url : '/admin/profile',
								templateUrl : 'partials/user/profile.html',
								controller : adminProfileCtrl
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
								templateUrl : 'partials/admin/archive.html',
								controller : archiveCtrl
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
								controller : dashbordCtrl,
								templateUrl : 'partials/manager/dashbord.html'
							});
							$stateProvider.state('manager.user', {
								url : '/manager/profile',
								templateUrl : 'partials/user/profile.html',
								controller : adminProfileCtrl
							});
							$stateProvider.state('manager.bilan', {
								url : '/manager/bilan',
								templateUrl : 'partials/manager/bilan.html'
							});
							$stateProvider.state('manager.bap', {
								url : '/manager/bilan/bap',
								templateUrl : 'partials/admin/bap.html',
								controller : bapCtrl
							});
							$stateProvider.state('manager.archive', {
								url : '/manager/archives',
								templateUrl : 'partials/admin/archive.html',
								controller : archiveCtrl
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
												templateUrl : 'partials/collaborateur/dashbord.html',
												controller : dashbordColCtrl

											});
							$stateProvider
									.state(
											'collaborateur.newObjectifs',
											{
												url : '/collaborateur/nouveaux-objectifs',
												templateUrl : 'partials/collaborateur/newObjectifs.html',
												controller : newObjectifs

											});
							$stateProvider
									.state(
											'collaborateur.currentObjectifs',
											{
												url : '/collaborateur/objectifs',
												templateUrl : 'partials/collaborateur/currentObjectifs.html',
												controller : currentObjectifs

											});
							$stateProvider
									.state(
											'collaborateur.bap',
											{
												url : '/collaborateur/bap',
												templateUrl : 'partials/collaborateur/bap.html',
												controller : bapColCtrl

											});
							$stateProvider
									.state(
											'collaborateur.feedbacks',
											{
												url : '/collaborateur/feedbacks',
												templateUrl : 'partials/collaborateur/feedbacks.html',
												controller : feedbacksColCtrl
											});
							$stateProvider
									.state(
											'collaborateur.archive',
											{
												url : '/collaborateur/archive',
												templateUrl : 'partials/collaborateur/archive.html'

											});
							$stateProvider
									.state(
											'collaborateur.archiveBaps',
											{
												url : '/collaborateur/archive/baps',
												templateUrl : 'partials/collaborateur/archiveBaps.html',
												controller : archiveBapsCtrl

											});
							$stateProvider
									.state(
											'collaborateur.archiveObjectifs',
											{
												url : '/collaborateur/archive/objectifs',
												templateUrl : 'partials/collaborateur/archiveObjectifs.html',
												controller : archiveObjectifsCtrl

											});
							$stateProvider
									.state(
											'collaborateur.archiveFeedbacks',
											{
												url : '/collaborateur/archive/feedbacks',
												templateUrl : 'partials/collaborateur/archiveFeedbacks.html',
												controller : archiveFeedbacksCtrl

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
												templateUrl : 'partials/encadrant/dashbord.html',
												controller : dashbordEncCtrl
											});
							$stateProvider
									.state(
											'encadrant.feedbacks',
											{
												url : '/encadrant/feedbacks',
												controller : feedbackCtrl,
												templateUrl : 'partials/encadrant/feedbacks.html'
											});
							$stateProvider
									.state(
											'encadrant.archiveFeedbacks',
											{
												url : '/encadrant/archive/feedbacks',
												controller : archiveFeedbackCtrl,
												templateUrl : 'partials/encadrant/archiveFeedbacks.html'
											});
							$stateProvider.state('collaborateur.user', {
								url : '/collaborateur/profile',
								templateUrl : 'partials/user/profile2.html',
								controller : adminProfileCtrl
							});
							$stateProvider.state('encadrant.user', {
								url : '/encadrant/profile',
								templateUrl : 'partials/user/profile2.html',
								controller : adminProfileCtrl
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
					$rootScope.initialized = false;
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
function adminProfileCtrl($scope, $http, $rootScope) {
	var role = $rootScope.user.role.toLowerCase();
	switch (role) {
	case 'manager':
		var manager = angular.element(document.querySelectorAll('li.manager'));
		manager.removeClass("active active-manager");
		break;
	case 'admin':
		var admin = angular.element(document.querySelectorAll('li.admin'));
		admin.removeClass("active");
	case 'collaborateur':
		var collaborateur = angular.element(document
				.querySelectorAll('li.collaborateur'));
		collaborateur.removeClass("active active-user");
	case 'encadrant':
		var encadrant = angular.element(document
				.querySelectorAll('li.encadrant'));
		encadrant.removeClass("active active-encadrant");
	}
	$scope.errorSaisie = false;
	$scope.changePasswordF = false;
	$scope.isValid = false;

	$scope.change = function() {
		$scope.changePasswordF = true;
	}
	$scope.retour = function() {
		$scope.changePasswordF = false;
	}
	$scope.checkPassword = function() {
		$http({
			method : 'POST',
			url : 'user/authenticate',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			data : serializeData({
				username : $rootScope.user.username,
				password : $scope.password
			})
		}).then(function(response) {
			$scope.isValid = true;
		}, function() {
			$rootScope.logout();
		});

	};
	$scope.checkIsValid = function() {
		$scope.tap = true;

		if ($scope.newPassword === $scope.newPassword2) {
			$scope.errorSaisie = false;
		} else {
			$scope.errorSaisie = true;
		}
	}
	$scope.changePassword = function() {

		if (!$scope.errorSaisie && $scope.isValid) {
			$http({
				method : 'POST',
				url : 'user/updatepassword',
				headers : {
					'Content-Type' : 'application/x-www-form-urlencoded'
				},
				data : serializeData({
					matricule : $rootScope.user.matricule,
					password : $scope.newPassword
				})
			}).then(function(response) {
				$scope.changePasswordF = false;
				$scope.tape = false;
				$scope.errorSaisie = false;
				$scope.isValid = false;
			}, function() {

			});
		}

	};

}

function archiveCtrl($scope, $http, $rootScope) {
	var role = $rootScope.user.role.toLowerCase();
	var archive = angular.element(document.querySelector('#archive'));
	if (role == 'manager') {
		var manager = angular.element(document.querySelectorAll('li.manager'));
		manager.removeClass("active active-manager");
		archive.addClass("active active-manager");
	}
	if (role == 'admin') {
		var admin = angular.element(document.querySelectorAll('li.admin'));
		admin.removeClass("active");
		archive.addClass("active");

	}
	$scope.detail = false;
	$scope.feedbackF = false;
	$scope.tab = 0;
	$scope.seeMore = function(b) {
		$scope.bapCurrent = b;
		$scope.detail = true;
	}
	$scope.seeMoreFeedback = function(f) {
		$scope.feedback = f;
		$scope.feedbackF = true;
	}
	$scope.retour = function() {
		$scope.detail = false;
	}
	$scope.retourFeedback = function() {
		$scope.feedbackF = false;
	}
	$scope.getBaps = function(page) {
		$http({
			method : 'GET',
			url : 'rest/' + role + '/archives',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				page : page,
				matricule : $rootScope.user.matricule
			}
		}).then(function(response) {
			$scope.baps = response.data.content;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.getBaps(0);
}
function dashbordCtrl($scope, $http, $rootScope) {
	var role = $rootScope.user.role.toLowerCase();
	$http({
		url : 'rest/' + role + '/enattenten',
		method : 'GET',
		params : {
			matricule : $rootScope.user.matricule
		},
		transformResponse : [ function(data) {
			$scope.enattente = data;
			return data;
		} ]
	});
	$http({
		url : 'rest/' + role + '/encoursn',
		method : 'GET',
		params : {
			matricule : $rootScope.user.matricule
		},
		transformResponse : [ function(data) {
			$scope.encours = data;
			return data;
		} ]
	});
	$http({
		url : 'rest/' + role + '/rejeten',
		method : 'GET',
		params : {
			matricule : $rootScope.user.matricule
		},
		transformResponse : [ function(data) {
			$scope.rejete = data;
			return data;
		} ]
	});
}
function archiveFeedbackCtrl($scope, $http, $rootScope) {
	$scope.detail = false;
	$scope.changeDetail = function() {
		$scope.detail = false;
	}
	$scope.seeMore = function(f) {
		$scope.feedback = f;
		$scope.detail = true;
	}
	$scope.getFeedbacks = function(page) {
		$http({
			method : 'GET',
			url : 'rest/encadrant/feedbacksa',
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
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.getFeedbacks(0);
}
function dashbordEncCtrl($scope, $http, $rootScope) {
	$http({
		url : 'rest/encadrant/nombre',
		method : 'GET',
		params : {
			matricule : $rootScope.user.matricule
		},
		transformResponse : [ function(data) {
			$scope.feedbacks = data;
			return data;
		} ]
	});
}
function archiveFeedbacksCtrl($http, $rootScope, $scope) {
	$scope.detail = false;
	$scope.getFeedbacks = function(page) {
		$http({
			method : 'GET',
			url : 'rest/collaborateur/feedbacksa',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				matricule : $rootScope.user.matricule,
				page : page
			}
		}).then(function(response) {
			var feedbacks = response.data.content;
			$scope.feedbacks = feedbacks;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.getFeedbacks(0);
	$scope.seeMore = function(f) {
		$scope.feedback = f;
		$scope.detail = true;
	}
	$scope.retour = function() {
		$scope.detail = false;
	}
}
function archiveBapsCtrl($http, $scope, $rootScope) {
	$scope.detail = false;
	$scope.getBaps = function(page) {
		$http({
			method : 'GET',
			url : 'rest/collaborateur/bapa',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				matricule : $rootScope.user.matricule,
				page : page
			}
		}).then(function(response) {
			var baps = response.data.content;
			$scope.baps = baps;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.getBaps(0);
	$scope.seeMore = function(b) {
		$scope.bapCurrent = b;
		$scope.detail = true;
	}
	$scope.retour = function() {
		$scope.detail = false;
	}
}
function archiveObjectifsCtrl($http, $scope, $rootScope) {
	$scope.getObjectifs = function(page) {
		$http({
			method : 'GET',
			url : 'rest/collaborateur/objectifsa',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				matricule : $rootScope.user.matricule,
				page : page
			}
		}).then(function(response) {
			var objectifs = response.data.content;
			$scope.objectifs = objectifs;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.getObjectifs(0);
}
function feedbacksColCtrl($http, $rootScope, $scope) {
	$scope.detail = false;
	$http({
		method : 'GET',
		url : 'rest/collaborateur/bapc',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		params : {
			matricule : $rootScope.user.matricule
		}
	}).then(function(response) {
		$scope.feedbacks = response.data.feedbacks;
	}, function() {

	});
	$scope.seeMore = function(f) {
		$scope.feedback = f;
		$scope.detail = true;
	}
	$scope.changeDetail = function() {
		$scope.detail = false;
	}
}
function bapColCtrl($http, $rootScope, $scope) {
	$http({
		method : 'GET',
		url : 'rest/collaborateur/bapc',
		headers : {
			'Content-Type' : 'application/x-www-form-urlencoded'
		},
		params : {
			matricule : $rootScope.user.matricule
		}
	}).then(function(response) {
		$scope.bapCurrent = response.data;
	}, function() {

	});
}
function dashbordColCtrl($scope, $http, $rootScope) {
	$http({
		url : 'rest/collaborateur/status',
		method : 'GET',
		params : {
			matricule : $rootScope.user.matricule
		},
		transformResponse : [ function(data) {
			$scope.bap = data;
			return data;
		} ]
	});
}
function newObjectifs($scope, $http, $rootScope) {
	$http({
		url : 'rest/collaborateur/compteur',
		method : 'GET',
		params : {
			matricule : $rootScope.user.matricule
		},
		transformResponse : [ function(data) {
			$scope.compteur = data;
			return data;
		} ]
	});
	$scope.getNewObjectifs = function() {
		$http({
			method : 'GET',
			url : 'rest/collaborateur/objectifsn',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				matricule : $rootScope.user.matricule
			}
		}).then(function(response) {
			$scope.objectifs = response.data;
		}, function() {

		});
	}
	$scope.getNewObjectifs();
	$scope.validerOrRefuser = function(idObjectif) {
		$http({
			method : 'POST',
			url : 'rest/collaborateur/validerorrefuser',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				idObjectif : idObjectif
			}
		}).then(function(response) {
			$scope.getNewObjectifs();
		}, function() {

		});
	}
	$scope.envoyer = function() {
		$http({
			method : 'POST',
			url : 'rest/collaborateur/envoyer',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				matricule : $rootScope.user.matricule
			}
		}).then(function(response) {
			$scope.getNewObjectifs();
		}, function() {

		});

	}
}
function currentObjectifs($http, $scope, $rootScope, $state) {
	$scope.getObjectifs = function(page) {
		$http({
			method : 'GET',
			url : 'rest/collaborateur/objectifsc',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				matricule : $rootScope.user.matricule,
				page : page
			}
		}).then(function(response) {
			var objectifs = response.data.content;
			$scope.objectifs = objectifs;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
		}, function() {

		});
	}
	$scope.getObjectifs(0);
}
function bapCtrl($scope, $http, $rootScope) {
	var role = $rootScope.user.role.toLowerCase();
	var bilan = angular.element(document.querySelector('#bilan'));
	if (role == 'manager') {
		var manager = angular.element(document.querySelector('.manager'));
		manager.removeClass(' active active-manager');
		bilan.addClass('active active-manager');
	}
	if (role == 'admin') {
		var admin = angular.element(document.querySelector('.admin'));
		admin.removeClass('active ');
		bilan.addClass('active ');
	}
	$scope.errorSomme = false;
	$scope.tab = 0;
	$scope.descriptions = [];
	$scope.feedbackF = false;
	$scope.calculeSomme = function() {
		$scope.error = '';
		$scope.somme = 0;
		for (i = 0; $scope.bapCurrent.objectifsSortantes.length > i; i++) {
			for (j = 0; $scope.bapCurrent.objectifsSortantes[i].descriptions.length > j; j++) {
				$scope.somme += $scope.bapCurrent.objectifsSortantes[i].descriptions[j].poids;
			}
		}
		for (i = 0; $scope.descriptions.length > i; i++) {
			$scope.somme += $scope.descriptions[i].poids;
		}
		if ($scope.somme == 100)
			$scope.errorSomme = false;
		if ($scope.somme !== 100)
			$scope.errorSomme = true;

	}

	$scope.ajusterPoids = function() {
		$scope.error = '';
		$scope.descriptions = [];
		$scope.calculeSomme();
	}
	$scope.ajouterUneAutre = function() {
		if ($scope.description.description !== null
				&& $scope.description.mesure !== null
				&& $scope.description.responsable.matricule !== null) {
			$scope.descriptions.push($scope.description);
			for (i = 0; $scope.encadrants.length > i; i++) {
				if ($scope.encadrants[i].matricule == $scope.description.responsable.matricule) {
					$scope.encadrants.splice(i, 1);
				}
			}
			$scope.description = {};
		}
	}

	$scope.editResultatF = false;
	$scope.editResultat = function() {
		$scope.editResultatF = true;
	}
	$scope.editRemarqueF = false;
	$scope.editRemarque = function() {
		$scope.editRemarqueF = true;
	}
	$scope.clearDecision = function() {
		$scope.bapCurrent.decision = '';
	}

	$scope.detail = false;
	$scope.ajuster = false;
	$scope.getEncadrants = function() {
		$http({
			method : 'GET',
			url : 'rest/' + role + '/encadrants'
		}).then(function(response) {
			$scope.encadrants = response.data;
		}, function() {

		});
	}
	$scope.getEncadrants();
	$scope.editerDescription = function(d) {
		$scope.encadrants.push($scope.descriptions[d].responsable);
		$scope.descriptions.splice(d, 1);
	}
	$scope.getBap = function(idBap) {
		$http({
			method : 'GET',
			url : 'rest/' + role + '/bilan',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				id : idBap
			}
		}).then(function(response) {
			$scope.bapCurrent = response.data;
			$scope.tab = 0;
		}, function() {

		});
	}
	$scope.getBaps = function(page) {
		$http({
			method : 'GET',
			url : 'rest/' + role + '/baps',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				page : page,
				matricule : $rootScope.user.matricule

			}
		}).then(function(response) {
			$scope.baps = response.data.content;
			$scope.last = response.data.last;
			$scope.first = response.data.first;
			$scope.pages = new Array(response.data.totalPages);
			$scope.pageCurrent = page;
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.addObjectif = function() {
		$scope.errorDescription = false;

		try {
			if ($scope.description.description !== null
					&& $scope.description.mesure !== null
					&& $scope.description.responsable.matricule !== null) {
				$scope.ajouterUneAutre();
			}
		} catch (e) {

		}
		if ($scope.descriptions.length == 0)
			$scope.errorDescription = true;

		if ($scope.errorDescription == false) {
			try {
				for (i = 0; $scope.descriptions.length > i; i++) {
					$scope.descriptions[i].responsable.type = 'ENC';
					$scope.descriptions[i].responsable.user = '';
					$scope.descriptions[i].responsable.password = '';
				}
			} catch (e) {
			}
			$scope.objectif.descriptions = new Array();
			$scope.objectif.descriptions = $scope.descriptions;
			$scope.objectif.employe = $scope.bapCurrent.collaborateur;
			$scope.objectif.dateCreation = new Date();

			$http({
				method : 'POST',
				url : 'rest/' + role + '/objectif',
				params : {
					idBap : $scope.bapCurrent.id
				},
				data : $scope.objectif
			}).then(function(response) {
				$scope.descriptions = [];
				$scope.description = {};
				$scope.objectif = {};
				$rootScope.reload();
			}, function() {

			});
		}
	}
	$scope.getBaps(0);
	$scope.seeMore = function(bap) {
		$scope.bapCurrent = bap;
		$scope.detail = true;
		$scope.calculeSomme();
	}
	$scope.retour = function() {
		$scope.detail = false;
		$scope.getBaps($scope.pageCurrent);
	}

	$scope.calculNoteGlobale = function() {
		$scope.bapCurrent.noteGlobale = 0;
		for (i = 0; $scope.bapCurrent.objectifsEntrantes.length > i; i++) {
			for (j = 0; $scope.bapCurrent.objectifsEntrantes[i].descriptions.length > j; j++) {
				$scope.bapCurrent.noteGlobale += $scope.bapCurrent.objectifsEntrantes[i].descriptions[j].note;
			}
		}
	}

	$scope.updateBap = function() {
		$scope.bapCurrent.type = 'BAP';
		$http({
			method : 'PUT',
			url : 'rest/' + role + '/bilan',
			data : $scope.bapCurrent
		}).then(function(response) {
			$scope.bapCurrent = response.data;
			$scope.editResultatF = false;
			$scope.editRemarqueF = false;
			$scope.getBaps($scope.pageCurrent);
		}, function() {

		});
	}
	$scope.updatePoids = function() {
		if ($scope.somme !== 100) {
			$scope.errorSomme = true;
		} else {
			$scope.updateBap();
			$scope.ajuster = false;
			$scope.errorSomme = false;
		}
	}
	$scope.updateObjectif = function(o) {
		$scope.error = '';
		$scope.editObjectif = true;
		$scope.objectif = o;
		$scope.tab = 1;
	}
	$scope.updateObjectif2 = function() {

		if ($scope.errorSomme == false) {
			for (i = 0; $scope.bapCurrent.objectifsSortantes.length > i; i++) {
				if ($scope.bapCurrent.objectifsSortantes[i].idObjectif == $scope.objectif.idObjectif) {
					$scope.bapCurrent.objectifsSortantes[i] = $scope.objectif;
					$scope.updateBap();
					$scope.tab = 0;
					$scope.objectif = {};
					$scope.editObjectif = false;
					break;
				}
			}
		}
	}
	$scope.tab1 = function() {
		$scope.tab = 1;
		$scope.editObjectif = false;
		$scope.objectif = {};
	}
	$scope.deleteObjectif = function(id) {
		$http({
			method : 'DELETE',
			url : 'rest/' + role + '/objectif',
			params : {
				id : id
			}
		}).then(function(response) {
			$scope.getBap($scope.bapCurrent.id);
			$scope.calculeSomme();
		}, function() {

		});
	}
	$scope.retourFeedback = function() {
		$scope.feedbackF = false;
	}
	$scope.seeMoreFeedback = function(f) {
		$scope.feedback = f;
		$scope.feedbackF = true;
	}
	$scope.sendObjectif = function() {
		$scope.calculeSomme();
		if ($scope.somme == 100) {
			$http({
				method : 'PUT',
				url : 'rest/' + role + '/envoyer',
				params : {
					id : $scope.bapCurrent.id
				}
			}).then(function(response) {
				$scope.bapCurrent = response.data;

			}, function() {

			});
		} else {
			$scope.error = 'La somme des poids est differente de 100%';
		}
	}
	$scope.lockOrUnlockBap = function() {
		if ($rootScope.user.matricule == $scope.bapCurrent.manager.matricule) {
			$http({
				method : 'PUT',
				url : 'rest/' + role + '/lock',
				params : {
					id : $scope.bapCurrent.id
				}
			}).then(function(response) {
				$scope.bapCurrent = response.data;

			}, function() {

			});
		}

	}
	$scope.validerBap = function(idBap) {

		$http({
			method : 'POST',
			url : 'rest/' + role + '/valider',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				idBap : idBap
			}
		}).then(function(response) {
			$scope.getBaps(0);
		}, function() {

		});
	}
	$scope.annulerBap = function(idBap) {

		$http({
			method : 'POST',
			url : 'rest/' + role + '/annuler',
			headers : {
				'Content-Type' : 'application/x-www-form-urlencoded'
			},
			params : {
				idBap : idBap
			}
		}).then(function(response) {
			$scope.getBaps(0);
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
	$rootScope.titre = 'Login';
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
			$rootScope.username = '';
			password: $rootScope.password = '';
			var user = response.data;

			$cookieStore.put('user', user);
			$cookieStore.put('token', user.token);

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
				idFeedback : id,
				matricule : $rootScope.user.matricule
			}
		}).then(function(response) {
			$scope.getFeedbacks($scope.pageCurrent);
		}, function() {

		});
	};
	$scope.detail = false;
	$scope.seeMore = function(feedback) {
		$scope.feedback = feedback;
		$scope.detail = true;
	}
	$scope.changeDetail = function() {
		$scope.detail = false;
	}
	$scope.update = false;
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

	$scope.getCollaborateurs = function() {
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
	}
	$scope.getCollaborateurs();
	$scope.changeAdd = function() {
		switch ($scope.add) {
		case true:
			$scope.add = false;
			$scope.f = null;
			$scope.qualifications = null;
			$scope.update = false;
			break;
		case false:
			$scope.getCollaborateurs();
			$scope.add = true;
			$scope.f = null;
			break;
		}
	}
	$scope.isCollaborateurCurrent = function(id) {
		for (i = 0; $scope.collaborateurs.length >= i; i++) {
			if ($scope.collaborateurs[i].matricule == id) {
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
			$scope.vide = response.data.totalPages;
		}, function() {

		});
	}
	$scope.updateFeedback = function(feedback) {
		$scope.f = feedback;
		$scope.debutInter = feedback.debutInter;
		$scope.finInter = feedback.finInter;
		$scope.nombreJ = feedback.nombreJourValorise
		$scope.update = true;
		$scope.add = true;
	}
	$scope.getFeedbacks(0);
	$scope.addFeedback = function() {
		$rootScope.initialized = false;
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
			$scope.update = false;
			$scope.f = null;
			$scope.qualifications = null;
			$rootScope.initialized = true;
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
