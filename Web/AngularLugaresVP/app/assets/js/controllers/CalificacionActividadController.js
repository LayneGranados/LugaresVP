(function() {
  'use strict';

  function CalificacionActividadController(CalificacionActividad, toaster, SweetAlert, saDelete, goTwoFields, stdfrm) {
    var self = this;
    this.gridOptions = {};
    this.calificacionesA = CalificacionActividad.query();
    this.element = new CalificacionActividad();
    this.rowObject = null;
    this.mensaje = 'Crear Nueva Calificación De Actividad';
    this.tfrm = 'Calificaciones De Actividad';

    angular.extend(this, stdfrm(this, CalificacionActividad, 'Calificaciones De Actividad'));

    this.gridOptions = goTwoFields(this.calificacionesA, self, [{
      field: 'actividad.nombre',
      displayName: 'Actividad'
    }, {
      field: 'nombre',
      displayName: 'Calificacion'
    }]);
  }
  angular.module('vp.controllers').controller('CalificacionActividadController', CalificacionActividadController);
})();
