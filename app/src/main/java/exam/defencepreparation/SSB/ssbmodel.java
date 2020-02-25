package exam.defencepreparation.SSB;

/**
 * Created by hp on 5/28/2018.
 */

public class ssbmodel {

    public ssbmodel(String image, String topic) {
        this.image = image;
        Topic = topic;
    }
 public ssbmodel(){}

    private  String image;

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getTopic() {
        return Topic;
    }

    public void setTopic(String topic) {
        Topic = topic;
    }

    private  String Topic;
}
