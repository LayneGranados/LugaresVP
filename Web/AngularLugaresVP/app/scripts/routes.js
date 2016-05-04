function config($locationProvider, $routeProvider) {
  'use strict';
  $locationProvider.html5Mode(true);
  $routeProvider
    .when('/', {
		controller:'LoginController',
		controllerAs:'controller',
		templateUrl: 'views/login.html'
    }).when('/actividad', {
      controller: 'ActividadController',
      controllerAs: 'controller',
      templateUrl: 'views/actividad.html'
    }).when('/actividadTipoLugar', {
      templateUrl: 'views/actividadTipoLugar.html'
    }).when('/calificacionActividad', {
      controller: 'CalificacionActividadController',
      controllerAs: 'controller',
      templateUrl: 'views/calificacionActividad.html'
    }).when('/empleado', {
      controller: 'EmpleadoController',
      controllerAs: 'controller',
      templateUrl: 'views/empleado.html'
    }).when('/evaluacion', {
      templateUrl: 'views/evaluacion.html'
    }).when('/login', {
      controller:'LoginController',
      controllerAs:'controller',
      templateUrl: 'views/login.html'
    }).when('/lugar', {
      controller: 'LugarController',
      controllerAs: 'controller',
      templateUrl: 'views/lugar.html'
    }).when('/supervision', {
      controller: 'SupervisionController',
      controllerAs: 'controller',
      templateUrl: 'views/supervision.html'
    }).when('/tipoIdentificacion', {
      templateUrl: 'views/tipoIdentificacion.html'
    }).when('/tipo-lugar', {
      templateUrl: 'views/tipoLugar.html'
    }).when('/tipo-empleado', {
      controller: 'TipoEmpleadoController',
      controllerAs: 'controller',
      templateUrl: 'views/tipoEmpleado.html'
    });
}
angular.module('blog').config(config);
