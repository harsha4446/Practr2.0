package in.co.mybsolutions.practrapp.Model;

/**
 * Created by dhaval.mehta on 08-May-18.
 */

public class UserModel {

    public String name = "";
    public String desc = "";
    public int image;


    public UserModel(String name, String desc, int image) {
        this.name = name;
        this.desc = desc;
        this.image = image;
    }
}
