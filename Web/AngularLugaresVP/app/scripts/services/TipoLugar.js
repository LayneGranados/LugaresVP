(function() {
  'use strict';
  angular.module('blog.services').factory('TipoLugar', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'tipoLugar/:tipolugarId', {}, {
      delete: {
        method: 'POST',
        isArray: false,
        url: BaseUrl + 'tipoLugar/del',
        headers: {
          'Content-Type': 'application/json'
        }
      },
      update: {
        method: 'PUT',
        isArray: false,
        url: BaseUrl + 'tipoLugar',
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  });
})();
