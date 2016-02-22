function config($locationProvider, $routeProvider) {
  'use strict';
  $locationProvider.html5Mode(true);
  $routeProvider
    .when('/', {
      templateUrl: 'views/Login.html'
    }).when('/actividad', {
      templateUrl: 'views/actividad.html'
    }).when('/actividadTipoLugar', {
      templateUrl: 'views/actividadTipoLugar.html'
    }).when('/calificacionActividad', {
      controller: 'CalificacionActividadController',
      controllerAs: 'controller',
      templateUrl: 'views/calificacionActividad.html'
    }).when('/empleado', {
      templateUrl: 'views/empleado.html',
      controller: 'EmpleadoController',
      controllerAs: 'controller'
    }).when('/evaluacion', {
      templateUrl: 'views/evaluacion.html'
    }).when('/login', {
      templateUrl: 'views/Login.html'
    }).when('/lugar', {
      templateUrl: 'views/lugar.html'
    }).when('/supervision', {
      templateUrl: 'views/supervision.html'
    }).when('/tipoIdentificacion', {
      templateUrl: 'views/tipoIdentificacion.html'
    }).when('/tipo-lugar', {
      templateUrl: 'views/tipoLugar.html'
    }).when('/tipo-usuario', {
      templateUrl: 'views/tipoEmpleado.html'
    });
};
angular.module('blog').config(config);
