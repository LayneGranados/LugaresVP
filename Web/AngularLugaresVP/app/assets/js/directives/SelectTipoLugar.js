(function() {
  'use strict';
  angular.module('blog').directive('selectTipoLugar', function(TipoLugar) {
    return {
      link: function($scope) {
        $scope.list = TipoLugar.query();
      },
      restrict: 'E',
      scope: {
        object: '='
      },
      templateUrl: 'views/directives/selectGeneral.html'
    };
  });
})();
