package com.gapxventuraplaza;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.text.TextUtils;

import com.dm.zbar.android.scanner.ZBarConstants;
import com.dm.zbar.android.scanner.ZBarScannerActivity;
import com.gapxventuraplaza.domain.Globales;
import com.gapxventuraplaza.menu.ResideMenu;

import net.sourceforge.zbar.Symbol;

/**
 * Created by laynegranadosmogollon on 12/07/15.
 */
public class CodeScanner extends Activity {

    private static final int ZBAR_SCANNER_REQUEST = 0;
    private static final int ZBAR_QR_SCANNER_REQUEST = 1;
    private ResideMenu resideMenu;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (isCameraAvailable()) {
            Intent intent = new Intent(this, ZBarScannerActivity.class);
            intent.putExtra(ZBarConstants.SCAN_MODES, new int[]{Symbol.QRCODE});
            startActivityForResult(intent, ZBAR_SCANNER_REQUEST);
        }
    }

    public boolean isCameraAvailable() {
        PackageManager pm = getPackageManager();
        return pm.hasSystemFeature(PackageManager.FEATURE_CAMERA);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ZBAR_SCANNER_REQUEST:
            case ZBAR_QR_SCANNER_REQUEST:
                if (resultCode == RESULT_OK) {
                    finish();
                    Intent returnIntent = new Intent(getApplicationContext(), MainActivity.class);
                    String lugar = data.getStringExtra(ZBarConstants.SCAN_RESULT);
                    returnIntent.putExtra("escaneado", lugar);
                    final Globales globales = (Globales) getApplicationContext();
                    globales.setLugar(Integer.parseInt(lugar));
                    startActivity(returnIntent);
                } else if(resultCode == RESULT_CANCELED && data != null) {
                    String error = data.getStringExtra(ZBarConstants.ERROR_INFO);
                    if(!TextUtils.isEmpty(error)) {

                    }
                }
                break;
        }
    }


}
