(function() {
  'use strict';

  var ModalActividadesController = function(tipoLugar, Actividad, ActividadTipoLugar, goTwoFields, saDelete) {


    var self = this;
    this.tipoLugar = tipoLugar;
    this.actividadSelected = null;
    this.element = new ActividadTipoLugar();
    this.element.tipoLugar = tipoLugar;
    this.listActividades = ActividadTipoLugar.noLugar({
      idLugar: this.tipoLugar.id
    });
    this.listElements = [];
    this.listActivadesLugar = ActividadTipoLugar.queryLugar({
      idLugar: this.tipoLugar.id
    });
    var fields = [{
      field: 'actividadnombre',
      displayName: 'Actividad'
    }];
    this.gridOptions = goTwoFields(this.listActivadesLugar, this, fields, null, 5);


    this.save = function() {
      if (self.element.actividad === undefined || self.element.actividad === null) {
        return;
      }
      self.element.tipolugar = self.element.tipoLugar.id;
      self.element.actividad = self.element.actividad.id;
      self.element.$save(null, function(object) {
        self.gridOptions.data.push({
          'id': object.id,
          'actividadnombre': object.actividad.nombre,
          'actividadid': object.actividad.id
        });
        var counter = 0;
        angular.forEach(self.listElements, function(value) {
          if (value.id === self.element.actividad.id) {
            self.listElements.splice(counter, 1);
            return;
          }
          counter++;
        });
      });
    };

    this.delete = function(row) {
      var entity = row.entity;
      saDelete(row, self.gridOptions);
      self.listElements.push(new Actividad({'id': entity.actividadid,'nombre':entity.actividadnombre}));
    };

  };
  angular.module('vp.controllers').controller('ModalActividadesController', ModalActividadesController);
}());
