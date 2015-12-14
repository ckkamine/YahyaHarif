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
												controller : adminCtrl,
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
												controller : managerCtrl,
												templateUrl : 'partials/encadrant/dashbord.html'
											});

							$httpProvider.interceptors
									.push(function($q, $rootScope, $location,
											$cookieStore) {
										return {
											'request' : function(config) {
												var isRestCall = config.url
														.indexOf('rest') == 0;
												if (isRestCall
														&& angular.isDefined($cookieStore.get('token'))) {
													var authToken = $cookieStore.get('token');
													config.headers['X-Auth-Token'] = signatureHMAC(
															authToken,
															config.url);
												}
												return config
														|| $q.when(config);
											}
										};
									});

						} ])
		.run(function($rootScope, $location, $cookieStore, $http, $state) {
			/* Try getting valid user from cookie or go to login page */
			var originalPath = $location.path();
			var user = $cookieStore.get('user');
			if (user !== undefined) {
				
					
					$rootScope.user = user;
					isConnected = true;
					$location.path(originalPath);

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

			var today = new Date();

			$rootScope.date = today.getDate() + ' / ' + (today.getMonth() + 1);

			
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
				};


			};
			$rootScope.initialized = true;
		});
function adminCtrl($scope) {

}
function managerCtrl($scope) {

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
		url : 'rest/admin/usernames',

	}).then(function(response) {
		$scope.usernames = response.data;
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
			$scope.u= null;
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
			
				if(response.data.matricule== $rootScope.user.matricule){
					$cookieStore.put('user', response.data);
					$rootScope.user= response.data;
				}
				$scope.getUsers($scope.pageCurrent);
				$scope.add = false;
				$scope.update = false;
				
			}, function() {

			});
			
		} else {
			if ($scope.errorUsername) {
				$scope.error = false;
				$scope.errorEmail=false;
				if (!($scope.u.type == null)) {
					$scope.u.password = '';
					$scope.u.dateRecrutement = $scope.dt;
					$http({
						method : 'POST',
						url : 'rest/admin/user',
						data : $scope.u
					}).then(function(response) {
						$scope.getUsers($scope.pageCurrent);
						$scope.add = false;
						$scope.errorRole = false;
						$scope.u=null;
					}, function() {
						$scope.errorEmail= true;
					});
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
		}).then(
				function(response) {
					var user = response.data;
					$scope.u = user;
					var date = new Date($scope.u.dateRecrutement);
					$scope.dateRecrutement = date.getDate() + ' / '
							+ (date.getMonth() + 1) + ' / '
							+ date.getFullYear();

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
function checkIsNonConnected($q, $timeout, $location, $state, $rootScope, $cookieStore) {
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
