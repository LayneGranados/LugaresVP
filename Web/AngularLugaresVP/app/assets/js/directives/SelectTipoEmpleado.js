(function() {
  'use strict';
  angular.module('blog').directive('selectTipoEmpleado', function(TipoEmpleado) {
    return {
      link: function(scope) {
        scope.list = TipoEmpleado.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'templates/directives/selectGeneral.html'
    };
  });
})();
