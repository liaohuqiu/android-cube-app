package in.srain.cube.demo.event;

import in.srain.cube.demo.data.ImageListItem;

import java.util.ArrayList;

public class ImageListDataEvent {

    public boolean success;
    public boolean hasMore;
    public ArrayList<ImageListItem> imageList;
}
