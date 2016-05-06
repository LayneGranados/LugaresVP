(function() {
  'use strict';
  angular.module('vp').directive('selectTipoIdentificacion', function(TipoIdentificacion) {
    return {
      link: function(scope) {
        scope.list = TipoIdentificacion.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'templates/directives/selectGeneral.html'
    };
  });
})();
