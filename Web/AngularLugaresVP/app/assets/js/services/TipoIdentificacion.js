(function() {
  'use strict';
  angular.module('vp.services').factory('TipoIdentificacion', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoIdentificacion/', {}, {
      save: {
        method: 'POST'
      }
    });
  });
})();
