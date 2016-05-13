(function() {
  'use strict';
  angular.module('vp.services').factory('TipoIdentificacion', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoIdentificacion/:id', {
      id: '@id'
    }, {
      save: {
        method: 'POST'
      }
    });
  });
})();
