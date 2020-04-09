"use strict";
exports.__esModule = true;
var JOB_BASE_1 = require("./JOB_BASE");
var JOB_SQRXXB_LINK_1 = require("./JOB_SQRXXB_LINK");
var Factory = /** @class */ (function () {
    function Factory() {
    }
    Factory.prototype.buildJOB_BASE = function (obj) {
        var data = obj;
        var job_bases = new Array();
        var job_base = new JOB_BASE_1.JOB_BASE(data.JID, data.BCODE, data.BPCODE, data.DATASTATE, data.DATASOURCE, data.JDISTRICT, data.JTITLE, data.REGTYPE, data.PROPOSER, data.JLOCATION);
        job_bases.push(job_base);
        return job_bases;
    };
    ;
    Factory.prototype.bulidJOB_SQRXXB_LINK = function (obj) {
        var datas = obj.QLRXX;
        if (datas === null || datas === void 0)
            return null;
        var job_sqrxxb_links = new Array();
        for (var i = 0; i < datas.length; i++) {
            var data = datas[i];
            var job_sqrxxb_link = new JOB_SQRXXB_LINK_1.JOB_SQRXXB_LINK(data.XH, data.LX, data.MC, data.ZJZL, data.ZJHM, data.DWXZ, data.LXR, data.LXDH, data.SSHY, data.GJDQ, data.HJSZSS, data.TXDZ, data.GYQK, data.QLBL, data.DZYJ, data.YB, data.XB, data.FRMC, data.FRZJZL, data.FRZJHM, data.DLRMC, data.DLRZJZL, data.DLRZJHM, data.DLRDH, data.DLJG, data.GZDW);
            job_sqrxxb_links.push(job_sqrxxb_link);
        }
        return job_sqrxxb_links;
    };
    ;
    Factory.prototype.buildBackObject = function (obj) {
        var backObject = {
            "JOB_BASE": Array(),
            "JOB_SQRXXB_LINK.IQLR": Array()
        };
        backObject["JOB_SQRXXB_LINK.UUU"] = "1";
        backObject.JOB_BASE = this.buildJOB_BASE(obj);
        backObject["JOB_SQRXXB_LINK.IQLR"] = this.bulidJOB_SQRXXB_LINK(obj);
        return backObject;
    };
    ;
    return Factory;
}());
exports.Factory = Factory;
