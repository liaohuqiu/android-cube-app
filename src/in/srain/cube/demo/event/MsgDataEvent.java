package in.srain.cube.demo.event;

/**
* Created by srain on 2/26/15.
*/
public class MsgDataEvent {
    public String msg;

    public MsgDataEvent(String format, Object... args) {
        if (args.length > 0) {
            msg = String.format(format, args);
        } else {
            msg = format;
        }
    }
}
