package com.special;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import android.widget.AdapterView.OnItemClickListener;

import java.util.ArrayList;

import com.special.R;
import com.special.domain.Servicio;

public class ListFragment extends Fragment {

    private View parentView;
    private ListView listView;
    private ListAdapter mAdapter;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        parentView = inflater.inflate(R.layout.fragment_list, container, false);
        listView   = (ListView) parentView.findViewById(R.id.listView);
        initView();
        return parentView;
    }

    @SuppressLint("NewApi")
	@SuppressWarnings("deprecation")
	private void initView(){
    	//Getting width of display, could be usefull for scaling bitmaps
    	Display display = getActivity().getWindowManager().getDefaultDisplay();
    	int width;
    	if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB_MR2){
    		Point size = new Point();
    		display.getSize(size);
    		width = size.x;
    	} else{
        	width = display.getWidth();
    	}
    	
    	mAdapter = new ListAdapter(getActivity(), getListData(), width);
        listView.setAdapter(mAdapter);
        listView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                
                Servicio item = (Servicio) listView.getAdapter().getItem(i);
                
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("tipo_servicio", item.getTipoServicio());
                intent.putExtra("reserva", item.getCodigoReserva());
                intent.putExtra("descr", "layne granados");
                System.out.println("Hizo clic en uno de los de la lista");
                startActivity(intent);
            }
        });
    }

    private ArrayList<Servicio> getListData(){
        ArrayList<Servicio> servicios=  (ArrayList<Servicio>)this.getActivity().getIntent().getExtras().getSerializable("servicios");
        System.out.println("TAMAÑO DE LOS SERVICIOS EN FRAGMENT: "+servicios.size());

        /*ArrayList<ListItem> listData = new ArrayList<ListItem>();
        listData.add(new ListItem(R.drawable.ph_hotel, "Airport Hotel",        "Large hotel located next to the Airport Terminal", "5", "Rooms Available"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Select Hotel",         "Small hotel near the City", "3", "Rooms Available" ));
        listData.add(new ListItem(R.drawable.ph_hotel, "Beach Hotel",          "Located next to a white sand beach", "3", "Stars"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Dance and Party Club", "Ideal for teens", "10+", "Rooms Available"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Royal City Resort",    "Enjoy luxery in the City", "5", "Stars"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Safari Lodge",         "Relax in the Wild", "4.5", "Guest Rating"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Central Park",         "The famous Park Hotel","10+", "Rooms Available"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Tropical by WorldClub","Located in South Africa", "4.8", "Guest Rating"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Ski Hotel",            "Located next to the Lifts", "All", "Inclusive"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Relax by WorldClub",   "Affordable Luxery", "3", "Rooms Available"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Road Motel",           "Make a stop worth waiting", "No", "Reservation Needed"));
        listData.add(new ListItem(R.drawable.ph_hotel, "Alpine Lodge",         "Located in the Alps", "Full", "Pension"));
        return listData;*/
        return servicios;
    }
}
