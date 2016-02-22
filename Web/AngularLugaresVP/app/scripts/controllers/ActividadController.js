(function() {
  'use strict';

  function ActividadController(Actividad) {

    var self = this;

    this.actividades = Actividad.query();
    self.selectedActividad = new Actividad();
    this.cancel = function() {
      self.selectedActividad = new Actividad();

    };

    this.save = function() {
      if (self.selectedActividad.id === undefined) {
        self.selectedActividad.$save({}, function() {
          self.gridOptions.data.push(self.selectedActividad);
          self.selectedActividad = new Actividad();
        });
      } else {
        self.selectedActividad.$update({}, function(data) {
          self.gridOptions.data.splice(self.indexOf, 1);
          self.gridOptions.data.push(data);
        });
      }
    };

    this.edit = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      self.selectedActividad = angular.copy(rows[0]);
      self.indexOf = self.gridOptions.data.indexOf(rows[0]);
    };

    this.delete = function() {
      var rows = self.gridApi.selection.getSelectedRows();
      var indexOfDelete = self.gridOptions.data.indexOf(rows[0]);
      rows[0].$delete({}, function() {
        self.gridOptions.data.splice(indexOfDelete, 1);
      });
    };
    
    this.gridOptions = {
      paginationPageSize: 15,
      data: self.actividades,
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

  }

  angular.module('blog.controllers').controller('ActividadController', ActividadController);

})();
