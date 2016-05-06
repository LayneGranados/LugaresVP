function config($locationProvider, $routeProvider) {
  'use strict';
  $locationProvider.html5Mode(true);
  $routeProvider
    .when('/', {
		controller:'LoginController',
		controllerAs:'controller',
		templateUrl: 'templates/login.html'
    }).when('/actividad', {
      controller: 'ActividadController',
      controllerAs: 'controller',
      templateUrl: 'templates/actividad.html'
    }).when('/actividadTipoLugar', {
      templateUrl: 'templates/actividadTipoLugar.html'
    }).when('/calificacionActividad', {
      controller: 'CalificacionActividadController',
      controllerAs: 'controller',
      templateUrl: 'templates/calificacionActividad.html'
    }).when('/empleado', {
      controller: 'EmpleadoController',
      controllerAs: 'controller',
      templateUrl: 'templates/empleado.html'
    }).when('/evaluacion', {
      templateUrl: 'templates/evaluacion.html'
    }).when('/login', {
      controller:'LoginController',
      controllerAs:'controller',
      templateUrl: 'templates/login.html'
    }).when('/lugar', {
      controller: 'LugarController',
      controllerAs: 'controller',
      templateUrl: 'templates/lugar.html'
    }).when('/supervision', {
      controller: 'SupervisionController',
      controllerAs: 'controller',
      templateUrl: 'templates/supervision.html'
    }).when('/tipoIdentificacion', {
      templateUrl: 'templates/tipoIdentificacion.html'
    }).when('/tipo-lugar', {
      templateUrl: 'templates/tipoLugar.html'
    }).when('/tipo-empleado', {
      controller: 'TipoEmpleadoController',
      controllerAs: 'controller',
      templateUrl: 'templates/tipoEmpleado.html'
    });
}
angular.module('blog').config(config);
