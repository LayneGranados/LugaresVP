(function() {
  'use strict';
  function ModalActividadesController(tipoLugar, Actividad, ActividadTipoLugar) {
    var self = this;
    this.tipoLugar = tipoLugar;
    this.actividadSelected = null;
    this.listActividades = ActividadTipoLugar.noLugar({idLugar:this.tipoLugar.id});
    this.listActivadesLugar = ActividadTipoLugar.queryLugar({
      idLugar: this.tipoLugar.id
    });
    //Opciones para el grid
    this.gridOptions1 = {
      paginationPageSize: 15,
      data: self.listActivadesLugar,
      columnDefs: [{
        field: 'id',
        displayName: 'Id'
      }, {
        field: 'actividadnombre',
        displayName: 'Actividad'
      }],
      multiSelect: false,
      enableRowSelection: true,
      enableRowHeaderSelection: false
    };
    this.gridOptions1.onRegisterApi = function(gridApi) {
      self.gridApi = gridApi;
    };


    this.addActividad = function() {
      var actividadTipoLugar = new ActividadTipoLugar();
      actividadTipoLugar.tipolugar = self.tipoLugar.id;
      actividadTipoLugar.actividad = self.actividadSelected.actividadid;
      actividadTipoLugar.$save(null, function(object) {
        self.gridOptions1.data.push({
          'id': object.id,
          'actividadnombre': object.actividad.nombre
        });
        var counter = 0;
        self.listActividades.forEach(function(entry) {
          if (entry.actividadid === self.actividadSelected.actividadid) {
            self.listActividades.splice(counter, 1);
            return;
          }
          counter++;
        });
      });
    };

    this.delActividad = function() {
      var filas = {};
      filas = self.gridApi.selection.getSelectedRows();
      if(filas.length !== 1){
        return;
      }
      console.log(filas[0]);
      var actividadTipoLugar = new ActividadTipoLugar();
      actividadTipoLugar.id =filas[0].id;
      actividadTipoLugar.$eliminar(null,function (object) {
        if (object.deleted){
          self.listActividades.push({'actividadid':filas[0].actividadid,'actividadnombre':filas[0].actividadnombre});
          var index = self.gridOptions1.data.indexOf(filas[0]);
          self.gridOptions1.data.splice(index, 1);
        }
      });
    };

  }
  angular.module('blog.controllers').controller('ModalActividadesController', ModalActividadesController);
}());
