package info.lofei.android.torch.Util;

import android.hardware.Camera;

/**
 * @author lofei@duotin.com
 * @version 1.0.0
 *          lofei 2014-10-20 下午6:02
 * @package info.lofei.android.torch.Util
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 */
public class TorchUtil {

    private static Camera sCamera;

    public static void turnOn(){
        if(sCamera == null) {
            sCamera = Camera.open();
        }
        Camera.Parameters p = sCamera.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);
        sCamera.setParameters(p);
        sCamera.startPreview();
    }

    public static void turnOff(){
        if(sCamera == null) {
            sCamera = Camera.open();
        }
        Camera.Parameters p = sCamera.getParameters();
        p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
        sCamera.setParameters(p);
        sCamera.stopPreview();
        sCamera.release();
        sCamera = null;
    }

    public static boolean isTorchOn(){
        if(sCamera == null){
            sCamera = Camera.open();
        }
        Camera.Parameters p = sCamera.getParameters();
        return Camera.Parameters.FLASH_MODE_TORCH.equals(p.getFlashMode());
    }
}
