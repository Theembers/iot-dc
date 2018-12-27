package com.theembers.iot;

/**
 * @author TheEmbers Guo
 * @version 1.0
 * createTime 2018-11-09 14:41
 */
public class RTUCommandInfo {
    private String id;
    private String sn;
    private String instruction;
    private int instructionType;
    private String tid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSn() {
        return sn;
    }

    public void setSn(String sn) {
        this.sn = sn;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    public int getInstructionType() {
        return instructionType;
    }

    public void setInstructionType(int instructionType) {
        this.instructionType = instructionType;
    }

    public String getTid() {
        return tid;
    }

    public void setTid(String tid) {
        this.tid = tid;
    }

    @Override
    public String toString() {
        return "RTUCommandInfo{" +
                "id='" + id + '\'' +
                ", sn='" + sn + '\'' +
                ", instruction='" + instruction + '\'' +
                ", instructionType=" + instructionType +
                ", tid='" + tid + '\'' +
                '}';
    }

    public enum EInstructionType {
        JSON(1),
        HEX(2);

        int type;

        EInstructionType(int type) {
            this.type = type;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
