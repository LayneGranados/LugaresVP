(function() {
  'use strict';
  angular.module('vp').directive('selectTipoLugar', function(TipoLugar) {
    return {
      link: function($scope) {
        $scope.list = TipoLugar.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'templates/directives/selectGeneral.html'
    };
  });
})();
