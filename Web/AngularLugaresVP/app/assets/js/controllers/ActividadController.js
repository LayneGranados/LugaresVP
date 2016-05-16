(function() {
  'use strict';

  function ActividadController(Actividad, goTwoFields, stdfrm) {

    var self = this;
    this.gridOptions = {};
    this.actividades = Actividad.query();
    this.element = new Actividad();
    this.rowObject = null;
    this.mensaje = 'Crear Nueva Actividad';
    this.tfrm = 'Actividades';

    angular.extend(this, stdfrm(this, Actividad, 'Actividad'));
    this.gridOptions = goTwoFields(this.actividades, self);

  }

  angular.module('vp.controllers').controller('ActividadController', ActividadController);

})();
