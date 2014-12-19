package in.srain.cube.demo.ui.imageloader;

import in.srain.cube.image.ImageReuseInfo;
import in.srain.cube.image.ImageReuseInfoManger;
import in.srain.cube.util.LocalDisplay;

public class ImageSize {

    private static final String[] sizeList = new String[]{"small_180", "big_360", "big"};

    public static final ImageReuseInfoManger sImageReuseInfoManger = new ImageReuseInfoManger(sizeList);
    public static final ImageReuseInfo sGridImageReuseInfo = sImageReuseInfoManger.create("big_360");
    public static final ImageReuseInfo sBigImageReuseInfo = sImageReuseInfoManger.create("big");
    public static final ImageReuseInfo sSmallImageReuseInfo = sImageReuseInfoManger.create("small_180");

    public static final int sGirdImageSize = (LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(12 + 12 + 10)) / 2;
    public static final int sBigImageSize = LocalDisplay.SCREEN_WIDTH_PIXELS - LocalDisplay.dp2px(10 + 10);
}