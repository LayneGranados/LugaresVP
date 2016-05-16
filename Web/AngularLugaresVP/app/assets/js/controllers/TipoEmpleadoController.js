(function() {
  'use strict';

  function TipoEmpleadoController(TipoEmpleado,toaster, SweetAlert, goTwoFields,stdfrm) {
    var self = this;
    this.gridOptions = {};
    this.tipos = TipoEmpleado.query();
    this.element = new TipoEmpleado();
    this.rowObject = null;
    this.mensaje = 'Crear Nuevo Tipo De Empleado';

    angular.extend(this,stdfrm(this,TipoEmpleado,'Tipo De Empleado'));
    this.gridOptions = goTwoFields(this.tipos, self);
  }
  angular.module('vp.controllers').controller('TipoEmpleadoController', TipoEmpleadoController);
}());
