(function() {
  'use strict';
  angular.module('vp').service('goTwoFields', function() {
    return function(datar, controller, fields, firtsCol, pgSize) {
      var gridOptions = {
        data: datar,
        columnDefs: [],
        multiSelect: false,
        enablePaginationControls: false,
        paginationCurrentPage: 1,
        enableRowSelection: false,
        enableRowHeaderSelection: false
      };

      if (firtsCol === undefined || firtsCol === null) {
        gridOptions.columnDefs.push({
          field: 'id',
          name: 'Acciones',
          cellTemplate: 'templates/common/edit-button.html',
          width: 75,
          enableSorting: false,
          enableHiding: false,
          enableColumnMenu: false
        });
      } else {
        gridOptions.columnDefs.push(firtsCol);
      }

      if (fields !== null && fields !== undefined && fields.length > 0) {
        angular.forEach(fields, function(value) {
          gridOptions.columnDefs.push(value);
        });
      } else {
        gridOptions.columnDefs.push({
          field: 'nombre'
        }, {
          field: 'descripcion'
        });
      }

     if (pgSize !== null && pgSize !== undefined) {
        gridOptions.paginationPageSize = pgSize;
      }
      gridOptions.onRegisterApi = function(gridApi) {
        controller.gridApi = gridApi;
      };
      gridOptions.appScopeProvider = controller;
      return gridOptions;
    };
  });
})();
