(function() {
  'use strict';
  angular.module('blog').directive('selectTipoIdentificacion', function(TipoIdentificacion) {
    return {
      link: function(scope) {
        scope.list = TipoIdentificacion.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'views/directives/selectGeneral.html'
    };
  });
})();
