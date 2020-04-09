export class JOB_BASE {
    // jid字段
    JID: string;
    //业务码
    BCODE: string;
    // 上级分类
    BPCODE:string;
    // 数据状态 0是原始数据 1是上交数据
    DATASTATE: string;
    // 数据来源
    DATASOURCE: string;
    // 区县代码的 code值 JDISTRICT
    JDISTRICT: string;
    // 事项名称 固定值 （预购商品房预告登记）
    JTITLE: string;
    // 点击最终提交的时候，改为 预审
    REGTYPE: string;
    // 调档返回的抵押权人
    PROPOSER: string;
    // 坐落字段 FBDCZL
    JLOCATION: string;

    constructor(JID: string, BCODE: string, BPCODE: string, DATASTATE: string, DATASOURCE: string, JDISTRICT: string, JTITLE: string, REGTYPE: string, PROPOSER: string, JLOCATION: string) {
        this.JID = JID || "";
        this.BCODE = BCODE || "";
        this.BPCODE = BPCODE|| "";
        this.DATASTATE = DATASTATE||"";
        this.DATASOURCE = DATASOURCE||"";
        this.JDISTRICT = JDISTRICT;
        this.JTITLE = JTITLE||"";
        this.REGTYPE = REGTYPE||"";
        this.PROPOSER = PROPOSER||"";
        this.JLOCATION = JLOCATION||"";
    }
}