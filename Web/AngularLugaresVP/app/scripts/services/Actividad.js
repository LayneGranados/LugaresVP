(function() {
  'use strict';
  angular.module('blog.services').factory('Actividad', function($resource, BaseUrl) {
    return $resource(BaseUrl + '/actividad', {}, {
      query: {
        isArray: true,
        method: 'GET'
      },
      save: {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  });
})();
