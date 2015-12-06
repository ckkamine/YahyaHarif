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
	$stateProvider.state('index',{
		url: '/',
		template: '<h1> Test </h1>',
		//controller: loginCtrl,
		resolve:{
			connection: checkIsConnected
		}
	});
	
	$httpProvider.interceptors.push(function ($q, $rootScope, $location) {
        return {
        	'request': function(config) {
        		var isRestCall = config.url.indexOf('rest') == 0;
        		if (isRestCall && angular.isDefined($rootScope.user.token)) {
        			var authToken = $rootScope.user.token;
        			config.headers['X-Auth-Token'] = authToken;
        		}
        		return config || $q.when(config);
        	}
        };
    });
	$httpProvider.interceptors.push(function ($q, $rootScope, $location) {
        return {
        	'responseError': function(rejection) {
        		var status = rejection.status;
        		var config = rejection.config;
        		if (status == 401) {
        			$location.path( "/login" );
        			
        		}
        		if(status == 403){
        			if(config.url.indexOf('rest') == 0){
        				$rootScope.error= 'Vous pouvez pas acceder à cette ressource !';
        			}
        			$rootScope.error= 'Veuillez resaisir votre login et Mdp !';
        		}
              
        		return $q.reject(rejection);
        	}
        };
    }
);
}]).run(function($rootScope, $location, $cookieStore, $http, $state) {
		
		/* Reset error when a new view is loaded */
		$rootScope.$on('$viewContentLoaded', function() {
			delete $rootScope.error;
		});
		$rootScope.loginPage= function(){
			$state.go('login');
		}
		
		$rootScope.logout = function() {
			delete $rootScope.user;
			$cookieStore.remove('user');
			isConnected= false;
			$location.path("/login");
		};
		
		 /* Try getting valid user from cookie or go to login page */
		var originalPath = $location.path();
		
		var user = $cookieStore.get('user');
		if (user !== undefined) {
			isConnected= true;
			$rootScope.user= user;
			$location.path(originalPath);
			
		}
		
		$rootScope.initialized = true;
	});


function loginCtrl($scope, $rootScope, $location, $cookieStore, $http){
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
			}).then(function (data) {
				var user= data;
				if ($scope.rememberMe) {
					$cookieStore.put('user', user);
				}
				$rootScope.user= user;
				isConnected= true;
				$location.url('/'); 
			  }, function () {
				  $rootScope.error= 'Login ou mdp incorrect ';
			  });

		
	};
}

function indexCtrl($scope, $http){
	$scope.test= isConnected;
	$scope.testToken= function(){
		$http.get('rest/test').success(function(data){
			
		});
	}
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
function checkIsNonConnected($q, $timeout, $location, $state){
	var deffered= $q.defer();
	if(isConnected){
		$timeout(deffered.reject, 0);
		$location.url('/');
	}else{
		$timeout(deffered.resolve, 0);
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

