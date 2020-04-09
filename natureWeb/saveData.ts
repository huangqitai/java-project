// 预购商品房预告登记
// 保存数据格式
export default {
    //todo 需要维护基本数据
    JOB_BASE: [
        {
            // jid字段
            JID: "",
            // todo  当前用户ID()
            // ACCEPTER: "",
            // 用户名 默认为空
            // ACCEPTERNAME: "",
            //业务码
            BCODE: "7000101",
            // 上级分类
            BPCODE: "700",
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
            // 申请人 权利人信息中的权力人名称，。以顿号 、 分隔。 （张三、李四）
            PROPOSER: "",
            // 房屋坐落字段 FBDCZL
            JLOCATION: "",
        }
    ],
    // 基本信息（申请信息表）（收件登记表）
    JOB_SJDJB: [
        {
            //事项名称 登记类型 固定值 (预购商品房预告登记)
            FDJLX: "",
            // 区县代码 code值（查档前选择的档案所属区域）
            FQXDM: "",
            //产权ID 空
            QLID: "",
            //业务号 空
            QLJID: "",
            //申办流水号 (jid一样）
            FYWLSH: "",

            //是否分别持证 （根据页面来判断 ） （是、否）
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
    //预告登记信息表 (查档结果）
    JOB_YGDJXXB: [
        {
            //合同编号 （自己填写）
            FHTBH: '',
            // todo 加上 取的价格 （自己填写） 万元
            FQDJG: "",
            // todo 加上 房屋编码字段 只读
            FFWBM: "",
            //不动产单元号
            FBDCDYH: "",
            //房屋坐落
            FBDCZL: "",
            //房屋性质
            FFWXZ: "",
            //房屋结构
            FFWJG: "",
            //总层数
            FZCS: "",
            //所在层
            FSZC: "",
            //建筑面积
            FJZMJ: "",
            //规划用途
            FGHYT: "",
            //专有建筑面积 todo 加上页面 只读
            FZYJZMJ: "",
            //分摊建筑面积  todo 加上页面， 只读
            FFTJZMJ: "",

        }
    ],
    // 权利人信息（包括了自然人和法人）
    "JOB_SQRXXB_LINK.IQLR": [
        {
            //序号 以1开头
            "JOB_SQRXXB.XH": "",
            //权利人类型（自然人、法人）
            "JOB_SQRXXB.FSQRLX": "法人",
            //权利人名称
            "JOB_SQRXXB.FSQRMC": "",
            //证件种类 文本
            "JOB_SQRXXB.FZJZL": "",
            //证件号码
            "JOB_SQRXXB.FZJHM": "",
            //申请人性质（单位性质） 文本
            "JOB_SQRXXB.FDWXZ": "",
            //联系人
            "JOB_SQRXXB.FLXR": "",
            //联系电话
            "JOB_SQRXXB.FLXDH": "",
            //所属行业（文本）
            "JOB_SQRXXB.FSSHY": "",
            //国家/地区（文本）
            "JOB_SQRXXB.FGJDQ": "",
            //户籍所在省市（文本）
            "JOB_SQRXXB.FHJSZSS": "",
            //通讯地址
            "JOB_SQRXXB.FTXDZ": "",
            //共有情况（默认：单独所有）
            "JOB_SQRXXB.FGYQK": "",
            //权利比例（如果是单独所有、共同共有：空）
            // 按份共有 做计算（必须和等于100%）
            "JOB_SQRXXB.FQLBL": "",
            //电子邮件
            "JOB_SQRXXB.FDZYJ": "",
            //邮编
            "JOB_SQRXXB.FYB": "",
            //性别（默认：空字符串）
            "JOB_SQRXXB.FXB": "",
            //法人代表名称
            "JOB_SQRXXB.FFRMC": "",
            //法人证件种类 （文本）
            "JOB_SQRXXB.FFRZJZL": "",
            //法人证件号码
            "JOB_SQRXXB.FFRZJHM": "",
            //代理人名称
            "JOB_SQRXXB.FDLRMC": "",
            //代理人证件种类 （文本）
            "JOB_SQRXXB.FDLRZJZL": "",
            //代理人证件号码
            "JOB_SQRXXB.FDLRZJHM": "",
            //代理人电话
            "JOB_SQRXXB.FDLRDH": "",
            // 代理机构
            "JOB_SQRXXB.FDLJG": "",
            //    工作单位
            "JOB_SQRXXB.FGZDW": "",

        }
    ],
    // 义务人信息 （暂时不考虑多个）
    // 从合同信息里面取出来的
    "JOB_SQRXXB_OLD_LINK.OLD_IQLR": [
        {
            // 序号 默认1 开始
            "JOB_SQRXXB_OLD.XH": "",
            //共有情况（默认：单独所有）
            "JOB_SQRXXB_OLD.FGYQK": "",
            //权利人类型（自然人、法人）
            "JOB_SQRXXB_OLD.FSQRLX": "",
            //抵押人姓名 （ 申请人名称）
            "JOB_SQRXXB_OLD.FSQRMC": "",
            //证件种类
            "JOB_SQRXXB_OLD.FZJZL": "",
            //证件号码
            "JOB_SQRXXB_OLD.FZJHM": "",
            //申请人类型 （ 申请人性质 ）
            "JOB_SQRXXB_OLD.FDWXZ": "",
            //联系人
            "JOB_SQRXXB_OLD.FLXR": "",
            // 联系电话
            "JOB_SQRXXB_OLD.FLXDH": "",
            //所属行业 (类型是法人的情况下）
            "JOB_SQRXXB_OLD.FSSHY": "",
            //国家/地区
            "JOB_SQRXXB_OLD.FGJDQ": "",
            //户籍所在省市
            "JOB_SQRXXB_OLD.FHJSZSS": "",
            // 工作单位
            "JOB_SQRXXB_OLD.FGZDW": "",
            //通讯地址
            "JOB_SQRXXB_OLD.FTXDZ": "",
            //权利比例（默认：100%） 根据共有方式
            "JOB_SQRXXB_OLD.FQLBL": "",
            //电子邮件
            "JOB_SQRXXB_OLD.FDZYJ": "",
            //邮编
            "JOB_SQRXXB_OLD.FYB": "",
            //性别（默认：不详）
            "JOB_SQRXXB_OLD.FXB": "",
            //法人代表名称 （法人情况下）
            "JOB_SQRXXB_OLD.FFRMC": "",
            //法人证件种类 （法人情况下）
            "JOB_SQRXXB_OLD.FFRZJZL": "",
            //法人证件号码 （法人情况下）
            "JOB_SQRXXB_OLD.FFRZJHM": "",
            //代理人名称
            "JOB_SQRXXB_OLD.FDLRMC": "",
            //代理人证件种类
            "JOB_SQRXXB_OLD.FDLRZJZL": "",
            //代理人证件号码
            "JOB_SQRXXB_OLD.FDLRZJHM": "",
            //代理人电话
            "JOB_SQRXXB_OLD.FDLRDH": "",
            // 代理机构
            "JOB_SQRXXB_OLD.FDLJG": "",
        }
    ],
    // 附件信息表
    "JOB_FILES_LINK.IFJQD": [
        {
            //序号
            "JOB_FILES.XH": "1",
            //资料名称
            "JOB_FILES.ZLMC": "",
            //资料类型 （文本）
            "JOB_FILES.CCJZ": "",
            //上传路径  （文件名 ：： 分隔
            "JOB_FILES.FPATH": "",
            //是否必要 （是、否）
            "JOB_FILES.XYTG": "",
            //材料分类
            "JOB_FILES.CLFL": "",
            // 页数  （实际上传文件的个数）
            "JOB_FILES.FYS": "",
            // 数量 （三个数量求和）
            "JOB_FILES.FSL": "2",
            // 页码 （暂不维护）
            "JOB_FILES.FYM": "3",
            // 备注(材料提交要求）
            "JOB_FILES.FBZ": "",
        }
    ]
};
