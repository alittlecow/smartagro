package com.sywl.web.domain;

import java.util.List;

/**
 * Created by zhanglj on 2017/7/8.
 */

public class MqttReq {

	/**
     * action : getsysinfo
     * sysinfo : {"VER":"1.0.0","BAT":"4348","LAC":"51DE","CELL":"8750","CCID":"898602B41116C0830825"}
     * warn : {"type":"tmp_l","value":"12.4"}
     * history : [{"key":"2017-02-06 10: 05: 01&12.4&30.6"}]
     */

    private String action;
    private SysinfoBean sysinfo;
    private WarnBean warn;
    private List<HistoryBean> history;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public SysinfoBean getSysinfo() {
        return sysinfo;
    }

    public void setSysinfo(SysinfoBean sysinfo) {
        this.sysinfo = sysinfo;
    }

    public WarnBean getWarn() {
        return warn;
    }

    public void setWarn(WarnBean warn) {
        this.warn = warn;
    }

    public List<HistoryBean> getHistory() {
        return history;
    }

    public void setHistory(List<HistoryBean> history) {
        this.history = history;
    }

    public static class WarnBean {
        /**
         * type : tmp_l
         * value : 12.4
         */

        private String type;
        private String value;

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }

    public static class HistoryBean {
        /**
         * key : 2017-02-06 10: 05: 01&12.4&30.6
         */

        private String record;

		public String getRecord() {
			return record;
		}

		public void setRecord(String record) {
			this.record = record;
		}


        
    }
	
}
