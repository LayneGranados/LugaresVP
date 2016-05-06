(function() {
  'use strict';

  function TipoEmpleadoController(TipoEmpleado) {
    var self = this;
    //lista todos los Tipos de empleados
    this.tipos = TipoEmpleado.query();
    this.selectedTE = new TipoEmpleado();
    //opciones del grid
    this.gridOptions = {
      paginationPageSize: 15,
      data: self.tipos,
      columnDefs: [{
        field: 'id'
      }, {
        field: 'nombre'
      }, {
        field: 'descripcion'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };

    this.clear = function() {
      this.selectedTE = new TipoEmpleado();
      self.gridApi.selection.clearSelectedRows();
    };

    this.edit = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      self.selectedTE = angular.copy(rows[0]);
      self.indexOf = self.gridOptions.data.indexOf(rows[0]);
    };
    self.save = function() {
      if (self.selectedTE.id === undefined) {
        self.selectedTE.$save({}, function(data) {
          self.gridOptions.data.push(data);
          self.selectedTE = new TipoEmpleado();
        });
      } else {
        self.selectedTE.$update({}, function(data) {
          self.selectedTE = new TipoEmpleado();
          self.gridOptions.data.splice(self.indexOf, 1);
          self.gridOptions.data.push(data);
        });
      }
    };

    this.delete = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      var indexOfDelete = self.indexOf = self.gridOptions.data.indexOf(rows[0]);
      rows[0].$delete({}, function() {
        self.gridOptions.data.splice(indexOfDelete, 1);
      });
    };
  }

  angular.module('vp.controllers').controller('TipoEmpleadoController', TipoEmpleadoController);


}());
