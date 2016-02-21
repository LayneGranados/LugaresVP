(function() {
  'use strict';
  function EmpleadoController(Empleado) {

    var self = this;

    this.empleados = Empleado.query();

    this.registarUsuario = true;

    self.create =
      function() {

      };

    self.gridOptions = {
      paginationPageSize: 15,
      data: self.empleados,
      columnDefs: [{
        field: 'tipo_identificacion_nombre',displayName:'Tipo Identicación'
      },{
        field: 'identificacion',displayName:'Identificación'
      }, {
        field: 'nombres'
      }, {
        field: 'apellidos'
      }, {
        field: 'tipo_empleado'
      }, {
        field: 'login',displayName:'Usuario'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };
  }
  angular.module('blog.controllers').controller('EmpleadoController', EmpleadoController);

}());
