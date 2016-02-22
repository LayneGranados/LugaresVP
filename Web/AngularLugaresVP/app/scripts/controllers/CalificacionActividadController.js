(function() {
  'use strict';

  function CalificacionActividadController(CalificacionActividad) {
    var self = this;
    this.selectedCA = new CalificacionActividad();
    this.indexOf = undefined;
    this.save = function() {
      if (self.selectedCA.id === undefined) {
        self.selectedCA.$save();
        self.gridOptions.data.push(self.selectedCA);
        self.selectedCA = new CalificacionActividad();
      } else {
        self.selectedCA.$update();
      }

    };

    this.edit = function() {
      var calificacionesSeleted = self.gridApi.selection.getSelectedRows();
      self.indexOf = self.gridOptions.data.indexOf(calificacionesSeleted[0]);
      self.selectedCA = calificacionesSeleted[0];

    };
    this.delete = function() {
      var calificacionesSeleted = self.gridApi.selection.getSelectedRows();
      var indexOf = self.gridOptions.data.indexOf(calificacionesSeleted[0]);
      calificacionesSeleted[0].$delete({}, function() {
        self.gridOptions.data.splice(indexOf, 1);
      });
    };

    this.clear = function() {
      self.selectedCA = new CalificacionActividad();
    };
    this.caliciacionesActivdad = CalificacionActividad.query();
    this.gridOptions = {
      paginationPageSize: 15,
      data: self.caliciacionesActivdad,
      columnDefs: [{
        field: 'id',
        displayName: 'Id'
      }, {
        field: 'nombre',
        displayName: 'Calificacion'
      }, {
        field: 'actividad.nombre',
        displayName: 'Actividad'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };

    this.gridOptions.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };
  }
  angular.module('blog.controllers').controller('CalificacionActividadController', CalificacionActividadController);
})();
