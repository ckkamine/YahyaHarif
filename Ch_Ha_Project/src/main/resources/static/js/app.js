var app= angular.module('myApp', ['ui.router', 'ngCookies']);
var isConnected=false;
app.config(['$stateProvider','$locationProvider', '$httpProvider', 
            function($stateProvider, $locationProvider, $httpProvider){
	
	$stateProvider.state('login',{
		url: '/login',
		templateUrl: 'partials/login.html',
		controller: loginCtrl,
		resolve:{
			connection: checkIsNonConnected
		}
	});
	$stateProvider.state('admin',{
		templateUrl: 'partials/admin/index.html',
		resolve:{
			connection: checkIsConnected,
			role: checkIsAdmin
		}
	});
	$stateProvider.state('admin.dashbord',{
		url: '/admin',
		controller: adminCtrl,
		templateUrl: 'partials/admin/dashbord.html'
		
	});
	$stateProvider.state('admin.parametre',{
		url: '/admin/parametre',
		controller: parametreCtrl,
		templateUrl: 'partials/admin/parametre.html'
		
	});
	$stateProvider.state('manager',{
		templateUrl: 'partials/manager/index.html',
		resolve:{
			connection: checkIsConnected,
			role: checkIsManager
		}
	});
	$stateProvider.state('manager.dashbord',{
		url: '/manager',
		controller: managerCtrl,
		templateUrl: 'partials/manager/dashbord.html'	
	});
	
	$stateProvider.state('collaborateur',{
		templateUrl: 'partials/collaborateur/index.html',
		resolve:{
			connection: checkIsConnected,
			role: checkIsCollaborateur
		}
	});
	$stateProvider.state('collaborateur.dashbord',{
		url: '/collaborateur',
		controller: adminCtrl,
		templateUrl: 'partials/collaborateur/dashbord.html'
		
	});
	$stateProvider.state('encadrant',{
		templateUrl: 'partials/encadrant/index.html',
		resolve:{
			connection: checkIsConnected,
			role: checkIsEncadrant
		}
	});
	$stateProvider.state('encadrant.dashbord',{
		url: '/encadrant',
		controller: managerCtrl,
		templateUrl: 'partials/encadrant/dashbord.html'	
	});
	
	
	$httpProvider.interceptors.push(function ($q, $rootScope, $location) {
        return {
        	'request': function(config) {
        		var isRestCall = config.url.indexOf('rest') == 0;
        		if (isRestCall && angular.isDefined($rootScope.user.token)) {
        			var authToken = $rootScope.user.token;
        			config.headers['X-Auth-Token'] = signatureHMAC(authToken, config.url);
        		}
        		return config || $q.when(config);
        	}
        };
    });

}]).run(function($rootScope, $location, $cookieStore, $http, $state) {
		
		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});
		
		
		$rootScope.logout = function() {
			delete $rootScope.user;
			$cookieStore.remove('user');
			isConnected= false;
			$location.url("/login");
			$rootScope.username='';
			$rootScope.password='';
		};
		$rootScope.redirectRole= function (){
			switch($rootScope.user.role){
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
		var today= new Date();
		$rootScope.date= today.getDate() + ' / '+ (today.getMonth()+1);
		
		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		
		var user = $cookieStore.get('user');
		if (user !== undefined) {
			isConnected= true;
			$rootScope.user= user;
			$location.path(originalPath);
			
		}else{
			$location.url('/login');
		}
		
		$rootScope.initialized = true;
	});
function adminCtrl($scope){
	
}
function managerCtrl($scope){
	
}
function parametreCtrl($scope){
	
}

function loginCtrl($scope, $rootScope, $location, $cookieStore, $http, $state){
	$rootScope.rememberMe= true;
	$scope.inClick= function(){
		delete $rootScope.error;
	}
	$scope.login = function() {
		$http({
			  method: 'POST',
			  url: 'user/authenticate',
			  headers : {'Content-Type': 'application/x-www-form-urlencoded'},
			  data: serializeData({username: $rootScope.username, password: $rootScope.password})
			}).then(function (response) {
				var user= response.data;
				if ($rootScope.rememberMe) {
					$cookieStore.put('user', user);
				};
				$rootScope.user= user;
				isConnected= true;
				$rootScope.redirectRole(user.role);
			  }, function () {
				  $rootScope.error= 'Login ou mot de passe incorrect !';
			  });

		
	};
}



function checkIsConnected($q, $timeout, $location, $state){
	var deffered= $q.defer();
	if(isConnected){
		$timeout(deffered.resolve, 0);
	}else{
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsNonConnected($q, $timeout, $location, $state, $rootScope){
	var deffered= $q.defer();
	if(isConnected){
		$timeout(deffered.reject, 0);
		
		$rootScope.redirectRole();
	}else{
		$timeout(deffered.resolve, 0);
	}
	return deffered.promise;
}
function checkIsAdmin($q, $timeout, $location, $state, $rootScope){
	var deffered= $q.defer();
	if(isConnected && $rootScope.user.role=='ADMIN'){
		$timeout(deffered.resolve, 0);
	}else{
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsManager($q, $timeout, $location, $state, $rootScope){
	var deffered= $q.defer();
	if(isConnected && $rootScope.user.role=='MANAGER'){
		$timeout(deffered.resolve, 0);
	}else{
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsCollaborateur($q, $timeout, $location, $state, $rootScope){
	var deffered= $q.defer();
	if(isConnected && $rootScope.user.role=='COLLABORATEUR'){
		$timeout(deffered.resolve, 0);
	}else{
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}
function checkIsEncadrant($q, $timeout, $location, $state, $rootScope){
	var deffered= $q.defer();
	if(isConnected && $rootScope.user.role=='ENCADRANT'){
		$timeout(deffered.resolve, 0);
	}else{
		$timeout(deffered.reject, 0);
		$location.url('/login');
	}
	return deffered.promise;
}

function serializeData(data){
	if(! angular.isObject(data)){
		return ((data==null)? '': data.toString());
	}
	var buffer= [];
	for(var name in data){
		if(!data.hasOwnProperty(name)){
			continue;
		}
		var value= data[name];
		buffer.push(encodeURIComponent(name)+"="+encodeURIComponent((value==null)? "":value));
	}
	var source= buffer.join("&").replace(/%20/g,"+");
	return (source);
}
function signatureHMAC(token, url){
	var part= token.split(':');
	var hash= CryptoJS.HmacSHA1(url, part[2]);
return part[0]+':'+part[1]+':'+hash;
}


