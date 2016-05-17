(function() {
  'use strict';

  function ActividadTipoLugar($resource, BaseUrl) {
    return $resource(BaseUrl + 'actividadTipoLugar', {
      actividadTipoLugarId: '@id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      },
      'queryLugar': {
        method: 'GET',
        isArray: true,
        headers: {
          'Content-Type': 'application/json'
        },
        url: BaseUrl + '/actividadTipoLugar/lugar'
      },
      'noLugar': {
        method: 'GET',
        isArray: true,
        headers: {
          'Content-Type': 'application/json'
        },
        url: BaseUrl + 'actividadTipoLugar/nolugar'
      },
      'delete': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        },
        url: BaseUrl + 'actividadTipoLugar/del'
      }
    });
  }
  angular.module('vp').factory('ActividadTipoLugar', ActividadTipoLugar);
})();
