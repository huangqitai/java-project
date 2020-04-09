import { transformDateToTimestamp, transformTimestampToDate } from "./TransformFormat";
import { checkEmail, checkPhone, checkIDNumber } from "./TypeCheck";
import saveData from "./saveData";
import {Factory} from "./Factory";
import {JOB_BASE} from "./JOB_BASE";



// let timeStamp:number = transformDateToTimestamp(new Date());
// console.log(timeStamp);
// let time : number= 1578473073395;
// let date : Date = transformTimestampToDate(time);
// console.log(date);

// let phone : string = "157681a5100";
// if(checkPhone(phone))
//  console.log(phone+"手机号正确");
//  else console.log("手机号错误");

// let email : string= "438826893@qq.com";
// if(checkEmail(email))
//  console.log(email+"邮箱正确");
//  else console.log("邮箱错误");


// let idNumber : string = "4405821199811125494";
// if(checkIDNumber(idNumber))
//  console.log(idNumber+"身份证号码正确");
//  else console.log("身份证号码错误");

let data  =
 [{
  // jid字段
  JID: "",
  // todo  当前用户ID()
  // ACCEPTER: "",
  // 用户名 默认为空
  // ACCEPTERNAME: "",
  //业务码

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
  JLOCATION: ""
 },
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
     JLOCATION: ""
   }]

 // console.log(Factory.buildJOB_BASE(data));
