(function() {
  'use strict';
  angular.module('blog').directive('selectTipoIdentificacion', function(TipoDocumento) {
    return {
      link: function(scope) {
        scope.list = TipoDocumento.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'views/directives/selectGeneral.html'
    };
  });
})();
