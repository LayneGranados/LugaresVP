(function() {
  'use strict';
  angular.module('blog.services').factory('Actividad', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'actividad/', {}, {
      query: {
        isArray: true,
        method: 'GET'
      },
      save: {
        isArray: false,
        method: 'POST'
      },
      update: {
        method: 'PUT'
      }
    });
  });
})();
