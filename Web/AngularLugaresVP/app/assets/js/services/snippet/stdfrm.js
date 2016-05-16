(function() {
  'use strict';
  angular.module('vp').service('stdfrm', function(toaster, saDelete) {
    return function(controller, Obj, title) {
      var form = {};

      form.cancel = function() {
        controller.element = new Obj();
        console.log('Cancel');
      };

      form.save = function() {
        if (controller.element.id === undefined) {
          controller.element.$save({}, function() {
            controller.gridOptions.data.push(controller.element);
            toaster.pop('success', 'Elemento Guardado');
            controller.cancel();
          });
        } else {
          controller.element.$update({}, function(data) {
            angular.extend(controller.rowObject, data);
            toaster.pop('warning', 'Elemento Editado');
            controller.cancel();
          });
        }
      };

      form.edit = function(row) {
        console.log('Editar');
        controller.element = angular.copy(row.entity);
        controller.rowObject = row.entity;
        controller.mensaje = 'Editar '+title;
      };

      form.delete = function(row) {
        saDelete(row, controller.gridOptions);
      };

      return form;
    };
  });
})();
