(function() {
  'use strict';

  angular.module('blog', ['ngRoute', 'ui.grid', 'ui.grid.pagination', 'ui.grid.selection', 'blog.controllers']);

  function config($locationProvider, $routeProvider) {
    $locationProvider.html5Mode(true);
    $routeProvider
      .when('/', {
        templateUrl: 'views/login.html'
      })
      .when('/actividad', {
        templateUrl: 'views/actividad.html'
      })
      .when('/actividadTipoLugar', {
        templateUrl: 'views/actividadTipoLugar.html'
      })
      .when('/calificacion', {
        templateUrl: 'views/calificacion.html'
      })
      .when('/calificacionActividad', {
        templateUrl: 'views/calificacionActividad.html'
      })
      .when('/categoriaVehiculo', {
        templateUrl: 'views/categoriaVehiculo.html'
      })
      .when('/evaluacion', {
        templateUrl: 'views/evaluacion.html'
      })
      .when('/login', {
        templateUrl: 'views/Login.html'
      })
      .when('/lugar', {
        templateUrl: 'views/lugar.html'
      })
      .when('/persona', {
        templateUrl: 'views/persona.html'
      })
      .when('/supervision', {
        templateUrl: 'views/supervision.html'
      })
      .when('/tipoIdentificacion', {
        templateUrl: 'views/tipoIdentificacion.html'
      })
      .when('/tipo-lugar', {
        templateUrl: 'views/tipoLugar.html'
      })
      .when('/tipo-usuario', {
        templateUrl: 'views/tipoEmpleado.html'
      });
  }
  angular
    .module('blog')
    .config(config);

})();
