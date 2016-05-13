(function() {
  'use strict';
  angular.module('vp.services').factory('Persona', function Persona($resource, BaseUrl) {
    return $resource(BaseUrl + '/persona/:id', {id:'@id'}, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  });
})();
