package cn.zengmingyang.mobile.zhihudaily.data.model;

import java.util.List;

/**
 * Created by simonla on 2016/11/5.
 * 下午4:41
 */

public class News {

    /**
     * date : 20161105
     * stories : [{"images":["http://pic2.zhimg.com/557321ad3c262f0f72a81aa25b585d95.jpg"],"type":0,"id":8940877,"ga_prefix":"110516","title":"只会刷，不代表你真的会用信用卡"},{"images":["http://pic2.zhimg.com/55fc5cc04ad8e7086306dd47118248e1.jpg"],"type":0,"id":8942271,"ga_prefix":"110515","title":"孤独的人其实更擅长社交，只要你忘记自己"},{"images":["http://pic2.zhimg.com/091dd2040eeadbb37d70448f9200fad5.jpg"],"type":0,"id":8950991,"ga_prefix":"110514","title":"头顶的旋翼，是如何让直升机往前飞的？"},{"images":["http://pic1.zhimg.com/ae2f11c3bd5f8c354e1a6a00788c58e8.jpg"],"type":0,"id":8950930,"ga_prefix":"110513","title":"喜欢的东西就像是在「发光」，扫过去第一眼就看到"},{"images":["http://pic2.zhimg.com/1a5dbe677b946a21dcaee543cccc5c85.jpg"],"type":0,"id":8950406,"ga_prefix":"110512","title":"大误 · 被子，别跑！"},{"images":["http://pic3.zhimg.com/c7c5ca20ee028de542dd774c546e6dde.jpg"],"type":0,"id":8942365,"ga_prefix":"110511","title":"做点秃黄油，留下蟹的鲜美"},{"images":["http://pic4.zhimg.com/b8c0a2ae93ce82b0471351c9555e9d9b.jpg"],"type":0,"id":8936433,"ga_prefix":"110510","title":"《驴得水》的导演说，「体验派表演其实得从自我出发」"},{"images":["http://pic3.zhimg.com/0b6f1bc4764f90abefb52481ffc6ca76.jpg"],"type":0,"id":8946724,"ga_prefix":"110509","title":"做到这些，你就能申到美国前十的商学院研究生"},{"images":["http://pic4.zhimg.com/86d706c372eee41122859b5d2dd474b7.jpg"],"type":0,"id":8946845,"ga_prefix":"110508","title":"从前以为是杀虫剂，没想到其实是「杀生剂」"},{"images":["http://pic2.zhimg.com/0f5d16a60b5a3d0ed044f976e91aa025.jpg"],"type":0,"id":8935453,"ga_prefix":"110507","title":"好的简历是匠心独运的白描，不是搞推销"},{"images":["http://pic1.zhimg.com/ab2f2c654670bcbd180c0f23931a3ec4.jpg"],"type":0,"id":8944932,"ga_prefix":"110507","title":"特斯拉全系车辆都将完全自动驾驶，技术够格了吗？"},{"images":["http://pic1.zhimg.com/63551bcea0fefb9cbe368675b87d70b0.jpg"],"type":0,"id":8950619,"ga_prefix":"110507","title":"「你想多了，我和 Ta 只是普通朋友」"},{"images":["http://pic4.zhimg.com/6a0987a618b949b1f0f7d65939eb8a57.jpg"],"type":0,"id":8950478,"ga_prefix":"110507","title":"读读日报 24 小时热门 TOP 5 · 最高冷的旅店"},{"images":["http://pic1.zhimg.com/5bc800baefb136a619355c2aa401cff8.jpg"],"type":0,"id":8950409,"ga_prefix":"110506","title":"瞎扯 · 如何正确地吐槽"}]
     * top_stories : [{"image":"http://pic4.zhimg.com/b2d3846dc4d5784c20947fdb931da02b.jpg","type":0,"id":8940877,"ga_prefix":"110516","title":"只会刷，不代表你真的会用信用卡"},{"image":"http://pic2.zhimg.com/436dcaa5e3f6551288924d16d7be9611.jpg","type":0,"id":8942271,"ga_prefix":"110515","title":"孤独的人其实更擅长社交，只要你忘记自己"},{"image":"http://pic3.zhimg.com/d69ed74e46ebdd0691c27e67f3e2158e.jpg","type":0,"id":8935453,"ga_prefix":"110507","title":"好的简历是匠心独运的白描，不是搞推销"},{"image":"http://pic4.zhimg.com/a2cdaeaaadf78cdf1905c4dd5ed1a5b7.jpg","type":0,"id":8936433,"ga_prefix":"110510","title":"《驴得水》的导演说，「体验派表演其实得从自我出发」"},{"image":"http://pic3.zhimg.com/7766a061e3374ca2f498f6ed589e105e.jpg","type":0,"id":8942755,"ga_prefix":"110306","title":"这里是广告 · 斜杠青年的进阶，是斜杠中年还是高级玩家？"}]
     */

    private String date;
    /**
     * images : ["http://pic2.zhimg.com/557321ad3c262f0f72a81aa25b585d95.jpg"]
     * type : 0
     * id : 8940877
     * ga_prefix : 110516
     * title : 只会刷，不代表你真的会用信用卡
     */

    private List<StoriesBean> stories;
    /**
     * image : http://pic4.zhimg.com/b2d3846dc4d5784c20947fdb931da02b.jpg
     * type : 0
     * id : 8940877
     * ga_prefix : 110516
     * title : 只会刷，不代表你真的会用信用卡
     */

    private List<TopStoriesBean> top_stories;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public List<StoriesBean> getStories() {
        return stories;
    }

    public void setStories(List<StoriesBean> stories) {
        this.stories = stories;
    }

    public List<TopStoriesBean> getTop_stories() {
        return top_stories;
    }

    public void setTop_stories(List<TopStoriesBean> top_stories) {
        this.top_stories = top_stories;
    }

    public static class StoriesBean {
        private int type;
        private int id;
        private String ga_prefix;
        private String title;
        private List<String> images;

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public List<String> getImages() {
            return images;
        }

        public void setImages(List<String> images) {
            this.images = images;
        }
    }

    public static class TopStoriesBean {
        private String image;
        private int type;
        private int id;
        private String ga_prefix;
        private String title;

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getGa_prefix() {
            return ga_prefix;
        }

        public void setGa_prefix(String ga_prefix) {
            this.ga_prefix = ga_prefix;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }
    }
}
