(function() {
  'use strict';
  angular.module('blog.services').factory('TipoIdentificacion', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoIdentificacion/', {}, {
      save: {
        method: 'POST'
      }
    });
  });
})();
