package in.co.mybsolutions.practrapp.Model;

/**
 * Created by dhaval.mehta on 15-May-18.
 */

public class EventTaskModel {

    public String name = "";
    public String desc = "";
    public boolean isChecked = false;

    public EventTaskModel(String name, String desc, boolean isChecked) {
        this.name = name;
        this.desc = desc;
        this.isChecked = isChecked;
    }
}
