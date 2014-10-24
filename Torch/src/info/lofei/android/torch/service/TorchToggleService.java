package info.lofei.android.torch.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.widget.Toast;

import info.lofei.android.torch.Exception.TorchUnavailableException;
import info.lofei.android.torch.R;
import info.lofei.android.torch.Util.TorchUtil;

public class TorchToggleService extends Service {

    public static final String TOGGLE_ACTION = "info.lofei.android.torch.toggle_action";

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        if(intent != null) {
            String action = intent.getAction();
            if(TOGGLE_ACTION.equals(action)) {
                toggleTorch();
            }
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void toggleTorch() {
        try {
            if (TorchUtil.isTorchOn()) {
                TorchUtil.turnOff();
            } else {
                TorchUtil.turnOn();
            }
        } catch (TorchUnavailableException e) {
            Toast.makeText(this, R.string.torch_unavailable, Toast.LENGTH_SHORT).show();
        }
    }
}
