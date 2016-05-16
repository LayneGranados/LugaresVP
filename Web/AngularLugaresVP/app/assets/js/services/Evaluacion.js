(function() {
  'use strict';
  var Evaluacion = function($resource, BaseUrl) {
    return $resource(BaseUrl + '/evaluacion/:evaluacionId', {
      lugarId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  };
  angular
    .module('vp.services')
    .factory('Evaluacion', Evaluacion);
})();
