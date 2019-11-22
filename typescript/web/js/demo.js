debugger;
var JOB_FORMDATA = {
    JOB_BASE: [
        {
            // jid字段
            JID: "",
            // todo  当前用户ID()
            ACCEPTER: "",
            // 用户名 默认为空
            ACCEPTERNAME: "",
            //业务码
            BCODE: "4000101-02",
            // 上级分类
            BPCODE: "400",
            // 数据状态 0是原始数据 1是上交数据
            DATASTATE: "0",
            // 数据来源
            DATASOURCE: "8",
            // 区县代码的 code值 JDISTRICT
            JDISTRICT: "",
            // 事项名称 固定值 （预购商品房预告登记）
            JTITLE: "",
            // 点击最终提交的时候，改为 预审
            REGTYPE: "预审",
            // 调档返回的抵押权人
            PROPOSER: "",
            // 坐落字段 FBDCZL
            JLOCATION: "",
        }
    ],
    // 基本信息（申请信息表）（收件登记表）
    JOB_SJDJB: [
        {
            //事项名称 登记类型 固定值 （申请事项)
            FDJLX: "",
            // 区县代码 code值（查档前选择的档案所属区域）
            FQXDM: "",
            //产权ID  调档返回的RID
            QLID: "",
            //权利产权ID 空 调档返回的JID
            QLJID: "",
            //申办流水号 (jid一样）
            FYWLSH: "",
            //是否分别持证  不维护
            FSQFBCZ: "",
            // >>>>>>>>>>>>>寄证方式：寄证
            //是否快递寄证 （是、否）
            FSFKDJZ: "",
            //短信联系人 （联系人姓名)
            FDXLXR: "",
            //短信联系电话 （联系人电话）
            FDXTZDH: "",
            //联系地址
            FDZ: "",
            // >>>>>>>>>>>>>寄证方式：寄证
            // >>>>>>>>>>>>>寄材料方式：寄材料
            //是否快递寄材料
            FSFKDJCL: "",
            //收件人
            FSJR: "",
            //收件人联系电话
            FSJRLXDH: "",
            //收件人地址
            FSJRDZ: ""
            // >>>>>>>>>>>>>寄材料方式：寄材料
        }
    ],
    //抵押权人信息（包括了自然人和法人） 抵押权人放前面 默认为法人
    // 界面按照 【预告登记】的义务人的界面来调整
    "JOB_SQRXXB_LINK.IQLR": [
        {
            // 序号 默认1 开始
            "XH": "",
            //共有情况（默认：单独所有）
            "FGYQK": "",
            //权利人类型（自然人、法人）
            "FSQRLX": "",
            //抵押人姓名 （ 申请人名称）
            "FSQRMC": "",
            //证件种类
            "FZJZL": "",
            //证件号码
            "FZJHM": "",
            //申请人类型 （ 申请人性质 ）
            "FDWXZ": "",
            //联系人
            "FLXR": "",
            // 联系电话
            "FLXDH": "",
            //所属行业 (类型是法人的情况下）
            "FSSHY": "",
            //国家/地区
            "FGJDQ": "",
            //户籍所在省市
            "FHJSZSS": "",
            // 工作单位
            "FGZDW": "",
            //通讯地址
            "FTXDZ": "",
            //权利比例（默认：100%） 根据共有方式
            "FQLBL": "",
            //电子邮件
            "FDZYJ": "",
            //邮编
            "FYB": "",
            //性别（默认：不详）
            "FXB": "",
            //法人代表名称 （法人情况下）
            "FFRMC": "",
            //法人证件种类 （法人情况下）
            "FFRZJZL": "",
            //法人证件号码 （法人情况下）
            "FFRZJHM": "",
            //代理人名称
            "FDLRMC": "",
            //代理人证件种类
            "FDLRZJZL": "",
            //代理人证件号码
            "FDLRZJHM": "",
            //代理人电话
            "FDLRDH": "",
            // 代理机构
            "FDLJG": "",
        }
    ],
    // 抵押人（包括了自然人和法人） 默认为自然人
    // 界面按照 【预告登记】的权利人的界面来调整 （自然人的情况）
    // 界面按照 【预告登记】的义务人的界面来调整 （法人的情况）
    "JOB_SQRXXB_OLD_LINK.OLD_IQLR": [
        {
            //序号 以1开头
            "XH": "",
            //权利人类型（自然人、法人）
            "FSQRLX": "法人",
            //权利人名称
            "FSQRMC": "",
            //证件种类 文本
            "FZJZL": "",
            //证件号码
            "FZJHM": "",
            //申请人性质（单位性质） 文本
            "FDWXZ": "",
            //联系人
            "FLXR": "",
            //联系电话
            "FLXDH": "",
            //所属行业（文本）
            "FSSHY": "",
            //国家/地区（文本）
            "FGJDQ": "",
            //户籍所在省市（文本）
            "FHJSZSS": "",
            //通讯地址
            "FTXDZ": "",
            //共有情况（默认：单独所有）
            "FGYQK": "",
            //权利比例（如果是单独所有、共同共有：空）
            // 按份共有 做计算（必须和等于100%）
            "FQLBL": "",
            //电子邮件
            "FDZYJ": "",
            //邮编
            "FYB": "",
            //性别（默认：空字符串）
            "FXB": "",
            //法人代表名称
            "FFRMC": "",
            //法人证件种类 （文本）
            "FFRZJZL": "",
            //法人证件号码
            "FFRZJHM": "",
            //代理人名称
            "FDLRMC": "",
            //代理人证件种类 （文本）
            "FDLRZJZL": "",
            //代理人证件号码
            "FDLRZJHM": "",
            //代理人电话
            "FDLRDH": "",
            // 代理机构
            "FDLJG": "",
            //    工作单位
            "FGZDW": "",
        }
    ],
    //关联权利证明信息表
    "JOB_GLQLZMXXB_LINK.OLD_IQLDJ": [
        {
            // 不动产单元号
            "FBDCDYH": "",
            //房屋编码
            "FFWBM": "",
            //坐落型
            "FZL": "",
            //权利人
            "FQLR": "",
            //义务人
            "FYWR": "",
            //不动产登记证明号
            "FBDCDJZMH": "",
            //原业务号 （调档返回的JID）
            "FOLDJID": ""
        }
    ],
    // 附件信息表
    "JOB_FILES_LINK.IFJQD": [
        {
            //序号
            "XH": "1",
            //资料名称
            "ZLMC": "",
            //资料类型
            "CCJZ": "",
            //上传路径
            "FPATH": "",
            //是否必要
            "XYTG": "",
            //材料分类
            "CLFL": "",
            // 页数
            "FYS": "1",
            // 数量
            "FSL": "2",
            // 页码
            "FYM": "3",
            // 备注(材料提交要求）
            "FBZ": "",
        }
    ]
};
var JOB_FORMDATA_NEW = {};
function Converter() {
    for (var key in JOB_FORMDATA) {
        if (key == "JOB_FILES_LINK.IFJQD") {
            var JOB_FILES_NEW = Converter_XXX(JOB_FORMDATA["JOB_FILES_LINK.IFJQD"], "JOB_FILES.");
            JOB_FORMDATA_NEW["JOB_FILES_LINK.IFJQD"] = JOB_FILES_NEW;
        }
        else if (key == "JOB_SQRXXB_OLD_LINK.OLD_IQLR") {
            var JOB_SQRXXB_NEW = Converter_XXX(JOB_FORMDATA["JOB_SQRXXB_OLD_LINK.OLD_IQLR"], "JOB_SQRXXB_OLD.");
            JOB_FORMDATA_NEW["JOB_SQRXXB_OLD_LINK.OLD_IQLR"] = JOB_SQRXXB_NEW;
        }
        else if (key == "JOB_GLQLZMXXB_LINK.OLD_IQLDJ") {
            var JOB_GLQLZMXXB_NEW = Converter_XXX(JOB_FORMDATA["JOB_GLQLZMXXB_LINK.OLD_IQLDJ"], "JOB_GLQLZMXXB.");
            JOB_FORMDATA_NEW["JOB_GLQLZMXXB_LINK.OLD_IQLDJ"] = JOB_GLQLZMXXB_NEW;
        }
        else if (key == "JOB_SQRXXB_LINK.IQLR") {
            var JOB_SQRXXB_NEW = Converter_XXX(JOB_FORMDATA["JOB_SQRXXB_LINK.IQLR"], "JOB_SQRXXB.");
            JOB_FORMDATA_NEW["JOB_SQRXXB_LINK.IQLR"] = JOB_SQRXXB_NEW;
        }
        else {
            JOB_FORMDATA_NEW[key] = JOB_FORMDATA[key];
        }
    }
    return JOB_FORMDATA_NEW;
}
function Converter_XXX(JOB_DATA, JOB_XXX) {
    var JOB_XXX_DATA = JOB_DATA[0];
    var JOB_XXX_NEW = [];
    var map = {};
    for (var key in JOB_XXX_DATA) {
        map[JOB_XXX + key] = JOB_XXX_DATA[key];
    }
    JOB_XXX_NEW.push(map);
    return JOB_XXX_NEW;
}
