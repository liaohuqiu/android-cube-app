package in.srain.cube.demo.base;

import android.os.Bundle;
import in.srain.cube.demo.R;
import in.srain.cube.demo.ui.fragment.HomeFragment;
import in.srain.cube.mints.base.DemoBaseActivity;

public class HomeActivity extends DemoBaseActivity {

    @Override
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        pushFragmentToBackStack(HomeFragment.class, null);
    }

    protected String getCloseWarning() {
        return "Tap back to exit";
    }

    @Override
    protected int getFragmentContainerId() {
        return R.id.id_fragment;
    }
}