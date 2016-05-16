(function() {
  'use strict';
  angular.module('vp').service('saDelete', function(SweetAlert) {
    return function(row, gridOptions) {
      SweetAlert.swal({
          title: '¿Esta Seguro?',
          text: 'No se podra recuperar el registro',
          type: 'warning',
          showCancelButton: true,
          confirmButtonColor: '#DD6B55',
          confirmButtonText: 'Si, Eliminar',
          closeOnConfirm: false
        },
        function(isConfirm) {
          if (isConfirm) {
            row.entity.$delete(function(data) {
              if(data.id === -1){
                var i = gridOptions.data.indexOf(row.entity);
                gridOptions.data.splice(i, 1);
                SweetAlert.swal('Eliminado', '', 'success');
              }else{
                SweetAlert.swal('No Se Pudo Eliminar', '', 'error');
              }
            });
          }
        });
    };
  });
})();
