package com.example.myapplication28.mode.data;

import java.util.List;

/**
 * @创建时间 2021/4/25 20:21
 */
public class HomeBean {

    private Data data;
    private String errmsg;
    private int errno;

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public int getErrno() {
        return errno;
    }

    public void setErrno(int errno) {
        this.errno = errno;
    }

    public static class Data {
        private List<String> imgs;
        private List<Catalog> catalog;

        public List<String> getImgs() {
            return imgs;
        }

        public void setImgs(List<String> imgs) {
            this.imgs = imgs;
        }

        public List<Catalog> getCatalog() {
            return catalog;
        }

        public void setCatalog(List<Catalog> catalog) {
            this.catalog = catalog;
        }

        public static class Catalog {
            private String name;
            private String icon;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getIcon() {
                return icon;
            }

            public void setIcon(String icon) {
                this.icon = icon;
            }
        }
    }
}
