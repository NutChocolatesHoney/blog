package per.nutchocolateshoney.blog.common;

public class CommonConstant {
    /**
     * 有效
     */
    public enum IsActive {
        TRUE("有效", true),
        FALSE("无效",  false);

        public String name;
        public Boolean value;

        IsActive(String name, Boolean value) {
            this.name = name;
            this.value = value;
        }
    }

    /**
     * 是和否
     */
    public enum YesOrNo {
        YES("是",  true),
        NO("否",  false);

        public String name;
        public Boolean value;

        YesOrNo(String name, Boolean value) {
            this.name = name;
            this.value = value;
        }
    }


    /**
     * 砍价活动状态
     */
    public enum ActivityBargainingStatus {
        ALL("全部",  0),
        DOING("砍价中",  1),
        SUCCESS("砍价成功",  2),
        FAIL("砍价失败",  3);


        public String name;
        public Integer value;

        ActivityBargainingStatus(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
    }

    /**
     * 审核状态
     */
    public enum ExamineStatus {
        WAIT("待审核", 0),
        PASS("审核通过", 1),
        FAIL("审核不通过", -1);

        public String name;
        public Integer value;

        ExamineStatus(String name, Integer value) {
            this.name = name;
            this.value = value;
        }
    }
}
