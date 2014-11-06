package info.lofei.android.torch.Util;

import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.os.Build;

import java.io.IOException;
import java.util.List;

import info.lofei.android.torch.Exception.TorchUnavailableException;

/**
 * @author lofei@duotin.com
 * @version 1.0.0
 *          lofei 2014-10-20 下午6:02
 * @package info.lofei.android.torch.Util
 * @copyright Copyright (C) 2012-2020 DuoTin Network Technology Co.,LTD
 */
public class TorchUtil {

    private static Camera sCamera;

    private static boolean sIsTorchOn = false;

    public static void turnOn() throws TorchUnavailableException {
        try {
            if (sCamera == null) {
                sCamera = Camera.open();
            }
            Camera.Parameters p = sCamera.getParameters();

            p.setFlashMode(Camera.Parameters.FLASH_MODE_TORCH);

            sCamera.setParameters(p);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB ) {
                sCamera.setPreviewTexture(new SurfaceTexture(0));
            }
            sCamera.startPreview();
            sIsTorchOn = true;
        } catch (RuntimeException e) {
            throw new TorchUnavailableException(e);
        }catch (IOException e) {
            throw new TorchUnavailableException(e);
        }
    }

    public static void turnOff() throws TorchUnavailableException {
        try {
            if (sCamera == null) {
                sCamera = Camera.open();
            }
            Camera.Parameters p = sCamera.getParameters();
            p.setFlashMode(Camera.Parameters.FLASH_MODE_OFF);
            sCamera.setParameters(p);
            sCamera.stopPreview();
            sCamera.release();
            sCamera = null;
            sIsTorchOn = false;
        } catch (RuntimeException e) {
            throw new TorchUnavailableException(e);
        }
    }

    public static boolean isTorchOn() throws TorchUnavailableException {
       return sIsTorchOn;
    }
}
