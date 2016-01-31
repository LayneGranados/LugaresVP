package com.gapxventuraplaza;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.gapxventuraplaza.domain.CalificacionActividad;

class ListAdapter extends BaseAdapter {
	
	   ViewHolder viewHolder;

        private ArrayList<CalificacionActividad> mItems = new ArrayList<CalificacionActividad>();
        private Context mContext;
        @SuppressWarnings("unused")
		private int mScreenWidth;

        public ListAdapter(Context context, ArrayList<CalificacionActividad> list, Integer screenWidth) {
            mContext = context;
            mItems = list;
            mScreenWidth = screenWidth;           
        }

        @Override
        public int getCount() {
            return mItems.size();
        }

        @Override
        public Object getItem(int position) {
            return mItems.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            
            if(convertView==null){

            	LayoutInflater vi = (LayoutInflater) mContext
                        .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.fragment_list_item, null);

                viewHolder = new ViewHolder();
                viewHolder.tipoServicio = (TextView) v.findViewById(R.id.item_nombre_actividad);
                v.setTag(viewHolder);
                 
            }else{
                viewHolder = (ViewHolder) convertView.getTag();
            }

            final String estado = String.valueOf(mItems.get(position).getCalifaciones().get(0).getId());
            final String fechaHora = mItems.get(position).getCalifaciones().get(0).getNombre();
            final String direccionRecogida = "Prueba de layne";

            viewHolder.tipoServicio.setText(estado);
            viewHolder.direccionRecogida.setText(direccionRecogida);
            viewHolder.estado.setText(estado);
            viewHolder.rol.setText(fechaHora);
            viewHolder.fechaHora.setText(fechaHora);

            return v;
        }
        
        static class ViewHolder {
        	  TextView tipoServicio;
        	  TextView estado;
        	  TextView fechaHora;
              TextView rol;
              TextView direccionRecogida;
        	  int position;
        }

    }