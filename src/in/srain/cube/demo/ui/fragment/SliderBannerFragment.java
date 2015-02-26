package in.srain.cube.demo.ui.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;
import in.srain.cube.demo.R;
import in.srain.cube.demo.datamodel.SliderBannerDataModel;
import in.srain.cube.demo.event.DemoSimpleEventHandler;
import in.srain.cube.demo.event.EventCenter;
import in.srain.cube.demo.event.SliderBannerDataEvent;
import in.srain.cube.demo.ui.sliderbanner.SliderBannerController;
import in.srain.cube.mints.base.TitleBaseFragment;
import in.srain.cube.util.LocalDisplay;
import in.srain.cube.views.banner.SliderBanner;

public final class SliderBannerFragment extends TitleBaseFragment {

    @Override
    protected View createView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        setHeaderTitle(R.string.cube_demo_slider_banner);

        View view = inflater.inflate(R.layout.fragment_slider_banner, null);

        final SliderBanner sliderBanner = (SliderBanner) view.findViewById(R.id.demo_slider_banner);
        LinearLayout.LayoutParams lyp = new LinearLayout.LayoutParams(LocalDisplay.SCREEN_WIDTH_PIXELS, SliderBannerController.Height);
        sliderBanner.setLayoutParams(lyp);

        final SliderBannerController sliderBannerController = new SliderBannerController(sliderBanner);

        EventCenter.bindContainerAndHandler(this, new DemoSimpleEventHandler() {

            public void onEvent(SliderBannerDataEvent event) {
                sliderBannerController.play(event.data.optJson("data"));
            }

        }).tryToRegisterIfNot();

        SliderBannerDataModel.getSliderBanner();


        view.findViewById(R.id.demo_slider_banner_next_page).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getContext(), "Coming soon.", 1).show();
            }
        });
        return view;
    }
}