let queryResult2 =  {
  // jid字段
  JID: "",
  // todo  当前用户ID()
  // ACCEPTER: "",
  // 用户名 默认为空
  // ACCEPTERNAME: "",
  //业务码

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
  // 区县代码
  "XZQHDM": "武江区",
  "HTSLRQ": "2019-02-15 09:39: 53",
  "HTBH": "201800243",
  "HTPATH": null,
  "HTLX": 1,
  "FFWBM": "44020010255318066302600164",
  "ZL": "韶关市浈江区北江中路33号***号",
  "GYFS": null,
  "FWXZ": null,
  "ZDMJ": "",
  "ZDQLXZ": null,
  "JZMJ": 115.1,
  "ZYJZMJ": 105.99,
  "FTJZMJ": 9.11,
  "DJSJ": "2017-02-06 00:00: 00",
  "DJJG": "韶关市国土资源局",
  "BZDM": "1",
  "BZMC": "人民币",
  "JYZJE": 462700,
  "SFTS": null,
  "SFDJ": 0,
  "SFRQ": "2018-10-16 00: 00: 00",
  "SFJE": 185700,
  "EQRQ": null,
  "EQJE": 0,
  "MQJE": 277000,
  "FKFS": "银行按揭",
  "CQZBLDWZZJGDM": "11440200MB2C61035A",
  "CQZBLDWMC": "相关部门",
  "JFRQ": "2019-02-15 00: 00: 00",
  "ZRFWYJ": 0,
  "ZRFWYBZDM": "1",
  "ZRFWYBZMC": "人民币",
  "SRFWYJ": 0,
  "SRFWYBZDM": "1",
  "SRFWYBZMC": "人民币",
  "ZYCLFS": "1",
  "ZCDW": null,
  "HTFS": 3,
  "DDFDCGLBMFS": 1,
  "DSJFS": 1,
  "SLH": null,
  "SLRQ": "2019-02-15 09: 39: 42",
  "SLR": "一窗受理系统",
  "ZSHM": "粤（2017）韶关市不动产权第***号",
  "GYZM": "粤（2017）韶关市不动产权第***号",
  "XXLY": "林",
  "HX": "三房一厅",
  "CX": "东南",
  "JCND": "2001",
  "GPFS": "1",
  "SZC": "7",
  "ZCS": "8",
  "FWYT": "住宅",
  "ZXQK": "精装",
  // 权利人信息
  "QLRXX": [
    {
      // 权利人类型
      "LX": "1",
      // 是否分别持证
      "SQFBCZ": "1",
      // 共有方式
      "GYFS": "共同共有",
      //所占份额
      "FE": null,
      // 名称
      "MC": "谭*",
      //曾用名（不保存）
      "CYM": null,
      // 证件类型
      "ZJZL": "身份证",
      // 证件号码
      "ZJHM": "430482*********3576",
      // 联系电话
      "LXDH": "186*****952",
      // 国家
      "GJ": "中国",
      // 通讯地址
      "TXDZ": "常宁市蓬塘乡彭塘村街****号",
      // 法定代表人
      "FDDBR": null,
      // 法定代表人证件类型
      "FDDBRZJLX": null,
      // 法定代表人证件号码
      "FDDBRZJHM": null,
      // 代理人姓名
      "DLRXM": null,
      // 代理人电话
      "DLRLXDH": null,
      // 代理人身份证类型
      "DLRSFZZL": null,
      // 代理人证件号码
      "DLRZJH": null,
      //代理人机构名称
      "DLRDLJGMC": null,
      // 权利人家庭成员住宅情况
      "QLRJTCY": []
    },
    {
      "LX": "2",
      "SQFBCZ": "1",
      "GYFS": "共同共有",
      "FE": null,
      "MC": "张*",
      "CYM": null,
      "ZJZL": "身份证",
      "ZJHM": "430482*********6679",
      "LXDH": "186*****952",
      "GJ": "中国",
      "TXDZ": "常宁市蓬塘乡彭塘村街****号",
      "FDDBR": null,
      "FDDBRZJLX": null,
      "FDDBRZJHM": null,
      "DLRXM": null,
      "DLRLXDH": null,
      "DLRSFZZL": null,
      "DLRZJH": null,
      "DLRDLJGMC": null,
      "QLRJTCY": []
    }
  ],
  // 义务人信息
  "YWRXX": [
    {
      "LX": "1",
      // 名称
      "MC": "梁*成",
      // 曾用名 （不保存）
      "CYM": null,
      // 共有方式
      "GYFS": "",
      "FE": null,
      "ZJZL": "身份证",
      // 证件号码
      "ZJHM": "440204********3011",
      // 联系电话 188*****006
      "LXDH": "",
      // 国际
      "GJ": "",
      // 通讯地址
      "TXDZ": "韶关市浈江区乐园镇****号",
      // 法定代表人
      "FDDBR": null,
      // 法定代表人证件类型
      "FDDBRZJLX": null,
      // 法定代表人证件号码
      "FDDBRZJHM": null,
      // 代理人姓名
      "DLRXM": null,
      // 代理人联系电话
      "DLRLXDH": null,
      // 代理人身份证种类
      "DLRSFZZL": null,
      // 代理人证件号
      "DLRZJH": null,
      // 代理机构名称
      "DLRDLJGMC": null,
      // 义务人家庭成员信息（不需要保存）
      "YWRJTCY": []
    },
    {
      "LX": "1",
      "MC": "梁*生",
      "CYM": null,
      "GYFS": "共同共有",
      "FE": null,
      "ZJZL": "身份证",
      "ZJHM": "440204********3011",
      "LXDH": "188*****006",
      "GJ": "中国",
      "TXDZ": "韶关市浈江区乐园镇****号",
      "FDDBR": null,
      "FDDBRZJLX": null,
      "FDDBRZJHM": null,
      "DLRXM": null,
      "DLRLXDH": null,
      "DLRSFZZL": null,
      "DLRZJH": null,
      "DLRDLJGMC": null,
      "YWRJTCY": []
    }
  ]
};
console.log(new Factory().buildBackObject(queryResult2));
// console.log(new Factory().bulidJOB_SQRXXB_LINK(queryResult2.QLRXX));
// let job_base : JOB_BASE = new JOB_BASE("","","","","","","",
//     "","","");
//
// let a = {
//   "name" :"abc"
// }
// console.log(typeof job_base);



