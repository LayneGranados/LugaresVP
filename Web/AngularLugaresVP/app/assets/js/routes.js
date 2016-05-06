(function() {
  'use strict';
  var config = function($stateProvider, $urlRouterProvider) {
    $urlRouterProvider.otherwise('/login');
    $stateProvider.state('app', {
      url: '/vp',
      templateUrl: 'templates/app.html',
      abstract: true
    }).state('app.dashboard', {
      url: '/dashboard',
      templateUrl: 'templates/dashboard.html',
      title: 'Dashboard',
      ncyBreadcrumb: {
        label: 'Dashboard'
      }
    }).state('login', {
      url: '/login',
      templateUrl: 'templates/login.html',
      controller: 'LoginController',
      controllerAs: 'controller',
      title: 'Dashboard',
      ncyBreadcrumb: {
        label: 'login'
      }
    }).state('app.actividad', {
      url: '/actividad',
      controller: 'ActividadController',
      controllerAs: 'controller',
      templateUrl: 'templates/actividad.html'
    }).state('app.actividad-tipo-lugar', {

      url: '/actividad-tipo-lugar',
      templateUrl: 'templates/actividadTipoLugar.html'

    }).state('app.calificacion-actividad', {
      url: '/calificacion-actividad',
      controller: 'CalificacionActividadController',
      controllerAs: 'controller',
      templateUrl: 'templates/calificacionActividad.html'
    }).state('app.empleado', {
      url: '/empleado',
      controller: 'EmpleadoController',
      controllerAs: 'controller',
      templateUrl: 'templates/empleado.html'
    }).state('app.evaluacion', {
      url: '/evaluacion',
      templateUrl: 'templates/evaluacion.html'
    }).state('app.lugar', {
      url: '/lugar',
      controller: 'LugarController',
      controllerAs: 'controller',
      templateUrl: 'templates/lugar.html'
    }).state('app.supervision', {
      url: '/supervision',
      controller: 'SupervisionController',
      controllerAs: 'controller',
      templateUrl: 'templates/supervision.html'
    }).state('app.tipo-identificacion', {
      url: '/tipo-identificacion',
      templateUrl: 'templates/tipoIdentificacion.html'
    }).state('app.tipo-lugar', {
      controller: 'TipoLugarCRUDController',
      controllerAs: 'tipCreateCtrl',
      templateUrl: 'templates/tipoLugar.html',
      url: '/tipo-lugar'
    }).state('app.tipo-empleado', {
      url: '/tipo-empleado',
      controller: 'TipoEmpleadoController',
      controllerAs: 'controller',
      templateUrl: 'templates/tipoEmpleado.html'
    });
  };
  angular.module('vp').config(config);
})();
