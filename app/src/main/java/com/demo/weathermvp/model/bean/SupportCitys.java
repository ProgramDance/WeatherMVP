package com.demo.weathermvp.model.bean;

import java.util.List;

public class SupportCitys {
    /**
     * reason : 查询成功
     * result : [{"id":"1","province":"北京","city":"北京","district":"北京"},{"id":"2","province":"北京","city":"北京","district":"海淀"},{"id":"3","province":"北京","city":"北京","district":"朝阳"}]
     */

    private String reason;
    private List<ResultBean> result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public List<ResultBean> getResult() {
        return result;
    }

    public void setResult(List<ResultBean> result) {
        this.result = result;
    }

    public static class ResultBean {
        /**
         * id : 1
         * province : 北京
         * city : 北京
         * district : 北京
         */

        private String id;
        private String province;
        private String city;
        private String district;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getProvince() {
            return province;
        }

        public void setProvince(String province) {
            this.province = province;
        }

        public String getCity() {
            return city;
        }

        public void setCity(String city) {
            this.city = city;
        }

        public String getDistrict() {
            return district;
        }

        public void setDistrict(String district) {
            this.district = district;
        }
    }
}
