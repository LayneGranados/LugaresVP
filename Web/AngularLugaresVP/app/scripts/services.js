(function() {
  'use strict';

  angular.module('blog.services', ['ngResource']);

  function Actividad($resource, BaseUrl) {
    return $resource(BaseUrl + '/actividad/:actividadId', {
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
  }

  function ActividadTipoLugar($resource, BaseUrl) {
    return $resource(BaseUrl + '/actividadTipoLugar/:actividadTipoLugarId', {
      actividadTipoLugarId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  }

  function CalificacionActividad($resource, BaseUrl) {
    return $resource(BaseUrl + '/calificacionActividad/:calificacionActividadId', {
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
  }

  function CategoriaVehiculo($resource, BaseUrl) {
    return $resource(BaseUrl + '/categoriaVehiculo/:categoriaId', {
      categoriaId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {

          'Content-Type': 'application/json'
        }
      }
    });
  }

  function Evaluacion($resource, BaseUrl) {
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
  }

  function Lugar($resource, BaseUrl) {
    return $resource(BaseUrl + '/lugar/:lugarId', {
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
  }

  function Login($resource, BaseUrl) {
    return $resource(BaseUrl + '/login/:loginId', {
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
  }

  function Supervision($resource, BaseUrl) {
    return $resource(BaseUrl + '/supervision/:supervisionId', {
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
  }


  function TipoEmpleado($resource, BaseUrl) {
    return $resource(BaseUrl + '/tipoEmpleado/:tipoempleadoId', {
      tipoempleadoId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      },
      'update': {
        method: 'PUT',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      },
    });
  }

  function TipoIdentificacion($resource, BaseUrl) {
    return $resource(BaseUrl + '/tipoIdentificacion/:tipoIdentificacionId', {
      tipoIdentificacionId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  }

  function TipoLugar($resource, BaseUrl) {
    return $resource(BaseUrl + '/tipoLugar/:tipolugarId', {
      tipolugarId: '@_id'
    }, {
      'save': {
        method: 'POST',
        isArray: false,
        headers: {
          'Content-Type': 'application/json'
        }
      }
    });
  }



  function Persona($resource, BaseUrl) {
    return $resource(BaseUrl + '/persona/:personaId', {
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
  }

  angular
    .module('blog.services')
    .constant('BaseUrl', 'http://192.168.0.50:8080/InspeccionZonas/rest/')
    .factory('TipoLugar', TipoLugar)
    .factory('TipoEmpleado', TipoEmpleado)
    .factory('TipoIdentificacion', TipoIdentificacion)
    .factory('Lugar', Lugar)
    .factory('Login', Login)
    .factory('Actividad', Actividad)
    .factory('Persona', Persona)
    .factory('Supervision', Supervision)
    .factory('Evaluacion', Evaluacion)
    .factory('ActividadTipoLugar', ActividadTipoLugar)
    .factory('CalificacionActividad', CalificacionActividad)
    .factory('CategoriaVehiculo', CategoriaVehiculo);
})();
