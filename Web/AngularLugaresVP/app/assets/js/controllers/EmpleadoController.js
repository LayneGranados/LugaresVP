(function() {
  'use strict';

  function EmpleadoController(Empleado) {

    var self = this;

    this.empleados = Empleado.query();
    this.selectedEmpleado = new Empleado();
    this.selectedEmpleado.login = {};
    console.log(this.selectedEmpleado);
    this.registarUsuario = false;

    this.save = function() {
      if (self.selectedEmpleado.id === undefined) {
        self.selectedEmpleado.$save({}, function() {
          self.gridOptions.data.push(self.selectedEmpleado);
          self.selectedEmpleado = new Empleado();
          self.selectedEmpleado.login = {};
        });
      } else {
        self.selectedEmpleado.$update({}, function(data) {
          self.gridOptions.data.splice(self.indexOf, 1);
          self.gridOptions.data.push(data);
        });
      }
    };

    this.cancel = function() {
      self.selectedEmpleado = new Empleado();
      self.selectedEmpleado.login = {};
      self.registarUsuario = false;
    };

    this.edit = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      self.selectedEmpleado = angular.copy(rows[0]);
      self.indexOf = self.gridOptions.data.indexOf(rows[0]);
      if (self.selectedEmpleado.login.id !== '') {
        self.registarUsuario = true;
      } else {
        self.registarUsuario = false;
      }
    };

    this.delete = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      var indexOfDelete = self.gridOptions.data.indexOf(rows[0]);
      rows[0].$delete({}, function(data) {
        if (data === 'true') {
          self.gridOptions.data.splice(indexOfDelete, 1);
        }
      });
    };

    self.gridOptions = {
      paginationPageSize: 15,
      data: self.empleados,
      columnDefs: [{
        displayName: 'Tipo Identicación',
        field: 'tipoIdentificacion.nombre'

      }, {
        displayName: 'Identificación',
        field: 'persona.identificacion'
      }, {
        displayName: 'Nombres',
        field: 'persona.nombres'
      }, {
        displayName: 'Apellidos',
        field: 'persona.apellidos'
      }, {
        displayName: 'Tipo Empleado',
        field: 'tipoEmpleado.nombre'
      }, {
        displayName: 'Usuario',
        field: 'login.login',

      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };
  }
  angular.module('vp.controllers').controller('EmpleadoController', EmpleadoController);

}());
