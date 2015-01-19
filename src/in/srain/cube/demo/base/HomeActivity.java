package in.srain.cube.demo.base;

import android.os.Bundle;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.fragment.CubeDemoHomeFragment;
import in.srain.cube.mints.base.MintsBaseActivity;

public class HomeActivity extends MintsBaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        pushFragmentToBackStack(CubeDemoHomeFragment.class, null);
    }

    protected String getCloseWarning() {
        return "Tap back to exit";
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragment;
    }
}