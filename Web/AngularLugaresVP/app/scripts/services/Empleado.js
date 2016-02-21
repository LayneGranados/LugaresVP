(function() {
  'use strict';
  angular.module('blog.services').factory('Empleado', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'empleado/', {}, {
      query: {
        method: 'GET',
        isArray: true
      }
    });
  });
})();
