(function() {
  'use strict';
  angular.module('vp.services').factory('EvaulacionSupervicion', function($resource, BaseUrl) {
    return $resource(BaseUrl + 'evaluacion-supervision/:id', {
      id: '@id'
    }, {
      query: {
        method: 'POST',
        isArray: true
      }
    });
  });
})();
