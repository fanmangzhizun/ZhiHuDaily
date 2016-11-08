package cn.zengmingyang.mobile.zhihudaily.data.bean;

import java.util.List;

/**
 * Created by simonla on 2016/11/8.
 * 下午5:05
 */

public class NewsComment {

    /**
     * author : EleganceWorld
     * id : 545442
     * content : 上海到济南，无尽的猪排盖饭… （后略）
     * likes : 0
     * time : 1413589303
     * avatar : http://pic2.zhimg.com/1f76e6a25_im.jpg
     */

    private List<CommentsBean> comments;

    public List<CommentsBean> getComments() {
        return comments;
    }

    public void setComments(List<CommentsBean> comments) {
        this.comments = comments;
    }

    public static class CommentsBean {
        private String author;
        private int id;
        private String content;
        private int likes;
        private long time;
        private String avatar;

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public int getLikes() {
            return likes;
        }

        public void setLikes(int likes) {
            this.likes = likes;
        }

        public long getTime() {
            return time;
        }

        public void setTime(long time) {
            this.time = time;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }
    }
}
