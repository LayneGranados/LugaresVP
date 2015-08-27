package com.special;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ScrollView;
import com.special.menu.ResideMenu;



public class ScanCode extends Fragment {

    //Layouts
    private ResideMenu resideMenu;
    Button btn, btnCancel;
    Dialog dialog;
    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final ScrollView v =  (ScrollView) inflater.inflate(R.layout.scan_code, container, false);

        btn = (Button) v.findViewById(R.id.buttonDialog);
        btn.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {

                try {
                    Intent i = new Intent(getActivity(), CodeScanner.class);
                    getActivity().startActivityForResult(i, 1);
                } catch (Exception e) {
                }
            }

        });
        return v;
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        getActivity();
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

        btnCancel.setOnClickListener(new View.OnClickListener() {

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
