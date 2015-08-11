package com.special;

import com.special.menu.ResideMenu;
import android.app.Dialog;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.PorterDuff.Mode;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.SeekBar;

public class ElementsFragment extends Fragment {
	
	//Layouts
	private ResideMenu resideMenu;
 	Button btn, btnCancel;
 	Dialog dialog;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
    	final ScrollView v =  (ScrollView) inflater.inflate(R.layout.fragment_elements, container, false);
    	
    	MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();
    	
        //Adding a view to be ignored by the menu for horizontal finger movements 
    	SeekBar ignored_view = (SeekBar) v.findViewById(R.id.seekbar1);
        resideMenu.addIgnoredView(ignored_view);
        
        Button b3 = (Button) v.findViewById(R.id.button3);
        Button b4 = (Button) v.findViewById(R.id.button4);
        
        //Set the color of any view without having to modify and duplicate original layout files
        b3.getBackground().setColorFilter(getActivity().getResources().getColor(R.color.green), Mode.MULTIPLY);
        b4.getBackground().setColorFilter(Color.BLACK, Mode.MULTIPLY);
        
        //Set the (pressed) text color of any view without having to modify and duplicate original layout files
        b3.setTextColor (new ColorStateList (
        		   new int [] [] {
        		      new int [] {android.R.attr.state_pressed},
        		      new int [] {}
        		   },
        		   new int [] {
        			getActivity().getResources().getColor(R.color.green),
        		      Color.WHITE
        		   }
        		));
        
        b4.setTextColor (new ColorStateList (
        		   new int [] [] {
        		      new int [] {android.R.attr.state_pressed},
        		      new int [] {}
        		   },
        		   new int [] {
        		      Color.WHITE,
        		      Color.BLACK
        		   }
        		));
        
        //Adding a listener to a button
        btn = (Button) v.findViewById(R.id.buttonDialog);
        btn.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				showCustomDialog(v);
			}

		});
        return v;
    }
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
    }
    
    //Showing a custom styled dialog and adding actions to the buttons
    protected void showCustomDialog(View v) {

		dialog = new Dialog(getActivity(),
				android.R.style.Theme_Translucent);
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		dialog.setCancelable(true);
		dialog.setContentView(R.layout.layout_dialog);

		btnCancel = (Button) dialog.findViewById(R.id.btncancel);

		btnCancel.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View view) {
				dialog.cancel();
			}

		});
		
		final ImageView myImage = (ImageView) dialog.findViewById(R.id.loader);
        myImage.startAnimation(AnimationUtils.loadAnimation(getActivity(), R.anim.rotate) );
        
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(0x7f000000));
		
		dialog.show();
	}
}
