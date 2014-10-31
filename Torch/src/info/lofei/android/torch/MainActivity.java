package info.lofei.android.torch;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;
import android.widget.ToggleButton;

import info.lofei.android.torch.Exception.TorchUnavailableException;
import info.lofei.android.torch.Util.TorchUtil;

public class MainActivity extends Activity {


    private ToggleButton mTorchStateBtn;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);

        findViews();

        initClickActions();

    }

    @Override
    protected void onPause() {
        if(!TorchUtil.isTorchOn()){
            TorchUtil.turnOff();
        }
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        try {
            mTorchStateBtn.setChecked(!TorchUtil.isTorchOn());
        } catch (TorchUnavailableException e){
            Toast.makeText(this, R.string.torch_unavailable, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void toggleTorch() {
        if(TorchUtil.isTorchOn()){
            TorchUtil.turnOff();
            mTorchStateBtn.setChecked(true);
        } else {
            TorchUtil.turnOn();
            mTorchStateBtn.setChecked(false);
        }
    }

    private void findViews() {
        mTorchStateBtn = (ToggleButton) findViewById(R.id.btn_torch_state);
    }

    private void initClickActions() {
        mTorchStateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                toggleTorch();
            }
        });
    }
}
