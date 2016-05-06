(function () {
  'use strict';
  angular.module('vp.services').factory('EvaulacionSupervicion',function ($resource, BaseUrl) {
    return $resource(BaseUrl + 'evaluacion-supervision/',{},{
      query:{
        method:'POST',
        isArray: true
      }
    });
  });
})();
