package com.yyd.yyd.constants.ienum;



public enum DSEnum {

    MYSQL(0, "com.mysql.cj.jdbc.Driver"), ORACLE(1, "oracle.jdbc.driver.OracleDriver");

    private Integer type;

    private String driverClassName;

    DSEnum(int type, String driverClassName) {
        this.type = type;
        this.driverClassName = driverClassName;
    }

    public int getType() {
        return type;
    }

    public String getDriverClassName() {
        return driverClassName;
    }

    public static String getDriverClassName(Integer type) {
        for (DSEnum dsEnum:DSEnum.values()) {
            if (dsEnum.type.intValue() == type.intValue()) {
                return dsEnum.driverClassName;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return "DSEnum{" +
                "type=" + type +
                ", driverClassName='" + driverClassName + '\'' +
                '}';
    }
}
